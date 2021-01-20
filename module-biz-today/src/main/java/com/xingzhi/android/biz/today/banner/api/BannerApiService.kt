package com.xingzhi.android.biz.today.banner.api

import android.content.Context
import com.xingzhi.android.base.http.ComponentsClient
import com.xingzhi.android.base.http.api.BaseApiService
import com.xingzhi.android.base.http.resp.Response
import com.xingzhi.android.biz.today.banner.model.Banner
import io.reactivex.Observable

/**
 * Created by xiedongdong on 2021/01/19
 */
class BannerApiService private constructor(private val api: BannerApi) : BaseApiService() {

    companion object {
        private var instance: BannerApiService? = null

        @Synchronized
        fun getInstance(context: Context): BannerApiService {
            if (instance == null) {
                instance = BannerApiService(
                    ComponentsClient.getInstance(context).client.create(BannerApi::class.java)
                )
            }
            return instance as BannerApiService
        }
    }

    fun getBannerList(): Observable<List<Banner>> {
        return api.getBannerList().flatMap { resp: Response<List<Banner>> -> convert(resp) }
    }

}
