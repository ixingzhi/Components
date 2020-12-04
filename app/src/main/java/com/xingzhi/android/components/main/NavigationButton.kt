package com.xingzhi.android.components.main

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import com.xingzhi.android.components.R
import kotlinx.android.synthetic.main.layout_nav_item.view.*

/**
 * Created by xiedongdong on 2018/11/09.
 */
class NavigationButton @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0, defStyleRes: Int = 0
) : FrameLayout(context, attrs, defStyleAttr, defStyleRes) {

    private var mFragment: Fragment? = null
    private val mClx: Class<*>? = null
    private var mTag: Any? = null

    init {
        LayoutInflater.from(context).inflate(R.layout.layout_nav_item, this, true)
    }

    override fun setSelected(selected: Boolean) {
        super.setSelected(selected)
        mNavIcon.isSelected = selected
        mNavTitle.isSelected = selected
    }

    fun showRedDot(count: Int) {
        mNavDotTips.visibility = if (count > 0) View.VISIBLE else View.GONE
        mNavDotTips.text = count.toString()
    }

    fun init(@DrawableRes resId: Int, @StringRes strId: Int, fragmentList: List<Fragment>, tag: Int) {
        mNavIcon.setImageResource(resId)
        mNavTitle.setText(strId)
        mFragment = fragmentList[tag]
        mTag = tag
    }

    val clx: Class<*>?
        get() = mFragment?.javaClass

    override fun getTag(): Any? {
        return mTag
    }

    val fragment: Fragment?
        get() = mFragment

}