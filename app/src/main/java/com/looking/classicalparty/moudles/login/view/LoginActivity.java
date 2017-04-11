package com.looking.classicalparty.moudles.login.view;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.looking.classicalparty.R;
import com.looking.classicalparty.lib.base.activity.BaseActivity;
import com.looking.classicalparty.lib.constants.ConstantApi;
import com.looking.classicalparty.lib.constants.StringContants;
import com.looking.classicalparty.lib.http.HttpUtils;
import com.looking.classicalparty.lib.http.Param;
import com.looking.classicalparty.lib.http.ResultCallback;
import com.looking.classicalparty.lib.utils.LogUtils;
import com.looking.classicalparty.lib.utils.SharedPreUtils;
import com.looking.classicalparty.moudles.login.bean.UserBean;
import com.looking.classicalparty.moudles.register.view.RegisterActivity;
import com.squareup.okhttp.Request;

import java.util.ArrayList;
import java.util.List;

import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;

public class LoginActivity extends BaseActivity {

    private static final int LSUCCESS = 2000;
    private EditText etUserName;
    private EditText etPassword;
    private LinearLayout btnLogin;
    private LinearLayout btnRegister;
    private FrameLayout mFrBack;
    private TextView mTvTitle;
    private TextView mTvTitleTag;

    @Override
    public void initView() {
        setContentView(R.layout.activity_login);
        etUserName = (EditText) findViewById(R.id.et_username);
        etPassword = (EditText) findViewById(R.id.et_password);
        btnLogin = (LinearLayout) findViewById(R.id.btn_login);
        btnRegister = (LinearLayout) findViewById(R.id.btn_register);
        mFrBack = (FrameLayout) findViewById(R.id.fr_back);
        mTvTitle = (TextView) findViewById(R.id.tv_title);
        mTvTitleTag = (TextView) findViewById(R.id.tv_title_tag);
        btnLogin.setOnClickListener(this);
        btnRegister.setOnClickListener(this);

    }

    @Override
    public void initListener() {
        mFrBack.setOnClickListener(this);
        mTvTitleTag.setOnClickListener(this);
    }

    @Override
    public void initData() {
        mTvTitle.setText("登录");
//        mTvTitleTag.setText("忘记密码？");
    }

    @Override
    public void processClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                login(etUserName.getText().toString(), etPassword.getText().toString());
                break;
            case R.id.btn_register:
                goRegister();
                break;
            case R.id.fr_back:
                setResult(4000);
                finish();
                break;
//            case R.id.tv_title_tag:
//                Crouton.makeText(this, "找回密码", Style.ALERT).show();
//                break;
        }

    }

    private void login(String username, String password) {
        List<Param> paramList = new ArrayList<>();
        Param userName = new Param("username", username);
        Param pwd = new Param("password", password);
        Param key = new Param("key", SharedPreUtils.getString(StringContants.KEY, ""));
        LogUtils.d("SharedPreUtils.getString(StringContants.KEY,)--" + key);
        paramList.add(userName);
        paramList.add(pwd);
        paramList.add(key);
        HttpUtils.post(ConstantApi.login, paramList, new ResultCallback() {
            @Override
            public void onSuccess(Object response) {
                UserBean userBean = new Gson().fromJson(response.toString(), UserBean.class);
                if (userBean.getResult() == 200) {
                    SharedPreUtils.saveString(StringContants.TOKEN, userBean.getToken());
                    Crouton.makeText(LoginActivity.this, "登录成功", Style.CONFIRM).show();
                    setResult(LSUCCESS);
                    finish();
                } else {
                    Crouton.makeText(LoginActivity.this, userBean.getResultMsg(), Style.CONFIRM).show();
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


    public void goRegister() {
        startActivity(new Intent(this, RegisterActivity.class));
    }

}
