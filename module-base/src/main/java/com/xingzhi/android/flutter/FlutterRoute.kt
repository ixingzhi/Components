package com.xingzhi.android.flutter

import android.content.Context
import com.xingzhi.android.biz.utils.AppUtils
import com.xingzhi.android.biz.utils.ConvertUtils

/**
 * Created by xiedongdong on 2020/08/24
 */
object FlutterRoute {

    fun main(context: Context) {
        FlutterAppActivity.start(context, "")
    }

    fun versionInfo(context: Context, version: String) {
        val params = InitialRoute("version_info_route", version)
        FlutterAppActivity.start(context, ConvertUtils.toJson(params))
    }

}