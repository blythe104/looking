package com.looking.classicalparty.lib.utils;

import android.support.graphics.drawable.BuildConfig;
import android.util.Log;

/**
 * Created by xin on 2016/10/18.
 */
public class LogUtils {

    public static final String Tag = "classical--";

    public static void v(String tag, String message) {
        if (!BuildConfig.DEBUG) {
            Log.v(tag, message);
        }
    }

    public static void v(String message) {
        v(Tag, message);
    }

    public static void d(String tag, String message) {
        if (!BuildConfig.DEBUG) {
            Log.d(tag, message);
        }
    }

    public static void d(String message) {
        d(Tag, message);
    }

    public static void i(String tag, String message) {
        if (!BuildConfig.DEBUG) {
            Log.i(tag, message);
        }
    }

    public static void i(String message) {
        i(Tag, message);
    }

    public static void w(String tag, String message) {
        if (!BuildConfig.DEBUG) {
            Log.w(tag, message);
        }
    }

    public static void w(String message) {
        w(Tag, message);
    }

    public static void e(String tag, String message) {
        if (!BuildConfig.DEBUG) {
            Log.e(tag, message);
        }
    }

    public static void e(String message) {
        e(Tag, message);
    }

    public static void e(String tag, String message, Exception e) {
        if (!BuildConfig.DEBUG) {
            Log.e(tag, message, e);
        }
    }
}
