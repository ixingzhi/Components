package com.xingzhi.android.biz.push.common

import android.content.Context
import android.content.Intent
import android.text.TextUtils
import com.xingzhi.android.biz.push.PushInit
import com.google.gson.reflect.TypeToken
import com.xingzhi.android.biz.SplashActivity
import com.xingzhi.android.biz.mine.api.router.SettingsLauncher
import com.xingzhi.android.biz.push.model.ResponsePush
import com.xingzhi.android.biz.push.model.TodayDetails
import com.xingzhi.android.biz.today.api.router.TodayDetailsLauncher
import com.xingzhi.android.biz.utils.AppUtils
import com.xingzhi.android.biz.utils.ConvertUtils
import com.xingzhi.android.router.Launcher
import org.json.JSONObject


/**
 * Created by xiedongdong on 2020/12/14
 */
object MessageHandler {

    fun init(context: Context?, data: String?) {
        if (context == null || TextUtils.isEmpty(data)) {
            return
        }
        val shouldInit = PushInit.shouldInit
        // TODO 此处根据自身业务页面逻辑实现 "isLogin"
        val isLogin = true

        // 没有登录，没有打开app
        if (!isLogin || !shouldInit) {
            PushSetting.pushData = data!!
            launcherApp(context, shouldInit)
            return
        }

        // app是否在后台运行
        val isBackground = AppUtils.isAppBackground(context)
        if (isBackground) {
            launcherApp(context, shouldInit)
        }

        // 分发消息​
        messageHandle(context, data!!)
    }

    /**
     * 消息分发
     */
    fun messageHandle(context: Context, data: String) {
        try {
            when (JSONObject(data).get("code") as Int) {
                PushType.SETTINGS_TYPE -> {
                    toSettings(context)
                }
                PushType.TODAY_DETAILS_TYPE -> {
                    val type = object : TypeToken<ResponsePush<TodayDetails>>() {}.type
                    val response = ConvertUtils.fromJson<ResponsePush<TodayDetails>>(data, type)
                    toTodayDetails(context, response.data.id)
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun launcherApp(context: Context, shouldInit: Boolean) {
        if (shouldInit) {
            val intent =
                AppUtils.getAppOpenIntentByPackageName(context, "com.xingzhi.android.components")
            context.startActivity(intent)
        } else {
            val intent = Intent(context, SplashActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)
        }
    }

    /**
     * 我的组件 - 设置页面
     */
    private fun toSettings(context: Context) {
        Launcher.navigation(SettingsLauncher::class.java).startActivity(
            context,
            Intent.FLAG_ACTIVITY_NEW_TASK
        )
    }

    /**
     * 今日组件 - 今日详情页面
     */
    private fun toTodayDetails(context: Context, id: Int) {
        Launcher.navigation(TodayDetailsLauncher::class.java).startActivity(
            context,
            Intent.FLAG_ACTIVITY_NEW_TASK,
            id
        )
    }

}