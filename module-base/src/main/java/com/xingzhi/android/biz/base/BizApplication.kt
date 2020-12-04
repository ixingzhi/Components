package com.xingzhi.android.biz.base

import android.annotation.SuppressLint
import android.content.Context
import com.alibaba.android.arouter.launcher.ARouter
import com.hjq.toast.ToastUtils
import com.xingzhi.android.base.android.BaseApplication
import com.xingzhi.android.BuildConfig

/**
 * Created by xiedongdong on 2020/11/28
 */
open class BizApplication : BaseApplication() {

    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
            private set
    }

    override fun init() {
        context = this
        initARouter()
        ToastUtils.init(this)
    }

    private fun initARouter() {
        // 必须写在init之前
        if (BuildConfig.DEBUG) {
            ARouter.openLog()
            ARouter.openDebug()
        }
        ARouter.init(this)
    }

}
