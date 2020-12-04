package com.xingzhi.android.biz.utils;

/**
 * Created by xiedongdong on 2018/10/11
 */
public class ClickUtils {

    private static final int MIN_DELAY_TIME = 800;  // 两次点击间隔不能少于800ms
    private static int CUSTOM_MIN_DELAY_TIME = 2000; // 自定义点击时间
    private static long lastClickTime;

    public static boolean isFastClick() {
        boolean flag = true;
        long currentClickTime = System.currentTimeMillis();
        if ((currentClickTime - lastClickTime) >= MIN_DELAY_TIME) {
            flag = false;
        }
        lastClickTime = currentClickTime;
        return flag;
    }

    public static boolean isFastClick(int delayTime) {
        CUSTOM_MIN_DELAY_TIME = delayTime;
        boolean flag = true;
        long currentClickTime = System.currentTimeMillis();
        if ((currentClickTime - lastClickTime) >= CUSTOM_MIN_DELAY_TIME) {
            flag = false;
        }
        lastClickTime = currentClickTime;
        return flag;
    }

}
