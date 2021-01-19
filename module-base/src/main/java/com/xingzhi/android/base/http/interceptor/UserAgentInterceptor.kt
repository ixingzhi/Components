package com.xingzhi.android.base.http.interceptor

import android.content.Context
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

/**
 * Created by xiedongdong on 2020/01/18
 */
class UserAgentInterceptor(context: Context) : Interceptor {
    private val userAgent: String = com.xingzhi.android.base.http.UserAgentBuilder.ua(context)

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val request = original.newBuilder().header("User-Agent", userAgent).build()
        return chain.proceed(request)
    }

}