package com.xingzhi.android.biz.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import com.xingzhi.android.R
import com.xingzhi.android.biz.utils.UIUtils
import java.util.*

/**
 * Created by xiedongdong on 2019/01/08.
 */
class RedDotTips @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
        AppCompatTextView(context, attrs, defStyleAttr) {

    companion object {
        const val ONLY_SHOW_TIPS = -1
    }

    private val mPaint: Paint
    private var radius: Float

    init {
        visibility = View.INVISIBLE
        setTextColor(Color.WHITE)
        gravity = Gravity.CENTER
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.RedDotTips)
        val textSizePxDefault = UIUtils.sp2px(context, 11f)
        val textSizePx = typedArray.getDimensionPixelSize(R.styleable.RedDotTips_rdtTextSize, textSizePxDefault)
        typedArray.recycle()
        setTextSize(TypedValue.COMPLEX_UNIT_PX, textSizePx.toFloat())
        mPaint = Paint()
        mPaint.isAntiAlias = true
        mPaint.style = Paint.Style.FILL
        mPaint.color = ContextCompat.getColor(context, R.color.color_f33_red)
        radius = UIUtils.dp2px(getContext(), 10f).toFloat()
    }

    override fun onDraw(canvas: Canvas) {
        val width = measuredWidth
        val height = measuredHeight
        radius = width.coerceAtMost(height).toFloat() / 2
        canvas.drawCircle(width.toFloat() / 2, height.toFloat() / 2, radius, mPaint)
        super.onDraw(canvas)
    }

    fun update(num: Int) {
        if (num > 0) {
            text = if (num > 99) {
                String.format(Locale.CHINA, "%d+", 99)
            } else {
                num.toString()
            }
            visibility = View.VISIBLE
        } else {
            if (num == ONLY_SHOW_TIPS) {
                text = ""
                visibility = View.VISIBLE
            } else {
                visibility = View.INVISIBLE
            }
        }
        requestLayout()
    }

}