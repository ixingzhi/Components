package com.xingzhi.android.biz.utils;

import android.content.Context;
import android.util.DisplayMetrics;

/**
 * Created by xiedongdong on 2019/01/08.
 */
public class UIUtils {

    public static int dp2px(Context context, final float dpValue) {
        final float scale = getDisplayMetrics(context).density;
        return (int) (dpValue * scale + 0.5f);
    }

    public static int px2dp(Context context, final float pxValue) {
        final float scale = getDisplayMetrics(context).density;
        return (int) (pxValue / scale + 0.5f);
    }

    public static int sp2px(Context context, final float spValue) {
        final float fontScale = getDisplayMetrics(context).scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    public static int px2sp(Context context, final float pxValue) {
        final float fontScale = getDisplayMetrics(context).scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    public static DisplayMetrics getDisplayMetrics(Context context) {
        return context.getResources().getDisplayMetrics();
    }

}
