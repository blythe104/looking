package com.looking.classicalparty.moudles.launch;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;
import com.looking.classicalparty.R;
import com.looking.classicalparty.lib.base.activity.BaseActivity;
import com.looking.classicalparty.lib.common.TipDialog;
import com.looking.classicalparty.lib.common.UpdateDialog;
import com.looking.classicalparty.lib.constants.ConstantApi;
import com.looking.classicalparty.lib.constants.StringContants;
import com.looking.classicalparty.lib.http.HttpUtils;
import com.looking.classicalparty.lib.http.Param;
import com.looking.classicalparty.lib.http.ResultCallback;
import com.looking.classicalparty.lib.utils.LogUtils;
import com.looking.classicalparty.lib.utils.PackageManagerUtils;
import com.looking.classicalparty.lib.utils.SharedPreUtils;
import com.looking.classicalparty.moudles.launch.bean.AppKeyBean;
import com.looking.classicalparty.moudles.main.MainActivity;
import com.looking.classicalparty.moudles.mine.bean.VersionBean;
import com.looking.classicalparty.moudles.mine.bean.VersionResponse;
import com.squareup.okhttp.Request;

import java.util.ArrayList;
import java.util.List;

public class LaunchActivity extends BaseActivity {
    
    
    private static final String TAG = "SplashActivity";
    
    private static final int GO_MAIN = 100;
    private static final int GO_GUIDE = 200;
    private static final int GO_UPDATE = 300;
    
    // 延迟3秒
    private static final long SPLASH_DELAY_MILLIS = 3000;
    private TipDialog tipDialog;
    private VersionBean mVersion;
    private Button inApp;
    private boolean isDownload;
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
    
    /**
     * 获取AppKey
     */
    public void getAppKey() {
        List<Param> paramList = new ArrayList<>();
        Param key = new Param("", "");
        paramList.add(key);
        HttpUtils.post(ConstantApi.getkey, paramList, new ResultCallback() {
            @Override
            public void onSuccess(Object response) {
                LogUtils.d("appkey", response.toString());
                AppKeyBean keyBean = new Gson().fromJson(response.toString(), AppKeyBean.class);
                if (keyBean.getResult() == 200) {
                    SharedPreUtils.saveString(StringContants.KEY, keyBean.getKey());
                }
            }
            
            @Override
            public void onFailure(Request request, Exception e) {
                
            }
            
            @Override
            public void onNoNetWork(String resultMsg) {
                
            }
        });
    }
    
    public void goGuide() {
        
    }
    
    /**
     * 判断弹出框是否出现
     */
    private void isHasTips() {
        if (TextUtils.isEmpty(SharedPreUtils.getString(StringContants.KEY, ""))) {
            tipDialog = new TipDialog(this);
            tipDialog.setTipsListener(new TipDialog.tipsListener() {
                @Override
                public void getAppData() {
                    getAppKey();
                }
            });
            tipDialog.show();
        }
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
        inApp = (Button) findViewById(R.id.inapp);
    }
    
    @Override
    public void initListener() {
        inApp.setOnClickListener(this);
    }
    
    
    @Override
    public void initData() {
        getAppKey();
        getAppVersion();
        //判断是否有key
        //isHasTips();
        
    }
    
    @Override
    public void processClick(View v) {
        switch (v.getId()) {
            case R.id.inapp:
                mHandler.sendEmptyMessage(GO_MAIN);
                break;
        }
        
    }
    
    /**
     * 获取AppVersion
     */
    private void getAppVersion() {
        //跳转到主界面
        List<Param> paramList = new ArrayList<>();
        Param key = new Param("key", SharedPreUtils.getString(StringContants.KEY));
        Param apptype = new Param("apptype", "android");
        paramList.add(key);
        paramList.add(apptype);
        HttpUtils.post(ConstantApi.VERSION, paramList, new ResultCallback() {
            @Override
            public void onSuccess(Object response) {
                VersionResponse versionResponse = new Gson().fromJson(response.toString(), VersionResponse.class);
                Log.d("version---", versionResponse.toString());
                if (200 == versionResponse.getResult()) {
                    mVersion = versionResponse.getVersion();
                    if (mVersion == null) {
                        //版本信息为空
                    } else {
                        //判断版本信息  版本高的话弹窗提示是否更新
                        if (PackageManagerUtils.getVersionCode(LaunchActivity.this) < Double.parseDouble(mVersion
                                .getVersionCode())) {
                            
                            Toast.makeText(LaunchActivity.this, "更新", Toast.LENGTH_SHORT).show();
                            mHandler.sendEmptyMessage(GO_UPDATE);
                        }
                    }
                }
            }
            
            @Override
            public void onFailure(Request request, Exception e) {
                
            }
            
            @Override
            public void onNoNetWork(String resultMsg) {
                
            }
        });
        
    }
    
    
    /**
     * 显示更新对话框
     */
    protected void showUpdateDialog() {
        UpdateDialog updateDialog = new UpdateDialog(this);
        updateDialog.setCanceledOnTouchOutside(false);
        updateDialog.setUpdateListener(new UpdateDialog.UpdateListener() {
            @Override
            public void update() {
                downloadApk();
                isDownload = true;
                updateDialog.dismiss();
            }
            
            @Override
            public void closeDialog() {
                finish();
                
            }
        });
        updateDialog.show();
        
    }
    
    private void downloadApk() {
        
        
    }
}
