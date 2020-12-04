package com.xingzhi.android.biz.utils;


import org.greenrobot.eventbus.EventBus;

public class EventBusUtils {

    public static void register(Object subscriber) {
        try {
            EventBus.getDefault().register(subscriber);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void registerSticky(Object subscriber) {
        try {
            EventBus.getDefault().removeStickyEvent(subscriber);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void unregister(Object subscriber) {
        try {
            EventBus.getDefault().unregister(subscriber);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean isRegistered(Object subscriber) {
        return EventBus.getDefault().isRegistered(subscriber);
    }

    public static void post(Object event) {
        try {
            EventBus.getDefault().post(event);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void postSticky(Object event) {
        try {
            EventBus.getDefault().postSticky(event);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void removeSticky(Object event) {
        try {
            EventBus.getDefault().removeStickyEvent(event);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
