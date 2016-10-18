package com.looking.classicalparty.lib.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by xin on 2016/9/28.
 */
public class ToastUtils {
    private static Toast toast;

    /**
     * 判断弹出时对象是否为空
     *
     * @param context
     * @param content
     */
    public static void showToast(Context context, String content) {
        if (toast == null) {
            toast = Toast.makeText(context, content, Toast.LENGTH_SHORT);
        } else {
            toast.setText(content);
        }
        toast.show();
    }
}
