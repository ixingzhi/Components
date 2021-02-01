package com.xingzhi.android.flutter

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import io.flutter.embedding.android.FlutterActivity

/**
 * Created by xiedongdong on 2020/08/25
 */
internal class FlutterAppActivity : FlutterActivity() {

    companion object {
        private const val EXTRA_INIT_PARAMS = "extraInitParams"

        fun start(context: Context, initParams: String) {
            val intent = Intent(context, FlutterAppActivity::class.java)
            intent.putExtra(EXTRA_INIT_PARAMS, initParams)
            context.startActivity(intent)
        }
    }

    private var initParams: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initParams = intent.getStringExtra(EXTRA_INIT_PARAMS)
    }

    override fun getInitialRoute(): String {
        return if (TextUtils.isEmpty(initParams)) super.getInitialRoute() else initParams!!
    }

}