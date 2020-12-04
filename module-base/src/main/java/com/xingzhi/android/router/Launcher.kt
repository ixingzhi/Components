package com.xingzhi.android.router

import com.alibaba.android.arouter.launcher.ARouter
import com.hjq.toast.ToastUtils
import com.xingzhi.android.BuildConfig

/**
 * Created by xiedongdong on 2020/11/28
 */
object Launcher {

    @JvmStatic
    fun check(): Boolean {
        return if (!BuildConfig.IS_MODULE) {
            true
        } else {
            ToastUtils.show("组件开发中")
            false
        }
    }

    @JvmStatic
    fun <T> navigation(launcher: Class<out T>?): T {
        return ARouter.getInstance().navigation(launcher)
    }

}