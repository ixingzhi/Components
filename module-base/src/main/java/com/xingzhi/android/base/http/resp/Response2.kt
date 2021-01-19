package com.xingzhi.android.base.http.resp

import com.xingzhi.android.biz.model.Model

/**
 * Created by xiedongdong on 2020/01/18
 */
class Response2<T>(
    var isOk: Boolean = false,
    var msg: String? = null,
    var data: T? = null
) : Model()