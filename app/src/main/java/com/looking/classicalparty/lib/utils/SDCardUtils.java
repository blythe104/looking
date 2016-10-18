package com.looking.classicalparty.lib.utils;

import android.os.Environment;
import android.os.StatFs;

import java.io.File;

/**
 * Created by xin on 2016/10/18.
 */
public class SDCardUtils {
    /**
     * 判断SD卡是否挂载
     *
     * @return
     */
    public static boolean isSDCardEnable() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }

    /**
     * 获取SD卡的路径
     *
     * @return
     */
    public static String getSDCardPath() {
        return Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator;
    }

    /**
     * 获取SD卡总空间
     *
     * @return
     */
    public static long getSDCardAllSize() {
        if (isSDCardEnable()) {
            StatFs stat = new StatFs(getSDCardPath());
            long size = stat.getBlockSize();
            long count = stat.getBlockCount();
            return size * count;
        }
        return 0;
    }

    /**
     * 获取SD卡剩余空间
     *
     * @return
     */
    public static long getSDCardAvailableSize() {
        if (isSDCardEnable()) {
            StatFs stat = new StatFs(getSDCardPath());
            long size = stat.getBlockSize();
            long count = stat.getAvailableBlocks();
            return size * count;
        }
        return 0;
    }

    /**
     * 获取系统存储路径
     *
     * @return
     */
    public static String getRootDirectoryPath() {
        return Environment.getRootDirectory().getAbsolutePath();
    }

}
