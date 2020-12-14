package com.xingzhi.android.biz.mine.api.router

import android.content.Context

import com.alibaba.android.arouter.facade.template.IProvider

/**
 * Created by xiedongdong on 2020/11/28
 */
interface SettingsLauncher : IProvider {

    companion object {
        const val SETTINGS_LAUNCHER = "/mine/settings/SettingsActivity"
    }

    fun startActivity(context: Context)

    fun startActivity(context: Context, flags: Int)

}
