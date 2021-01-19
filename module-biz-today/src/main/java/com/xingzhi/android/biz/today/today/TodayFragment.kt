package com.xingzhi.android.biz.today.today

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.xingzhi.android.biz.base.BizFragment
import com.xingzhi.android.biz.event.HomePageSwitchEvent
import com.xingzhi.android.biz.mine.api.router.SettingsLauncher
import com.xingzhi.android.biz.today.R
import com.xingzhi.android.biz.today.api.event.UpdateMainTodayCountEvent
import com.xingzhi.android.biz.today.banner.activity.BannerListActivity
import com.xingzhi.android.biz.today.details.TodayDetailsActivity
import com.xingzhi.android.biz.today.login.activity.LoginActivity
import com.xingzhi.android.biz.today.test.TestActivity
import com.xingzhi.android.biz.utils.EventBusUtils
import com.xingzhi.android.router.Launcher
import kotlinx.android.synthetic.main.biz_today_fragment_today.*

/**
 * Created by xiedongdong on 2020/11/28
 */
class TodayFragment : BizFragment() {

    companion object {
        fun newInstance(): TodayFragment {
            return TodayFragment()
        }
    }

    private var redDotCount = 0

    override val layoutId: Int
        get() = R.layout.biz_today_fragment_today

    override fun initView(savedInstanceState: Bundle?, view: View?) {
    }

    override fun initEvent() {
        mTvSettings.setOnClickListener {
            if (Launcher.check()) {
                Launcher.navigation(SettingsLauncher::class.java)
                    .startActivity(mContext)
            }
        }

        mTvEmailSwitch.setOnClickListener { EventBusUtils.post(HomePageSwitchEvent(2)) }

        mTvUpdateTodayRedDotCount.setOnClickListener {
            redDotCount++
            EventBusUtils.post(UpdateMainTodayCountEvent(redDotCount))
        }

        mTvTest.setOnClickListener { startActivity(Intent(mContext, TestActivity::class.java)) }

        mTvTodayDetails.setOnClickListener {
            startActivity(TodayDetailsActivity.createIntent(mContext, 0))
        }

        mTvBannerList.setOnClickListener {
            startActivity(Intent(mContext, BannerListActivity::class.java))
        }

        mTvLogin.setOnClickListener {
            startActivity(Intent(mContext, LoginActivity::class.java))
        }
    }

    override fun initData() {
    }

}