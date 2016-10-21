package com.looking.classicalparty.moudles.login.view;

import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.looking.classicalparty.R;
import com.looking.classicalparty.lib.base.activity.BaseActivity;
import com.looking.classicalparty.moudles.login.presenter.IUserPresenter;
import com.looking.classicalparty.moudles.login.presenter.UserPresenterImpl;

public class LoginActivity extends BaseActivity implements IUserView {

    private EditText etUserName;
    private EditText etPassword;
    private LinearLayout btnLogin;
    private LinearLayout btnRegister;
    private IUserPresenter userPresenter;

    @Override
    public void initView() {
        setContentView(R.layout.activity_login);
        etUserName = (EditText) findViewById(R.id.et_username);
        etPassword = (EditText) findViewById(R.id.et_password);
        btnLogin = (LinearLayout) findViewById(R.id.btn_login);
        btnRegister = (LinearLayout) findViewById(R.id.btn_register);


        userPresenter = new UserPresenterImpl(this, this);
        btnLogin.setOnClickListener(this);
        btnRegister.setOnClickListener(this);

    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void processClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                userPresenter.login(etUserName.getText().toString(), etPassword.getText().toString());
                break;
            case R.id.btn_register:
                userPresenter.clear();
                break;
        }

    }


    @Override
    public void onClearText() {
        etUserName.setText("");
        etPassword.setText("");
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
