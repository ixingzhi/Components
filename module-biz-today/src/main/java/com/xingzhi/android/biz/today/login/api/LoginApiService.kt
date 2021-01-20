package com.xingzhi.android.biz.today.login.api

import android.content.Context
import com.xingzhi.android.base.http.ComponentsClient
import com.xingzhi.android.base.http.api.BaseApiService
import com.xingzhi.android.base.http.resp.Response
import com.xingzhi.android.biz.today.login.model.Login
import com.xingzhi.android.biz.today.login.model.User
import io.reactivex.Observable

/**
 * Created by xiedongdong on 2021/01/19
 */
class LoginApiService private constructor(private val api: LoginApi) : BaseApiService() {

    companion object {
        private var instance: LoginApiService? = null

        @Synchronized
        fun getInstance(context: Context): LoginApiService {
            if (instance == null) {
                instance = LoginApiService(
                    ComponentsClient.getInstance(context).client.create(LoginApi::class.java)
                )
            }
            return instance as LoginApiService
        }

        @Synchronized
        fun getInstance(context: Context, url: String): LoginApiService {
            return LoginApiService(
                ComponentsClient.getInstance(context, url).client.create(LoginApi::class.java)
            )
        }
    }

    fun login1(username: String, password: String): Observable<User> {
        return api.login1(username, password).flatMap { resp: Response<User> -> convert(resp) }
    }

    fun login2(params: Map<String, Any>): Observable<User> {
        return api.login2(params).flatMap { resp: Response<User> -> convert(resp) }
    }

    fun login3(login: Login): Observable<User> {
        return api.login3(createRequestBody(login)).flatMap { resp: Response<User> -> convert(resp) }
    }

}
