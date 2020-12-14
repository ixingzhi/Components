package com.xingzhi.android.components.main

import android.os.Bundle
import com.xingzhi.android.base.android.BaseActivity
import com.xingzhi.android.biz.push.PushInit
import com.xingzhi.android.components.R
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Created by xiedongdong on 2020/11/28
 */
class MainActivity : BaseActivity(), NavFragment.OnTabSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mFragmentManager = supportFragmentManager
        (mNavBar as NavFragment).setup(mFragmentManager, mViewPager, this)

        // push init
        PushInit.with().init(this)
    }

    override fun onTabSelected(navigationButton: NavigationButton) {
    }

    override fun onTabReselected(navigationButton: NavigationButton) {
    }

}