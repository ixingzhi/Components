package com.xingzhi.android.biz.today.module

import android.content.Context
import android.content.Intent
import com.alibaba.android.arouter.facade.annotation.Route
import com.xingzhi.android.router.MainTodayLauncher

/**
 * Created by xiedongdong on 2020/12/01
 */
@Route(path = MainTodayLauncher.MAIN_TODAY_LAUNCHER)
class MainTodayLauncherImpl : MainTodayLauncher {

    override fun startActivity(context: Context) {
        context.startActivity(Intent(context, MainTodayActivity::class.java))
    }

    override fun init(context: Context) {}

}