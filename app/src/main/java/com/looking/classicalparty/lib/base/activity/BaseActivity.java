package com.looking.classicalparty.lib.base.activity;

/**
 * Created by xin on 2016/10/18.
 */

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;
import android.view.View;

/**
 * 基类
 */
public abstract class BaseActivity extends FragmentActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView();
        initListener();
        initData();
    }

    public abstract void initView();

    public abstract void initListener();

    public abstract void initData();

    public abstract void processClick(View v);

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        processClick(v);
    }



    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //监听回退键
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                finish();
                break;
        }
        return super.onKeyDown(keyCode, event);
    }
}

