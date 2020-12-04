package com.xingzhi.android.biz.email.email

import android.os.Bundle
import android.view.View
import com.xingzhi.android.biz.base.BizFragment
import com.xingzhi.android.biz.email.R
import kotlinx.android.synthetic.main.biz_email_fragment_email.*

/**
 * Created by xiedongdong on 2020/12/01
 */
class EmailFragment : BizFragment() {

    companion object {
        fun newInstance(): EmailFragment {
            return EmailFragment()
        }
    }

    override val layoutId: Int
        get() = R.layout.biz_email_fragment_email

    override fun initView(savedInstanceState: Bundle?, view: View?) {
    }

    override fun initEvent() {
        mTvTest.setOnClickListener {

        }
    }

    override fun initData() {
    }

}