package com.xingzhi.android.router

import android.content.Context
import com.alibaba.android.arouter.facade.template.IProvider

/**
 * Created by xiedongdong on 2020/12/01
 */
interface MainOfficeLauncher : IProvider {

    companion object {
        const val MAIN_OFFICE_LAUNCHER = "/office/module/MainCustomerActivity"
    }

    fun startActivity(context: Context)

}