package com.xingzhi.android.biz.customer.module

import android.os.Bundle
import com.xingzhi.android.base.android.BaseActivity
import com.xingzhi.android.biz.customer.R
import com.xingzhi.android.biz.customer.customer.CustomerFragment

/**
 * Created by xiedongdong on 2020/12/01
 */
class MainCustomerActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.biz_customer_activity_main_customer)

        addModule()
    }

    private fun addModule() {
        val mFragment = CustomerFragment.newInstance()

        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.fl_content_container, mFragment)
        transaction.commit()
    }

}
