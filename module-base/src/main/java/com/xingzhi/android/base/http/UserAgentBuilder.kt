package com.xingzhi.android.base.http

import android.content.Context
import android.os.Build
import com.xingzhi.android.biz.utils.AppUtils
import org.apache.commons.lang.StringUtils

/**
 * Created by xiedongdong on 2020/01/18
 */
object UserAgentBuilder {

    private const val USER_AGENT_FORMAT = "componentsAgent/1.0 Android/{platform_version} {code_name}/{app_version} {channel}/0 {brand}/{phone_model} frontend/1.0 api/1.0"

    fun ua(context: Context): String {
        val platformVersion = safeFormat(Build.VERSION.RELEASE)
        val codeName = safeFormat(context.packageName)
        val appVersion = safeFormat(AppUtils.getAppVersionName(context))
        val channel = safeFormat("")
        val brand = safeFormat(Build.MANUFACTURER)
        val phoneModel = safeFormat(Build.MODEL)
        return USER_AGENT_FORMAT
                .replace("{platform_version}", platformVersion)
                .replace("{code_name}", codeName)
                .replace("{app_version}", appVersion)
                .replace("{channel}", channel)
                .replace("{brand}", brand)
                .replace("{phone_model}", phoneModel)
    }

    private fun safeFormat(text: String): String {
        return StringUtils.replace(StringUtils.trimToEmpty(text), " ", "_")
    }

}