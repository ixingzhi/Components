package com.xingzhi.android.biz.push.common

import com.xingzhi.android.biz.base.BizApplication
import com.xingzhi.android.biz.utils.SharedPreferencesUtils

/**
 * Created by xiedongdong on 2020/12/14
 */
object PushSetting {

    private const val PUSH_DATA = "PUSH_DATA"

    var pushData: String
        get() = SharedPreferencesUtils.getString(BizApplication.context, PUSH_DATA, "")
        set(data) {
            SharedPreferencesUtils.saveString(BizApplication.context, PUSH_DATA, data)
        }

}