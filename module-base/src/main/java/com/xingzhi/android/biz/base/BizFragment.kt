package com.xingzhi.android.biz.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.xingzhi.android.base.android.BaseFragment

/**
 * Created by xiedongdong on 2020/11/28
 */
abstract class BizFragment : BaseFragment(), IBizView {
    //private static final String TAG = "BizFragment";

    protected lateinit var mContext: Context
    protected lateinit var mContentView: View
    protected var isViewInitiated: Boolean = false
    protected var isVisibleToUser: Boolean = false
    protected var isDataInitiated: Boolean = false
    private var lazyLoadEnable: Boolean = false

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mContentView = inflater.inflate(layoutId, null)
        return mContentView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (!isViewInitiated) {
            initView(savedInstanceState, mContentView)
            initEvent()
        }
        isViewInitiated = true
        isLazyLoadData()
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        this.isVisibleToUser = userVisibleHint
        if (lazyLoadEnable) {
            isLazyLoadData()
        }
    }

    private fun isLazyLoadData() {
        if (lazyLoadEnable) {
            if (isVisibleToUser && isViewInitiated && !isDataInitiated) {
                isDataInitiated = true
                initData()
            }
        } else {
            isDataInitiated = true
            initData()
        }
    }

    /**
     * 开启懒加载，默认关闭，只针对initData()方法
     * 在initView()方法中设置
     */
    protected fun setSupportLazyLoad() {
        this.lazyLoadEnable = true
    }

    override fun onDestroyView() {
        (mContentView.parent as ViewGroup).removeView(mContentView)
        super.onDestroyView()
    }

    override fun onDetach() {
        super.onDetach()
    }

}

