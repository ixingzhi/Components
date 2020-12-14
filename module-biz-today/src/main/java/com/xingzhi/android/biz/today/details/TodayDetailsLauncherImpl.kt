package com.xingzhi.android.biz.today.details

import android.content.Context
import com.alibaba.android.arouter.facade.annotation.Route
import com.xingzhi.android.biz.today.api.router.TodayDetailsLauncher

/**
 * Created by xiedongdong on 2020/12/14
 */
@Route(path = TodayDetailsLauncher.TODAY_DETAILS_LAUNCHER)
class TodayDetailsLauncherImpl : TodayDetailsLauncher {

    override fun init(context: Context?) {
    }

    override fun startActivity(context: Context, id: Int) {
        context.startActivity(TodayDetailsActivity.createIntent(context, id))
    }

    override fun startActivity(context: Context, flags: Int, id: Int) {
        val intent = TodayDetailsActivity.createIntent(context, id)
        intent.flags = flags
        context.startActivity(intent)
    }

}