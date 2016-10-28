package com.looking.classicalparty.moudles.register.view;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.looking.classicalparty.R;
import com.looking.classicalparty.lib.base.Bean.BaseBean;
import com.looking.classicalparty.lib.base.activity.BaseActivity;
import com.looking.classicalparty.lib.constants.ConstantApi;
import com.looking.classicalparty.lib.constants.StringContants;
import com.looking.classicalparty.lib.http.HttpUtils;
import com.looking.classicalparty.lib.http.Param;
import com.looking.classicalparty.lib.http.ResultCallback;
import com.looking.classicalparty.lib.ui.TitleBar;
import com.looking.classicalparty.lib.utils.SharedPreUtils;
import com.looking.classicalparty.moudles.register.bean.RegisterBean;
import com.squareup.okhttp.Request;

import java.util.ArrayList;
import java.util.List;

import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;

public class RegisterActivity extends BaseActivity {


    private TextView mTvRegister;
    private EditText mEtUserName;
    private EditText mEtPassword;
    private TitleBar mTitle;

    @Override
    public void initView() {
        setContentView(R.layout.activity_register);
        mTitle = (TitleBar) findViewById(R.id.title_bar);
        mTvRegister = (TextView) findViewById(R.id.tv_register);
        mEtUserName = (EditText) findViewById(R.id.et_username);
        mEtPassword = (EditText) findViewById(R.id.et_password);

    }

    @Override
    public void initListener() {
        mTvRegister.setOnClickListener(this);
        mTitle.setOnClickListener(new TitleBar.OnClickListener() {
            @Override
            public void OnLeftClick() {
                finish();

            }

            @Override
            public void OnRightClick() {
            }
        });


    }

    @Override
    public void initData() {
        mTitle.setTitle("注册");
    }

    /**
     * 检测用户名是否可用
     *
     * @param username
     */
    public void checkUser(final String username, final String pwd) {
        List<Param> paramList = new ArrayList<>();
        Param key = new Param("key", SharedPreUtils.getString(StringContants.KEY, ""));
        Param musername = new Param("musername", username);
        paramList.add(key);
        paramList.add(musername);
        HttpUtils.post(ConstantApi.checkUser, paramList, new ResultCallback() {
            @Override
            public void onSuccess(Object response) {
                BaseBean baseBean = new Gson().fromJson(response.toString(), BaseBean.class);
                if (baseBean.getResult() == 200) {
                    //调用注册接口
                    userRegister(username, pwd);
                } else {
                    //该用户名存在
                    Crouton.makeText(RegisterActivity.this, "该用户名已经存在", Style.ALERT).show();
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
     * 注册接口
     *
     * @param username
     * @param pwd
     */
    private void userRegister(String username, String pwd) {
        List<Param> paramlist = new ArrayList<>();
        Param name = new Param("musername", username);
        Param password = new Param("mpassword", pwd);
        Param key = new Param("key", SharedPreUtils.getString(StringContants.KEY));
        paramlist.add(name);
        paramlist.add(key);
        paramlist.add(password);
        HttpUtils.post(ConstantApi.register, paramlist, new ResultCallback() {
            @Override
            public void onSuccess(Object response) {
                RegisterBean registerBean = new Gson().fromJson(response.toString(), RegisterBean.class);
                if (registerBean.getResult() == 200) {
                    Crouton.makeText(RegisterActivity.this, "注册成功", Style.CONFIRM).show();
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


    @Override
    public void processClick(View v) {
        switch (v.getId()) {
            case R.id.tv_register:
                //检测用户名是否可用
                String username = mEtUserName.getText().toString();
                String password = mEtPassword.getText().toString();
                if (!TextUtils.isEmpty(username)) {
                    checkUser(username, password);
                } else {
                    Crouton.makeText(RegisterActivity.this, "注册成功", Style.CONFIRM).show();
                }
                break;
        }

    }
}
