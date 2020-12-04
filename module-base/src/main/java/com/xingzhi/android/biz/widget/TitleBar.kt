package com.xingzhi.android.biz.widget

import android.app.Activity
import android.content.Context
import android.graphics.drawable.Drawable
import android.text.TextUtils
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.view.View.OnClickListener
import android.widget.ImageButton
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import com.xingzhi.android.R
import com.xingzhi.android.biz.utils.ClickUtils
import com.xingzhi.android.biz.utils.KeyBoardUtils
import com.xingzhi.android.biz.utils.UIUtils

/**
 * Created by xiedongdong on 2019/3/28
 */
class TitleBar @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
        RelativeLayout(context, attrs, defStyleAttr) {

    companion object {
        private const val DEFAULT_HEIGHT = 40
    }

    private var mIbBack: ImageButton? = null
    private var mTvBack: TextView? = null
    private var mTvTitle: TextView? = null
    private var mIbMenu: ImageButton? = null
    private var mTvMenu: TextView? = null

    private var backImageRes: Drawable? = null
    private var backText: String? = null
    private var backTextColor = 0
    private var titleText: String? = null
    private var titleTextColor = 0
    private var menuImageRes: Drawable? = null
    private var menuText: String? = null
    private var menuTextColor = 0
    private var cBackgroundColor = 0
    private var dividerVisibility = 0
    private var backVisibility = 0

    private var mOnBackClickListener: (() -> Unit)? = null
    private var mOnTitleClickListener: (() -> Unit)? = null
    private var mOnMenuClickListener: (() -> Unit)? = null

    @get:Throws(Exception::class)
    private val activity: Activity
        get() {
            if (context is Activity) {
                return context as Activity
            }
            throw Exception("Unable to get Activity")
        }

    private val mBackClickListener = OnClickListener {
        if (mOnBackClickListener != null && !ClickUtils.isFastClick()) {
            mOnBackClickListener?.invoke()
        } else {
            try {
                val view = activity.window.peekDecorView()
                if (KeyBoardUtils.isShowKeyboard(activity, view)) {
                    KeyBoardUtils.hideSoftKeyboard(activity, view)
                }
                activity.finish()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private val mMenuClickListener = OnClickListener {
        if (mOnMenuClickListener != null && !ClickUtils.isFastClick()) {
            try {
                val view = activity.window.peekDecorView()
                if (KeyBoardUtils.isShowKeyboard(activity, view)) {
                    KeyBoardUtils.hideSoftKeyboard(activity, view)
                }
                mOnMenuClickListener?.invoke()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    init {
        // 界面默认焦点为顶部，部分手机页面存在EditText，会弹出键盘
        isFocusable = true
        isFocusableInTouchMode = true
        initAttrs(attrs)
        initView()
    }

    private fun initAttrs(attrs: AttributeSet?) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.TitleBar)
        backImageRes = typedArray.getDrawable(R.styleable.TitleBar_tbBackImage)
        backText = typedArray.getString(R.styleable.TitleBar_tbBackText)
        backTextColor = typedArray.getColor(R.styleable.TitleBar_tbBackTextColor, ContextCompat.getColor(context, R.color.color_base_text1))
        titleText = typedArray.getString(R.styleable.TitleBar_tbTitleText)
        titleTextColor = typedArray.getColor(R.styleable.TitleBar_tbTitleTextColor, ContextCompat.getColor(context, R.color.color_base_text1))
        menuImageRes = typedArray.getDrawable(R.styleable.TitleBar_tbMenuImage)
        menuText = typedArray.getString(R.styleable.TitleBar_tbMenuText)
        menuTextColor = typedArray.getColor(R.styleable.TitleBar_tbMenuTextColor, ContextCompat.getColor(context, R.color.color_base_text1))
        cBackgroundColor = typedArray.getColor(R.styleable.TitleBar_tbBackgroundColor, ContextCompat.getColor(context, R.color.color_base_bg6))
        dividerVisibility = typedArray.getInt(R.styleable.TitleBar_tbDividerVisibility, 1)
        backVisibility = typedArray.getInt(R.styleable.TitleBar_tbBackVisibility, 1)
        typedArray.recycle()
    }

    private fun initView() {
        initBackView()
        initTitleView()
        initMenuView()
        setBackgroundColor(cBackgroundColor)
        if (dividerVisibility == 1) {
            elevation = UIUtils.dp2px(context, 0.7f).toFloat()
        }
    }

    /**
     * 返回
     */
    private fun initBackView() {
        val backImageParams = LayoutParams(LayoutParams.WRAP_CONTENT, UIUtils.dp2px(context, DEFAULT_HEIGHT.toFloat()))
        backImageParams.addRule(ALIGN_PARENT_START)
        backImageParams.addRule(CENTER_VERTICAL)
        mIbBack = ImageButton(context)
        if (backImageRes == null) {
            backImageRes = ContextCompat.getDrawable(context, R.drawable.biz_selector_ic_title_bar_back)
            mIbBack?.setImageDrawable(backImageRes)
        }
        mIbBack?.background = null
        setSelectableItemBackground(mIbBack)
        val padding = UIUtils.dp2px(context, 16f)
        mIbBack?.setPadding(padding, 0, padding, 0)
        mIbBack?.layoutParams = backImageParams
        mIbBack?.setOnClickListener(mBackClickListener)
        addView(mIbBack)
        val backTextParams = LayoutParams(LayoutParams.WRAP_CONTENT, UIUtils.dp2px(context, DEFAULT_HEIGHT.toFloat()))
        backTextParams.addRule(ALIGN_PARENT_START)
        backTextParams.addRule(CENTER_VERTICAL)
        mTvBack = TextView(context)
        mTvBack?.gravity = Gravity.CENTER
        mTvBack?.maxWidth = UIUtils.dp2px(context, 100f)
        //mTvBack.setMinWidth(UIUtils.dp2px(context, 64));
        mTvBack?.textSize = 15f
        mTvBack?.maxLines = 1
        mTvBack?.ellipsize = TextUtils.TruncateAt.END
        mTvBack?.visibility = View.GONE
        mTvBack?.layoutParams = backTextParams
        mTvBack?.setPadding(padding, 0, padding, 0)
        setSelectableItemBackground(mTvBack)
        mTvBack?.setOnClickListener(mBackClickListener)
        addView(mTvBack)
        if (backImageRes != null) {
            setBackImageDrawable(backImageRes)
        } else if (!TextUtils.isEmpty(backText)) {
            setBackText(backText)
        }

        if (backVisibility == 0 || (backImageRes == null && TextUtils.isEmpty(backText))) {
            setBackVisibility(View.GONE)
        }
    }

    fun setBackImageDrawable(drawable: Drawable?) {
        if (drawable == null) {
            setBackVisibility(View.GONE)
            return
        }
        backImageRes = drawable
        mTvBack?.visibility = View.GONE
        mIbBack?.setImageDrawable(drawable)
        mIbBack?.visibility = View.VISIBLE
    }

    fun setBackImageResource(@DrawableRes resId: Int) {
        val drawable = ContextCompat.getDrawable(context, resId)
        setBackImageDrawable(drawable)
    }

    fun setBackText(text: String?) {
        if (TextUtils.isEmpty(text)) {
            setBackVisibility(View.GONE)
            return
        }
        backText = text
        setBackTextColor(backTextColor)
        mIbBack?.visibility = View.GONE
        mTvBack?.text = backText
        mTvBack?.visibility = View.VISIBLE
    }

    fun setBackText(@StringRes resId: Int) {
        val text = context.resources.getString(resId)
        setBackText(text)
    }

    fun setBackTextColor(@ColorInt resId: Int) {
        backTextColor = resId
        mTvBack?.setTextColor(backTextColor)
    }

    fun setBackVisibility(visibility: Int) {
        mTvBack?.visibility = visibility
        mIbBack?.visibility = visibility
    }

    /**
     * Title
     */
    private fun initTitleView() {
        val titleTextParams = LayoutParams(LayoutParams.WRAP_CONTENT, UIUtils.dp2px(context, DEFAULT_HEIGHT.toFloat()))
        titleTextParams.addRule(CENTER_IN_PARENT)
        mTvTitle = TextView(context)
        mTvTitle?.gravity = Gravity.CENTER
        mTvTitle?.textSize = 18f
        mTvTitle?.maxWidth = UIUtils.dp2px(context, 240f)
        mTvTitle?.maxLines = 1
        mTvTitle?.ellipsize = TextUtils.TruncateAt.END
        mTvTitle?.layoutParams = titleTextParams
        mTvTitle?.setOnClickListener {
            if (mOnTitleClickListener != null) {
                mOnTitleClickListener?.invoke()
            }
        }
        addView(mTvTitle)
        setTitleText(titleText)
        setTitleTextColor(titleTextColor)
    }

    fun setTitleText(text: String?) {
        titleText = text
        mTvTitle?.text = titleText
    }

    fun setTitleText(@StringRes resId: Int) {
        val text = context.resources.getString(resId)
        setTitleText(text)
    }

    fun setTitleTextColor(@ColorInt resId: Int) {
        titleTextColor = resId
        mTvTitle?.setTextColor(titleTextColor)
    }

    /**
     * Menu
     */
    private fun initMenuView() {
        val menuImageParams = LayoutParams(LayoutParams.WRAP_CONTENT, UIUtils.dp2px(context, DEFAULT_HEIGHT.toFloat()))
        menuImageParams.addRule(CENTER_VERTICAL)
        menuImageParams.addRule(ALIGN_PARENT_END)
        mIbMenu = ImageButton(context)
        if (menuImageRes != null) {
            mIbMenu?.setImageDrawable(menuImageRes)
        }
        mIbMenu?.background = null
        setSelectableItemBackground(mIbMenu)
        val padding = UIUtils.dp2px(context, 16f)
        mIbMenu?.setPadding(padding, 0, padding, 0)
        mIbMenu?.setOnClickListener(mMenuClickListener)
        mIbMenu?.layoutParams = menuImageParams
        addView(mIbMenu)
        val menuTextParams = LayoutParams(LayoutParams.WRAP_CONTENT, UIUtils.dp2px(context, DEFAULT_HEIGHT.toFloat()))
        menuTextParams.addRule(CENTER_VERTICAL)
        menuTextParams.addRule(ALIGN_PARENT_END)
        mTvMenu = TextView(context)
        mTvMenu?.gravity = Gravity.CENTER
        mTvMenu?.maxWidth = UIUtils.dp2px(context, 100f)
        //mTvMenu.setMinWidth(UIUtils.dp2px(context, 64));
        mTvMenu?.textSize = 15f
        mTvMenu?.maxLines = 1
        mTvMenu?.ellipsize = TextUtils.TruncateAt.END
        mTvMenu?.visibility = View.GONE
        mTvMenu?.layoutParams = menuTextParams
        mTvMenu?.setPadding(padding, 0, padding, 0)
        setSelectableItemBackground(mTvMenu)
        mTvMenu?.setOnClickListener(mMenuClickListener)
        addView(mTvMenu)
        if (menuImageRes != null) {
            setMenuImageDrawable(menuImageRes)
        } else if (!TextUtils.isEmpty(menuText)) {
            setMenuText(menuText)
        }

        if (menuImageRes == null && TextUtils.isEmpty(menuText)) {
            setMenuVisibility(View.GONE)
        }
    }

    fun setMenuImageDrawable(drawable: Drawable?) {
        if (drawable == null) {
            setMenuVisibility(View.GONE)
            return
        }
        menuImageRes = drawable
        mTvMenu?.visibility = View.GONE
        mIbMenu?.setImageDrawable(drawable)
        mIbMenu?.visibility = View.VISIBLE
    }

    fun setMenuImageResource(@DrawableRes resId: Int) {
        val drawable = ContextCompat.getDrawable(context, resId)
        setMenuImageDrawable(drawable)
    }

    fun setMenuText(text: String?) {
        if (TextUtils.isEmpty(text)) {
            setMenuVisibility(View.GONE)
            return
        }
        menuText = text
        setMenuTextColor(menuTextColor)
        mIbMenu?.visibility = View.GONE
        mTvMenu?.text = menuText
        mTvMenu?.visibility = View.VISIBLE
    }

    fun setMenuText(@StringRes resId: Int) {
        val text = context.resources.getString(resId)
        setMenuText(text)
    }

    fun setMenuTextColor(@ColorInt resId: Int) {
        menuTextColor = resId
        mTvMenu?.setTextColor(menuTextColor)
    }

    fun setMenuEnabled(bool: Boolean) {
        mTvMenu?.isEnabled = bool
        mIbMenu?.isEnabled = bool
    }

    fun setMenuVisibility(visibility: Int) {
        mTvMenu?.visibility = visibility
        mIbMenu?.visibility = visibility
    }

    val menuImageView: View
        get() = mIbMenu!!

    val menuTextView: View
        get() = mTvMenu!!

    fun setOnBackClickListener(listener: (() -> Unit)?) {
        mOnBackClickListener = listener
    }

    fun setOnTitleClickListener(listener: (() -> Unit)?) {
        mOnTitleClickListener = listener
    }

    fun setOnMenuClickListener(listener: (() -> Unit)?) {
        mOnMenuClickListener = listener
    }

    private fun setSelectableItemBackground(view: View?) {
        try {
            val typedValue = TypedValue()
            context.theme
                    .resolveAttribute(android.R.attr.selectableItemBackgroundBorderless, typedValue, true)
            val attribute = intArrayOf(android.R.attr.selectableItemBackgroundBorderless)
            val typedArray = context.theme.obtainStyledAttributes(typedValue.resourceId, attribute)
            view?.background = typedArray.getDrawable(0)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}