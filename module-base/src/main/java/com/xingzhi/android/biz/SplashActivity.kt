package com.xingzhi.android.biz

import android.os.Bundle
import com.xingzhi.android.router.MainCustomerLauncher
import com.xingzhi.android.BuildConfig
import com.xingzhi.android.base.android.BaseActivity
import com.xingzhi.android.biz.utils.AppUtils
import com.xingzhi.android.router.*

/**
 * Created by xiedongdong on 2020/12/01
 */
class SplashActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        toMainActivity()
    }

    private fun toMainActivity() {
        if (BuildConfig.DEBUG && BuildConfig.IS_MODULE) {
            // 根据包名，跳转不同的组件
            when (AppUtils.getAppPackageName(this)) {
                "com.xingzhi.android.biz.today" -> Launcher.navigation(MainTodayLauncher::class.java)
                    .startActivity(this)
                "com.xingzhi.android.biz.customer" -> Launcher.navigation(MainCustomerLauncher::class.java)
                    .startActivity(this)
                "com.xingzhi.android.biz.email" -> Launcher.navigation(MainEmailLauncher::class.java)
                    .startActivity(this)
                "com.xingzhi.android.biz.office" -> Launcher.navigation(MainOfficeLauncher::class.java)
                    .startActivity(this)
                "com.xingzhi.android.biz.mine" -> Launcher.navigation(MainMineLauncher::class.java)
                    .startActivity(this)
            }
        } else {
            if (Launcher.check()) {
                Launcher.navigation(MainLauncher::class.java).startMainActivity(this)
            }
        }

        finish()
    }

}