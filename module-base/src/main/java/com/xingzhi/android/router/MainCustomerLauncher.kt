package com.xingzhi.android.router

import android.content.Context
import com.alibaba.android.arouter.facade.template.IProvider

/**
 * Created by xiedongdong on 2020/12/01
 */
interface MainCustomerLauncher : IProvider {

    companion object {
        const val MAIN_CUSTOMER_LAUNCHER = "/customer/module/MainCustomerActivity"
    }

    fun startActivity(context: Context)

}