package com.xingzhi.android.base.http.exception

/**
 * Created by xiedongdong on 2020/01/18
 */
class HttpRespException(val httpCode: Int, msg: String?) : RespException(msg ?: "")