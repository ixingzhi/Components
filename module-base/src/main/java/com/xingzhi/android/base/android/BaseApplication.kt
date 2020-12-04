package com.xingzhi.android.base.android

import android.app.Application

/**
 * Created by xiedongdong on 2020/11/28
 */
abstract class BaseApplication : Application(){

    override fun onCreate() {
        super.onCreate()
        init()
    }

    abstract fun init()

}