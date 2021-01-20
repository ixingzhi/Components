package com.xingzhi.android.base.http.api

import com.xingzhi.android.base.http.exception.UnknownRespException
import com.xingzhi.android.base.http.resp.Response
import com.xingzhi.android.base.http.resp.Response2
import com.xingzhi.android.biz.utils.ConvertUtils
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody

/**
 * Created by xiedongdong on 2020/01/18
 */
open class BaseApiService {

    /**
     * form表单提交
     */
    protected fun createRequestBody(params: Any?): RequestBody {
        return RequestBody.create(
            "application/json; charset=utf-8".toMediaTypeOrNull(),
            ConvertUtils.toJson(params)
        )
    }

    /**
     * Response 数据类型解析方式
     */
    protected fun <T> convert(resp: Response<T>): Observable<T> {
        return Observable.create { subscriber: ObservableEmitter<T> ->
            if (resp.errorCode == 0) {
                if (resp.data != null) {
                    subscriber.onNext(resp.data!!)
                    subscriber.onComplete()
                } else {
                    subscriber.onError(UnknownRespException("resp data is null"))
                }
            } else {
                subscriber.onError(UnknownRespException(resp.errorMsg))
            }
        }
    }

    /**
     * Response2 数据类型解析方式
     */
    protected fun <T> convert2(resp: Response2<T>): Observable<T> {
        return Observable.create { subscriber: ObservableEmitter<T> ->
            if (resp.isOk) {
                if (resp.data != null) {
                    subscriber.onNext(resp.data!!)
                    subscriber.onComplete()
                } else {
                    subscriber.onError(UnknownRespException("resp data is null"))
                }
            } else {
                subscriber.onError(UnknownRespException(resp.msg))
            }
        }
    }

}