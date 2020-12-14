package com.xingzhi.android.biz.push

import android.content.Context
import android.text.TextUtils
import com.huawei.hms.aaid.HmsInstanceId
import com.xingzhi.android.biz.push.common.ConfigPush
import com.xingzhi.android.biz.push.common.MessageHandler
import com.xiaomi.mipush.sdk.MiPushClient
import com.xingzhi.android.biz.base.BizApplication
import com.xingzhi.android.biz.push.common.PushSetting
import com.xingzhi.android.biz.push.update.UpdatePushToken
import com.xingzhi.android.biz.utils.OSUtils
import io.reactivex.schedulers.Schedulers

/**
 * Created by xiedongdong on 2020/12/14
 */
class PushInit {

    companion object {
        // app是否已经初始化
        var shouldInit = false

        fun with(): PushInit {
            return PushInit()
        }
    }

    /**
     * 初始化
     */
    fun init(context: Context) {
        shouldInit = true

        if (OSUtils.isEmui()) {
            huaweiPushInit(context)
        } else {
            xiaomiPushInit()
        }

        // 有推送消息，需要处理
        val data = PushSetting.pushData
        if (!TextUtils.isEmpty(data)) {
            MessageHandler.messageHandle(context, data)
            PushSetting.pushData = ""
        }
    }

    private fun huaweiPushInit(context: Context) {
        runOnIOThread(Runnable {
            try {
                // EMUI10.0及以上版本的华为设备上，getToken接口直接返回token；如果当次调用失败PUSH会自动重试申请，成功后则以onNewToken接口返回
                val token = HmsInstanceId.getInstance(context.applicationContext).getToken(
                    ConfigPush.HUAWEI_APP_ID, "HCM"
                )
                if (!TextUtils.isEmpty(token)) {
                    UpdatePushToken.instance.update(token)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        })
    }

    private fun xiaomiPushInit() {
        MiPushClient.registerPush(
            BizApplication.context,
            ConfigPush.XIAOMI_APP_ID,
            ConfigPush.XIAOMI_APP_KEY
        )
    }

    private fun runOnIOThread(runnable: Runnable) {
        Schedulers.io()
            .createWorker()
            .schedule(runnable)
    }

}