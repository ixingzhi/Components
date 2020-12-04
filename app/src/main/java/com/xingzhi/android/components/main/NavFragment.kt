package com.xingzhi.android.components.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.xingzhi.android.biz.base.BizFragment
import com.xingzhi.android.biz.customer.customer.CustomerFragment
import com.xingzhi.android.biz.email.email.EmailFragment
import com.xingzhi.android.biz.event.HomePageSwitchEvent
import com.xingzhi.android.biz.mine.mine.MineFragment
import com.xingzhi.android.biz.office.office.OfficeFragment
import com.xingzhi.android.biz.today.api.event.UpdateMainTodayCountEvent
import com.xingzhi.android.biz.today.today.TodayFragment
import com.xingzhi.android.biz.utils.EventBusUtils
import com.xingzhi.android.components.R
import kotlinx.android.synthetic.main.fragment_nav.*
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import java.util.*

/**
 * Created by xiedongdong on 2020/11/28
 */
class NavFragment : BizFragment(), View.OnClickListener {
    private var mViewPager: ViewPager? = null
    private var mCurrentNavButton: NavigationButton? = null
    private var mOnTabSelectedListener: OnTabSelectedListener? = null
    private val mFragmentList: MutableList<Fragment> = ArrayList(5)

    override val layoutId: Int
        get() = R.layout.fragment_nav

    override fun initView(savedInstanceState: Bundle?, view: View?) {
        mFragmentList.add(TodayFragment.newInstance())
        mFragmentList.add(CustomerFragment.newInstance())
        mFragmentList.add(EmailFragment.newInstance())
        mFragmentList.add(OfficeFragment.newInstance())
        mFragmentList.add(MineFragment.newInstance())

        mNavToday.init(
            R.drawable.tab_ic_today,
            R.string.main_tab_name_today, mFragmentList, 0
        )
        mNavCustomer.init(
            R.drawable.tab_ic_customer,
            R.string.main_tab_name_customer, mFragmentList, 1
        )
        mNavEmail.init(
            R.drawable.tab_ic_email,
            R.string.main_tab_name_email, mFragmentList, 2
        )
        mNavOffice.init(
            R.drawable.tab_ic_office,
            R.string.main_tab_name_office, mFragmentList, 3
        )
        mNavMine.init(
            R.drawable.tab_ic_mine,
            R.string.main_tab_name_mine, mFragmentList, 4
        )

        EventBusUtils.register(this)
    }

    override fun initEvent() {
        mNavToday.setOnClickListener(this)
        mNavCustomer.setOnClickListener(this)
        mNavEmail.setOnClickListener(this)
        mNavOffice.setOnClickListener(this)
        mNavMine.setOnClickListener(this)
    }

    override fun initData() {}

    override fun onClick(view: View) {
        if (view is NavigationButton) {
            doSelect(view)
        }
    }

    fun setup(
        fragmentManager: FragmentManager?,
        viewPager: ViewPager?,
        listener: OnTabSelectedListener?
    ) {
        mViewPager = viewPager
        mOnTabSelectedListener = listener
        mViewPager?.offscreenPageLimit = 4
        mViewPager?.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(i: Int, v: Float, i1: Int) {}
            override fun onPageSelected(i: Int) {
                setCurrentItem(i)
            }

            override fun onPageScrollStateChanged(i: Int) {}
        })
        val mPageAdapter = PageAdapter(fragmentManager)
        mViewPager!!.adapter = mPageAdapter
        doSelect(mNavToday)
    }

    private fun doSelect(newNavButton: NavigationButton) {
        val oldNavButton: NavigationButton
        if (mCurrentNavButton != null) {
            oldNavButton = mCurrentNavButton as NavigationButton
            if (oldNavButton == newNavButton) {  // 第二次点击相同,则执行
                onReselect(oldNavButton)
                return
            }
            oldNavButton.isSelected = false
        }
        newNavButton.isSelected = true
        mViewPager?.setCurrentItem(newNavButton.tag as Int, false)
        onSelected(newNavButton)
        mCurrentNavButton = newNavButton
    }

    inner class PageAdapter internal constructor(fm: FragmentManager?) :
        FragmentPagerAdapter(fm!!) {
        override fun getItem(position: Int): Fragment {
            return mFragmentList[position]
        }

        override fun getCount(): Int {
            return mFragmentList.size
        }
    }

    private fun onSelected(newNavButton: NavigationButton) {
        mOnTabSelectedListener?.onTabSelected(newNavButton)
    }

    private fun onReselect(navigationButton: NavigationButton) {
        mOnTabSelectedListener?.onTabReselected(navigationButton)
    }

    interface OnTabSelectedListener {
        fun onTabSelected(navigationButton: NavigationButton)
        fun onTabReselected(navigationButton: NavigationButton)
    }

    fun setCurrentItem(position: Int) {
        when (position) {
            0 -> doSelect(mNavToday)
            1 -> doSelect(mNavCustomer)
            2 -> doSelect(mNavEmail)
            3 -> doSelect(mNavOffice)
            4 -> doSelect(mNavMine)
            else -> {
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onEvent(event: UpdateMainTodayCountEvent) {
        mNavToday.showRedDot(event.count)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onEvent(event: HomePageSwitchEvent) {
        if (event.position in 0..5) {
            setCurrentItem(event.position)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBusUtils.unregister(this)
    }

}