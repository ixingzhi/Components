package com.xingzhi.android.base.http

import android.content.Context
import com.xingzhi.android.BuildConfig
import com.xingzhi.android.base.http.interceptor.*
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

/**
 * Created by xiedongdong on 2020/01/18
 */
class ComponentsClient {

    lateinit var client: Retrofit

    companion object {
        /*
        * img base url
        */
        var BASE_IMG_URL = ""

        /*
        * web base url
        */
        var BASE_WEB_URL = ""

        /*
        * api base url
        */
        var BASE_API_URL = "https://www.wanandroid.com/"

        private val CLIENT_MAP: MutableMap<String, ComponentsClient> = HashMap()

        @Synchronized
        fun getInstance(context: Context): ComponentsClient {
            return getInstance(context, BASE_API_URL)
        }

        @Synchronized
        fun getInstance(context: Context, baseUrl: String): ComponentsClient {
            var client = CLIENT_MAP[baseUrl]
            if (client == null) {
                client = ComponentsClient()

                val clientBuilder =
                    OkHttpClient.Builder()
                        //.cookieJar(new CookieJar(PersistentCookieStore.getInstance(context)))
                        .addInterceptor(DynamicTimeoutInterceptor())
                        .addInterceptor(ApiVersionInterceptor())
                        .addInterceptor(HttpExceptionInterceptor())
                        .addInterceptor(UserAgentInterceptor(context))

                if (BuildConfig.DEBUG) {
                    clientBuilder.addInterceptor(HttpLoggingInterceptor())
                }

                client.client = Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .client(clientBuilder.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()
                CLIENT_MAP[baseUrl] = client
            }
            return client
        }
    }

}