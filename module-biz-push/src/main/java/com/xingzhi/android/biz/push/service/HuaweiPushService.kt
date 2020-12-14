package com.xingzhi.android.biz.push.service

import android.text.TextUtils
import com.xingzhi.android.biz.push.update.UpdatePushToken
import com.huawei.hms.push.HmsMessageService
import com.huawei.hms.push.RemoteMessage

/**
 * Created by xiedongdong on 2020/12/14
 */
class HuaweiPushService : HmsMessageService() {

    override fun onNewToken(token: String?) {
        super.onNewToken(token)
        if (!TextUtils.isEmpty(token)) {
            UpdatePushToken.instance.update(token ?: "")
        }
    }

    override fun onMessageReceived(message: RemoteMessage?) {
        super.onMessageReceived(message)
        if (message == null) {
            return
        }
    }

}