package com.xingzhi.android.biz.email.module

import android.content.Context
import android.content.Intent
import com.alibaba.android.arouter.facade.annotation.Route
import com.xingzhi.android.router.MainEmailLauncher

/**
 * Created by xiedongdong on 2020/12/01
 */
@Route(path = MainEmailLauncher.MAIN_EMAIL_LAUNCHER)
class MainEmailLauncherImpl : MainEmailLauncher {

    override fun startActivity(context: Context) {
        context.startActivity(Intent(context, MainEmailActivity::class.java))
    }

    override fun init(context: Context) {}

}