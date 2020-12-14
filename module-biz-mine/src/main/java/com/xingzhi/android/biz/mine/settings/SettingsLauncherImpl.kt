package com.xingzhi.android.biz.mine.settings

import android.content.Context
import android.content.Intent
import com.alibaba.android.arouter.facade.annotation.Route
import com.xingzhi.android.biz.mine.api.router.SettingsLauncher

/**
 * Created by xiedongdong on 2020/11/28
 */
@Route(path = SettingsLauncher.SETTINGS_LAUNCHER)
class SettingsLauncherImpl : SettingsLauncher {

    override fun init(context: Context?) {
    }

    override fun startActivity(context: Context) {
        context.startActivity(Intent(context, SettingsActivity::class.java))
    }

    override fun startActivity(context: Context, flags: Int) {
        val intent = Intent(context, SettingsActivity::class.java)
        intent.flags = flags
        context.startActivity(intent)
    }

}