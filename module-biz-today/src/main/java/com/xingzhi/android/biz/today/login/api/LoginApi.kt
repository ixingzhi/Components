package com.xingzhi.android.biz.today.login.api

import com.xingzhi.android.base.http.resp.Response
import com.xingzhi.android.biz.today.login.model.User
import io.reactivex.Observable
import okhttp3.RequestBody
import retrofit2.http.*

/**
 * Created by xiedongdong on 2021/01/19
 */
interface LoginApi {

    @POST("user/login")
    fun login1(
        @Query("username") username: String,
        @Query("password") password: String
    ): Observable<Response<User>>

    @POST("user/login")
    fun login2(@QueryMap params: @JvmSuppressWildcards Map<String, Any>): Observable<Response<User>>

    @POST("user/login")
    fun login3(@Body requestBody: RequestBody): Observable<Response<User>>

}
