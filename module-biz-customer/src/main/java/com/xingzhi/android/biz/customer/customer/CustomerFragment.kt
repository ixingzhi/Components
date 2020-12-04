package com.xingzhi.android.biz.customer.customer

import android.os.Bundle
import android.view.View
import com.xingzhi.android.biz.base.BizFragment
import com.xingzhi.android.biz.customer.R
import kotlinx.android.synthetic.main.biz_customer_fragment_customer.*

/**
 * Created by xiedongdong on 2020/12/01
 */
class CustomerFragment : BizFragment() {

    companion object {
        fun newInstance(): CustomerFragment {
            return CustomerFragment()
        }
    }

    override val layoutId: Int
        get() = R.layout.biz_customer_fragment_customer

    override fun initView(savedInstanceState: Bundle?, view: View?) {
    }

    override fun initEvent() {
        mTvTest.setOnClickListener {
        }
    }

    override fun initData() {
    }

}