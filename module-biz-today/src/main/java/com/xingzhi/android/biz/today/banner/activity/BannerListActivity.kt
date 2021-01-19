package com.xingzhi.android.biz.today.banner.activity

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.trello.rxlifecycle3.android.ActivityEvent
import com.xingzhi.android.base.http.RespHandler
import com.xingzhi.android.base.http.exception.RespException
import com.xingzhi.android.biz.base.BizActivity
import com.xingzhi.android.biz.today.R
import com.xingzhi.android.biz.today.banner.adapter.BannerListAdapter
import com.xingzhi.android.biz.today.banner.api.BannerApiService
import com.xingzhi.android.biz.today.banner.model.Banner
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.biz_today_activity_banner_list.*


/**
 * Created by xiedongdong on 2021/01/18
 */
class BannerListActivity : BizActivity() {

    private var mAdBannerList: BannerListAdapter? = null

    override val layoutId: Int
        get() = R.layout.biz_today_activity_banner_list

    override fun initView(savedInstanceState: Bundle?, view: View?) {
        mRvBannerList.apply {
            layoutManager = LinearLayoutManager(mContext)
            mAdBannerList =
                BannerListAdapter()
            adapter = mAdBannerList
        }
    }

    override fun initEvent() {
        mSwipeRefreshLayout.setOnRefreshListener { getBannerListData() }
    }

    override fun initData() {
        mSwipeRefreshLayout.post { getBannerListData() }
    }

    private fun getBannerListData() {
        BannerApiService.getInstance(mContext).getBannerList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .compose(this.bindUntilEvent(ActivityEvent.DESTROY))
            .subscribe(object : RespHandler<List<Banner>>() {

                override fun onSuccess(data: List<Banner>) {
                    mAdBannerList?.setList(data)
                }

                override fun onFailure(e: RespException) {
                }

                override fun onFinish() {
                    mSwipeRefreshLayout.isRefreshing = false
                }

            })
    }

}