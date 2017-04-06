package com.looking.classicalparty.lib.utils;

import android.app.Application;
import android.content.SharedPreferences;

import com.looking.classicalparty.R;

public class SharedPreUtils {
    //将可变的内容设置到一块，这这样可以更简便的更改
    public static final String SP_NAME = "classical_config";
    private static SharedPreferences sp;
    private static SharedPreferences.Editor editor;
    private static Application context;

    public static void init(Application application) {
        context = application;
        checkIsNull();
    }


    //懒汉式
    private static void checkIsNull() {
        if (sp == null) {
            sp = context.getSharedPreferences(SP_NAME, 0);
            editor = sp.edit();
        }
    }
    public static boolean enableMobileNetworkPlay() {
        return getBoolean(context.getString(R.string.setting_key_mobile_network_play), false);
    }

    public static void saveMobileNetworkPlay(boolean enable) {
        saveBoolean(context.getString(R.string.setting_key_mobile_network_play), enable);
    }


    public static void saveString(String key, String value) {
        editor.putString(key, value).commit();
    }

    public static String getString(String key, String defValue) {
        return sp.getString(key, defValue);
    }

    public static String getString(String key) {
        return getString(key, "");
    }


    public static void saveBoolean(String key, boolean value) {
        editor.putBoolean(key, value).commit();
    }

    public static boolean getBoolean(String key, boolean defValue) {
        return sp.getBoolean(key, defValue);
    }

    public static boolean getBoolean(String key) {
        return getBoolean(key, false);
    }

    public static void saveInt(String key, int value) {
        editor.putInt(key, value).commit();
    }

    public static int getInt(String key, int defValue) {
        return sp.getInt(key, defValue);
    }

    public static int getInt(String key) {
        return getInt(key, 0);
    }
}
