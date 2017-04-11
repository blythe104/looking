package com.looking.classicalparty.lib.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

public class PackageManagerUtils {

    /**
     * 获取版本名称
     *
     * @param ctx
     * @return
     */
    public static String getVersionName(Context ctx) {

        PackageManager pm = ctx.getPackageManager();
        try {
            PackageInfo info = pm.getPackageInfo(ctx.getPackageName(), 0);
            String name = info.versionName;
            return name;
        } catch (android.content.pm.PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 获取版本号
     *
     * @param ctx
     * @return
     */
    public static int getVersionCode(Context ctx) {
        PackageManager pm = ctx.getPackageManager();
        try {
            PackageInfo info = pm.getPackageInfo(ctx.getPackageName(), 0);
            int code = info.versionCode;
            return code;
        } catch (android.content.pm.PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return -1;
    }
}
