package com.xingzhi.android.biz.push.model

import com.xingzhi.android.biz.model.Model

/**
 * Created by xiedongdong on 2020/12/14
 */
class ResponsePush<T>(
    var code: Int,
    var data: T
) : Model()