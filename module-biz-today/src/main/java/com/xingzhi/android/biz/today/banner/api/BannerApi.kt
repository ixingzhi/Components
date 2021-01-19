package com.xingzhi.android.biz.today.banner.api

import com.xingzhi.android.base.http.resp.Response
import com.xingzhi.android.biz.today.banner.model.Banner
import io.reactivex.Observable
import retrofit2.http.GET

/**
 * Created by xiedongdong on 2021/01/19
 */
interface BannerApi {

    @GET("banner/json")
    fun getBannerList(): Observable<Response<List<Banner>>>

}
