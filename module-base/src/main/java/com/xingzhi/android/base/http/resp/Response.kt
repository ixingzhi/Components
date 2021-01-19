package com.xingzhi.android.base.http.resp

import com.xingzhi.android.biz.model.Model


/**
 * Created by xiedongdong on 2020/01/18
 */
class Response<T>(
    var errorCode: Int = 0,
    var errorMsg: String? = null,
    var data: T? = null
) : Model()