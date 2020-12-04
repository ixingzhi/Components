package com.xingzhi.android.biz.today.module

import android.os.Bundle
import com.xingzhi.android.base.android.BaseActivity
import com.xingzhi.android.biz.today.R
import com.xingzhi.android.biz.today.today.TodayFragment

/**
 * Created by xiedongdong on 2020/12/01
 */
class MainTodayActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.biz_today_activity_main_today)

        addModule()
    }

    private fun addModule() {
        val mFragment = TodayFragment.newInstance()

        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.fl_content_container, mFragment)
        transaction.commit()
    }

}
