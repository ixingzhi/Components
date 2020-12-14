package com.xingzhi.android.biz.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * Created by xiedongdong on 2019/01/03.
 */
public class SharedPreferencesUtils {

    private static final String SCHEME = "SharedPreferences";

    public static boolean saveLong(Context context, String key, long val) {
        if (null == context) {
            return false;
        }
        SharedPreferences sp = context.getSharedPreferences(SCHEME, 0);
        Editor editor = sp.edit();
        editor.putLong(key, val);
        editor.apply();
        return true;
    }

    public static long getLong(Context context, String key, long defValue) {
        if (null == context) {
            return defValue;
        }
        SharedPreferences sp = context.getSharedPreferences(SCHEME, 0);
        return sp.getLong(key, defValue);
    }

    public static boolean saveInt(Context context, String key, int val) {
        if (null == context) {
            return false;
        }
        SharedPreferences sp = context.getSharedPreferences(SCHEME, 0);
        Editor editor = sp.edit();
        editor.putInt(key, val);
        editor.apply();
        return true;
    }

    public static int getInt(Context context, String key, int defValue) {
        if (null == context) {
            return defValue;
        }
        SharedPreferences sp = context.getSharedPreferences(SCHEME, 0);
        return sp.getInt(key, defValue);
    }

    public static boolean saveBoolean(Context context, String key, boolean value) {
        if (null == context) {
            return false;
        }
        SharedPreferences sp = context.getSharedPreferences(SCHEME, 0);
        Editor editor = sp.edit();
        editor.putBoolean(key, value);
        editor.apply();
        return true;
    }

    public static boolean getBoolean(Context context, String key, boolean defValue) {
        if (null == context) {
            return defValue;
        }
        SharedPreferences sp = context.getSharedPreferences(SCHEME, 0);
        return sp.getBoolean(key, defValue);
    }

    public static boolean saveString(Context context, String key, String value) {
        if (null == context) {
            return false;
        }
        SharedPreferences sp = context.getSharedPreferences(SCHEME, 0);
        Editor editor = sp.edit();
        editor.putString(key, value);
        editor.apply();
        return true;
    }

    public static String getString(Context context, String key, String defVal) {
        if (null == context) {
            return defVal;
        }
        SharedPreferences sp = context.getSharedPreferences(SCHEME, 0);
        return sp.getString(key, defVal);
    }

    public static boolean remove(Context context, String key) {
        if (null == context) {
            return false;
        }
        SharedPreferences sp = context.getSharedPreferences(SCHEME, 0);
        Editor editor = sp.edit();
        editor.remove(key);
        return editor.commit();
    }

    public static void clear(Context context) {
        if (null == context) {
            return;
        }
        SharedPreferences sp = context.getSharedPreferences(SCHEME, 0);
        Editor editor = sp.edit();
        editor.clear();
        editor.apply();
    }
}
