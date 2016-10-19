package com.looking.classicalparty;

import android.app.Application;

import com.looking.classicalparty.lib.utils.SharedPreUtils;

/**
 * Created by xin on 2016/10/18.
 */
public class ClassicalApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        SharedPreUtils.init(getApplicationContext());
    }
}
