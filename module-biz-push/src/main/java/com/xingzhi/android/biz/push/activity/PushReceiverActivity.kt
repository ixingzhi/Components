package com.xingzhi.android.biz.push.activity

import android.os.Bundle
import com.xingzhi.android.biz.push.common.MessageHandler
import com.xingzhi.android.base.android.BaseActivity

/**
 * Created by xiedongdong on 2020/12/14
 */
class PushReceiverActivity : BaseActivity() {

    companion object {
        private const val EXTRA_DATA = "extraData"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val data = intent.getStringExtra(EXTRA_DATA)

        MessageHandler.init(this, data)
        finish()
    }

}