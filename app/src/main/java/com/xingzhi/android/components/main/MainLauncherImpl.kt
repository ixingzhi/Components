package com.xingzhi.android.components.main

import android.app.Activity
import android.content.Context
import android.content.Intent
import com.alibaba.android.arouter.facade.annotation.Route
import com.xingzhi.android.components.R
import com.xingzhi.android.router.MainLauncher

/**
 * Created by xiedongdong on 2020/11/28
 */
@Route(path = MainLauncher.MAIN_LAUNCHER)
class MainLauncherImpl : MainLauncher {

    override fun startMainActivity(context: Context) {
        val intent = Intent(context, MainActivity::class.java)
        context.startActivity(intent)
        (context as Activity).overridePendingTransition(R.anim.biz_zoom_center_in, 0)
    }

    override fun init(context: Context) {}

}