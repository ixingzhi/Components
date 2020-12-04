package com.xingzhi.android.biz.office.office

import android.os.Bundle
import android.view.View
import com.xingzhi.android.biz.base.BizFragment
import com.xingzhi.android.biz.office.R
import kotlinx.android.synthetic.main.biz_office_fragment_office.*

/**
 * Created by xiedongdong on 2020/12/01
 */
class OfficeFragment : BizFragment() {

    companion object {
        fun newInstance(): OfficeFragment {
            return OfficeFragment()
        }
    }

    override val layoutId: Int
        get() = R.layout.biz_office_fragment_office

    override fun initView(savedInstanceState: Bundle?, view: View?) {
    }

    override fun initEvent() {
        mTvTest.setOnClickListener {

        }
    }

    override fun initData() {
    }

}