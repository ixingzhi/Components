package com.xingzhi.android.biz.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import com.xingzhi.android.base.android.BaseActivity

/**
 * Created by xiedongdong on 2020/11/28
 */
abstract class BizActivity : BaseActivity(), IBizView {
    protected lateinit var mContext: Context
    private lateinit var mContentView: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = this

        mContentView = LayoutInflater.from(this).inflate(layoutId, null)
        setContentView(mContentView)
        initView(savedInstanceState, mContentView)
        initEvent()
        initData()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

}
