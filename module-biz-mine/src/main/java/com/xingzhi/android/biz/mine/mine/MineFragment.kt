package com.xingzhi.android.biz.mine.mine

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.xingzhi.android.biz.base.BizFragment
import com.xingzhi.android.biz.mine.R
import com.xingzhi.android.biz.mine.settings.SettingsActivity
import com.xingzhi.android.biz.utils.AppUtils
import com.xingzhi.android.flutter.FlutterRoute
import kotlinx.android.synthetic.main.biz_mine_fragment_mine.*

/**
 * Created by xiedongdong on 2020/11/28
 */
class MineFragment : BizFragment() {

    companion object {
        fun newInstance(): MineFragment {
            return MineFragment()
        }
    }

    override val layoutId: Int
        get() = R.layout.biz_mine_fragment_mine

    override fun initView(savedInstanceState: Bundle?, view: View?) {
    }

    override fun initEvent() {
        mTvSettings.setOnClickListener {
            startActivity(Intent(mContext, SettingsActivity::class.java))
        }

        mTvFlutterMain.setOnClickListener {
            FlutterRoute.main(mContext)
        }

        mTvFlutterVersion.setOnClickListener {
            FlutterRoute.versionInfo(mContext, AppUtils.getAppVersionName(context))
        }
    }

    override fun initData() {
    }

}