package com.xingzhi.android.biz.office.module

import android.os.Bundle
import com.xingzhi.android.base.android.BaseActivity
import com.xingzhi.android.biz.office.R
import com.xingzhi.android.biz.office.office.OfficeFragment

/**
 * Created by xiedongdong on 2020/12/01
 */
class MainOfficeActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.biz_office_activity_main_office)

        addModule()
    }

    private fun addModule() {
        val mFragment = OfficeFragment.newInstance()

        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.fl_content_container, mFragment)
        transaction.commit()
    }

}
