package com.xingzhi.android.biz.email.module

import android.os.Bundle
import com.xingzhi.android.base.android.BaseActivity
import com.xingzhi.android.biz.email.R
import com.xingzhi.android.biz.email.email.EmailFragment

/**
 * Created by xiedongdong on 2020/12/01
 */
class MainEmailActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.biz_email_activity_main_email)

        addModule()
    }

    private fun addModule() {
        val mFragment = EmailFragment.newInstance()

        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.fl_content_container, mFragment)
        transaction.commit()
    }

}
