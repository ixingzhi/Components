package com.xingzhi.android.base.http.interceptor

import com.xingzhi.android.base.http.ComponentsClient
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import java.util.concurrent.TimeUnit

/**
 * Created by xiedongdong on 2020/01/18
 */
class DynamicTimeoutInterceptor : Interceptor {

    companion object {
        private val urlTimeout60List = mutableListOf<String>()
        private val urlTimeout80List = mutableListOf<String>()

        init {
            // 60秒延时
            urlTimeout60List.add(ComponentsClient.BASE_API_URL + "api/xxx")

            // 80秒延时
            urlTimeout80List.add(ComponentsClient.BASE_API_URL + "api/xxx")
        }
    }

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val questUrl = request.url.toString()

        var isContains60 = false
        for (i in urlTimeout60List) {
            if (questUrl.contains(i)) {
                isContains60 = true
                break
            }
        }

        var isContains80 = false
        for (i in urlTimeout80List) {
            if (questUrl.contains(i)) {
                isContains80 = true
                break
            }
        }

        // 超时时间设置到60s，系统默认10s
        return when {
            isContains60 -> {
                chain.withConnectTimeout(60, TimeUnit.SECONDS)
                        .withReadTimeout(60, TimeUnit.SECONDS)
                        .withWriteTimeout(60, TimeUnit.SECONDS)
                        .proceed(request)
            }
            isContains80 -> {
                chain.withConnectTimeout(80, TimeUnit.SECONDS)
                        .withReadTimeout(80, TimeUnit.SECONDS)
                        .withWriteTimeout(80, TimeUnit.SECONDS)
                        .proceed(request)
            }
            else -> chain.proceed(request)
        }
    }

}