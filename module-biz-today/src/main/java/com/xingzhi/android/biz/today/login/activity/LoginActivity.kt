package com.xingzhi.android.biz.today.login.activity

import android.os.Bundle
import android.view.View
import com.trello.rxlifecycle3.android.ActivityEvent
import com.xingzhi.android.base.http.RespHandler
import com.xingzhi.android.base.http.exception.RespException
import com.xingzhi.android.biz.base.BizActivity
import com.xingzhi.android.biz.today.R
import com.xingzhi.android.biz.today.login.api.LoginApiService
import com.xingzhi.android.biz.today.login.model.Login
import com.xingzhi.android.biz.today.login.model.User
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.biz_today_activity_login.*

/**
 * Created by xiedongdong on 2021/01/19
 */
class LoginActivity : BizActivity() {

    override val layoutId: Int
        get() = R.layout.biz_today_activity_login

    override fun initView(savedInstanceState: Bundle?, view: View?) {
    }

    override fun initEvent() {
        mBtnLogin1.setOnClickListener { login1() }

        mBtnLogin2.setOnClickListener { login2() }

        mBtnLogin3.setOnClickListener { login3() }
    }

    override fun initData() {
    }

    /**
     * 参数传递
     */
    private fun login1() {
        val username = "admin"
        val password = "123456"

        LoginApiService.getInstance(mContext).login1(username, password)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .compose(this.bindUntilEvent(ActivityEvent.DESTROY))
            .subscribe(object : RespHandler<User>() {

                override fun onStart() {}

                override fun onSuccess(data: User) {}

                override fun onFailure(e: RespException) {
                    showToast(e.message)
                }

                override fun onFinish() {}

            })
    }

    /**
     * Map 参数传递
     */
    private fun login2() {
        val params = HashMap<String, Any>(4)
        params["username"] = "admin"
        params["password"] = "123456"
        params["xxx"] = "xxx"
        params["xxx"] = "xxx"

        LoginApiService.getInstance(mContext).login2(params)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .compose(this.bindUntilEvent(ActivityEvent.DESTROY))
            .subscribe(object : RespHandler<User>() {})
    }

    /**
     * 模型 传递
     */
    private fun login3() {
        val params = Login()
        params.username = "admin"
        params.password = "123456"

        LoginApiService.getInstance(mContext, "https://www.xxx.com/").login3(params)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .compose(this.bindUntilEvent(ActivityEvent.DESTROY))
            .subscribe(object : RespHandler<User>() {})
    }

}