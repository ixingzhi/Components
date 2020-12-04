package com.xingzhi.android.biz.mine.module

import android.content.Context
import android.content.Intent
import com.alibaba.android.arouter.facade.annotation.Route
import com.xingzhi.android.router.MainMineLauncher

/**
 * Created by xiedongdong on 2020/12/01
 */
@Route(path = MainMineLauncher.MAIN_MINE_LAUNCHER)
class MainMineLauncherImpl : MainMineLauncher {

    override fun startActivity(context: Context) {
        context.startActivity(Intent(context, MainMineActivity::class.java))
    }

    override fun init(context: Context) {}

}