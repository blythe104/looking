package com.looking.classicalparty.lib.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreUtils {
    //将可变的内容设置到一块，这这样可以更简便的更改
    public static final String SP_NAME = "classical_config";
    private static SharedPreferences sp;
    private static SharedPreferences.Editor editor;
    private Context context;

    //使用单例模式
    private SharedPreUtils() {
        sp = context.getSharedPreferences(SP_NAME, 0);
        editor = sp.edit();
    }

    //懒汉式
    private static void checkIsNull() {
        if (sp == null)
            new SharedPreUtils();
    }

    public static void saveString(Context context, String key, String value) {
        checkIsNull();
        editor.putString(key, value).commit();
    }

    public static String getString(Context context, String key, String defValue) {
        checkIsNull();
        return sp.getString(key, defValue);
    }


    public static void saveBoolean(Context context, String key, boolean value) {
        checkIsNull();
        editor.putBoolean(key, value).commit();
    }

    public static boolean getBoolean(Context context, String key, boolean defValue) {
        checkIsNull();
        return sp.getBoolean(key, defValue);
    }


    public static void saveInt(Context context, String key, int value) {
        checkIsNull();
        editor.putInt(key, value).commit();
    }

    public static int getInt(Context context, String key, int defValue) {
        checkIsNull();
        return sp.getInt(key, defValue);
    }
}
