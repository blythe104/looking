package com.looking.classicalparty.moudles.login.view;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.looking.classicalparty.R;
import com.looking.classicalparty.lib.base.activity.BaseActivity;
import com.looking.classicalparty.moudles.login.presenter.IUserPresenter;
import com.looking.classicalparty.moudles.login.presenter.UserPresenterImpl;
import com.looking.classicalparty.moudles.register.view.RegisterActivity;

import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;

public class LoginActivity extends BaseActivity implements IUserView {

    private EditText etUserName;
    private EditText etPassword;
    private LinearLayout btnLogin;
    private LinearLayout btnRegister;
    private IUserPresenter userPresenter;
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
        userPresenter = new UserPresenterImpl(this, this);
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
        mTvTitleTag.setText("忘记密码？");
    }

    @Override
    public void processClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                userPresenter.login(etUserName.getText().toString(), etPassword.getText().toString());
                break;
            case R.id.btn_register:
                userPresenter.toRegister();
                break;
            case R.id.fr_back:
                finish();
                break;
            case R.id.tv_title_tag:
                Crouton.makeText(this, "找回密码", Style.ALERT).show();
                break;
        }

    }


    @Override
    public void toRegisterActivity() {
        startActivity(new Intent(this, RegisterActivity.class));
    }

    @Override
    public void onLoginResult(Boolean result, int code) {
        btnLogin.setEnabled(true);
        btnRegister.setEnabled(true);
        if (result) {
            Toast.makeText(this, "Login Success", Toast.LENGTH_SHORT).show();
        } else
            Toast.makeText(this, "Login Fail, code = " + code, Toast.LENGTH_SHORT).show();
    }
}
