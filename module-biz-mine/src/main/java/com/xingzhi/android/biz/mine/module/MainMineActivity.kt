package com.xingzhi.android.biz.mine.module

import android.os.Bundle
import com.xingzhi.android.base.android.BaseActivity
import com.xingzhi.android.biz.mine.R
import com.xingzhi.android.biz.mine.mine.MineFragment

/**
 * Created by xiedongdong on 2020/12/01
 */
class MainMineActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.biz_mine_activity_main_mine)

        addModule()
    }

    private fun addModule() {
        val mFragment = MineFragment.newInstance()

        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.fl_content_container, mFragment)
        transaction.commit()
    }

}
