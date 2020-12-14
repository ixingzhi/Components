package com.xingzhi.android.biz.today.api.router

import android.content.Context

import com.alibaba.android.arouter.facade.template.IProvider

/**
 * Created by xiedongdong on 2020/12/14
 */
interface TodayDetailsLauncher : IProvider {

    companion object {
        const val TODAY_DETAILS_LAUNCHER = "/today/details/TodayDetailsActivity"
    }

    fun startActivity(context: Context, id: Int)

    fun startActivity(context: Context, flags: Int, id: Int)

}
