package com.xingzhi.android.base.http.interceptor

import com.xingzhi.android.biz.base.BizApplication
import com.xingzhi.android.biz.utils.AppUtils
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

/**
 * Created by xiedongdong on 2020/01/18
 */
class ApiVersionInterceptor : Interceptor {

    private val versionName = AppUtils.getAppVersionName(BizApplication.context)
    private var token = ""

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val request = original.newBuilder()
            .addHeader("versionName", versionName)
            .addHeader("token", token)
            .build()
        return chain.proceed(request)
    }

}