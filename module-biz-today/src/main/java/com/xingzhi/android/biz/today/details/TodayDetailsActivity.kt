package com.xingzhi.android.biz.today.details

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.hjq.toast.ToastUtils
import com.xingzhi.android.biz.base.BizActivity
import com.xingzhi.android.biz.today.R

/**
 * Created by xiedongdong on 2020/12/14
 */
class TodayDetailsActivity : BizActivity() {

    companion object {
        private const val EXTRA_ID = "extraID"

        fun createIntent(context: Context, id: Int): Intent {
            val intent = Intent(context, TodayDetailsActivity::class.java)
            intent.putExtra(EXTRA_ID, id)
            return intent
        }
    }

    override val layoutId: Int
        get() = R.layout.biz_today_activity_today_details

    override fun initView(savedInstanceState: Bundle?, view: View?) {
    }

    override fun initEvent() {
    }

    override fun initData() {
        val id = intent.getIntExtra(EXTRA_ID, 0)
        ToastUtils.show(id)
    }

}