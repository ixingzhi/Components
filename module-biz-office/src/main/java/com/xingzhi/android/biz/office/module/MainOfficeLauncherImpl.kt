package com.xingzhi.android.biz.office.module

import android.content.Context
import android.content.Intent
import com.alibaba.android.arouter.facade.annotation.Route
import com.xingzhi.android.router.MainOfficeLauncher

/**
 * Created by xiedongdong on 2020/12/01
 */
@Route(path = MainOfficeLauncher.MAIN_OFFICE_LAUNCHER)
class MainOfficeLauncherImpl : MainOfficeLauncher {

    override fun startActivity(context: Context) {
        context.startActivity(Intent(context, MainOfficeActivity::class.java))
    }

    override fun init(context: Context) {}

}