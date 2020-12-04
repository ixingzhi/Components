package com.xingzhi.android.base.android

import android.os.Bundle
import com.trello.rxlifecycle3.components.support.RxAppCompatActivity

/**
 * Created by xiedongdong on 2020/11/28
 */
open class BaseActivity : RxAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onDestroy() {
        super.onDestroy()
    }

}