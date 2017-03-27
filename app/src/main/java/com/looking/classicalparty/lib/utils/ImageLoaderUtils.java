package com.looking.classicalparty.lib.utils;

import android.content.Context;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;


public class ImageLoaderUtils {

    /**
     * 使用Glide加载图片
     *
     * @param context 上下文
     * @param url     图片链接
     * @param view    填充位置
     */
    public static void display(Context context, String url, ImageView view) {
        Glide.with(context).load(url).centerCrop().into(view);
    }

    /**
     * 使用Glide加载图片
     *
     * @param context      上下文
     * @param url          图片链接
     * @param view         填充位置
     * @param defaultImage 默认图片
     */
    public static void display(Context context, String url, int defaultImage, ImageView view) {
        if (TextUtils.isEmpty(url)) {
            Glide.with(context).load(url).centerCrop().placeholder(defaultImage).crossFade().into(view);
        } else {
            display(context, url, view);
        }
    }

    /**
     * 使用Glide加载图片
     *
     * @param context    上下文
     * @param url        图片链接
     * @param replaceImg 替换图片
     * @param defaultImg 默认图片
     * @param view       填充位置
     */
    public static void display(Context context, String url, int replaceImg, int defaultImg, ImageView view) {
        Glide.with(context).load(url).placeholder(replaceImg).error(defaultImg).centerCrop().into(view);
    }

    public static void display(Context context, int imageId, ImageView view) {
        Glide.with(context).load(imageId).centerCrop().into(view);
    }

    public static void url2Bitmap(Context context, String url) {
        Glide.with(context).load(url);
    }


}
