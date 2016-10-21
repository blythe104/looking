package com.looking.classicalparty.moudles.launch;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.View;

import com.looking.classicalparty.R;
import com.looking.classicalparty.lib.base.activity.BaseActivity;
import com.looking.classicalparty.moudles.main.MainActivity;

public class LaunchActivity extends BaseActivity {


    private static final String TAG = "SplashActivity";

    private static final int GO_MAIN = 100;
    private static final int GO_GUIDE = 200;
    private static final int GO_UPDATE = 300;

    // 延迟3秒
    private static final long SPLASH_DELAY_MILLIS = 3000;


    private Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case GO_MAIN:
                    goMain();
                    break;
                case GO_GUIDE:
                    goGuide();
                    break;
                case GO_UPDATE:
                    showUpdateDialog();
                    break;
            }
        }
    };

    public void goGuide() {

    }


    /**
     * 跳转到主页面
     */
    private void goMain() {
        startActivity(new Intent(this, MainActivity.class));


    }

    @Override
    public void initView() {
        setContentView(R.layout.activity_launch);
    }

    @Override
    public void initListener() {

    }


    @Override
    public void initData() {
        getAppVersion();
    }

    @Override
    public void processClick(View v) {

    }

    /**
     * 获取AppVersion
     */
    private void getAppVersion() {
        //先跳轉到主界面
        mHandler.sendEmptyMessage(GO_MAIN);
    }


    /**
     * 显示更新对话框
     */
    protected void showUpdateDialog() {

    }

    private void downloadApk() {

    }
}
