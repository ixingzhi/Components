package com.xingzhi.android.biz.customer.module

import android.content.Context
import android.content.Intent
import com.alibaba.android.arouter.facade.annotation.Route
import com.xingzhi.android.router.MainCustomerLauncher

/**
 * Created by xiedongdong on 2020/12/01
 */
@Route(path = MainCustomerLauncher.MAIN_CUSTOMER_LAUNCHER)
class MainCustomerLauncherImpl : MainCustomerLauncher {

    override fun startActivity(context: Context) {
        context.startActivity(Intent(context, MainCustomerActivity::class.java))
    }

    override fun init(context: Context) {}

}