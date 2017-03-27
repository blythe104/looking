package com.looking.classicalparty.moudles.security;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

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
import com.squareup.okhttp.Request;

import java.util.ArrayList;
import java.util.List;

import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;

public class ChangeLoginPwdActivity extends BaseActivity {


    private TitleBar mTitle;
    private EditText mEtOldpwd;
    private EditText mEtNewpwd;
    private String newPwd;
    private String oldPwd;

    @Override
    public void initView() {
        setContentView(R.layout.activity_change_login_pwd);
        mTitle = (TitleBar) findViewById(R.id.title_bar);
        mEtOldpwd = (EditText) findViewById(R.id.et_oldpwd);
        mEtNewpwd = (EditText) findViewById(R.id.et_newpwd);

    }

    @Override
    public void initListener() {
        mTitle.setOnClickListener(new TitleBar.OnClickListener() {
            @Override
            public void OnLeftClick() {
                finish();
            }

            @Override
            public void OnRightClick() {
                // TODO: 2016/10/28 添加修改密码接口
                oldPwd = mEtOldpwd.getText().toString();
                newPwd = mEtNewpwd.getText().toString();
                if (TextUtils.isEmpty(oldPwd) || TextUtils.isEmpty(newPwd)) {
                    Crouton.makeText(ChangeLoginPwdActivity.this, "旧密码或者新密码不能为空", Style.CONFIRM).show();
                } else {
                    toChangePwd(oldPwd, newPwd);
                }
            }
        });

    }


    private void toChangePwd(String oldPwd, String newPwd) {
        List<Param> paramList = new ArrayList<>();
        Param key = new Param("key", SharedPreUtils.getString(StringContants.KEY, ""));
        Param token = new Param("Token", SharedPreUtils.getString(StringContants.TOKEN, ""));
        Param pwd = new Param("password", newPwd);
        Param oPwd = new Param("oldpassword", oldPwd);
        paramList.add(key);
        paramList.add(token);
        paramList.add(pwd);
        paramList.add(oPwd);

        HttpUtils.post(ConstantApi.UPDATEPWD, paramList, new ResultCallback() {
            @Override
            public void onSuccess(Object response) {
                BaseBean baseBean = new Gson().fromJson(response.toString(), BaseBean.class);
                if (baseBean.getResult() == 200) {
                    Crouton.makeText(ChangeLoginPwdActivity.this, baseBean.getResultMsg(), Style.CONFIRM).show();
                    finish();
                } else {
                    Crouton.makeText(ChangeLoginPwdActivity.this, baseBean.getResultMsg(), Style.CONFIRM).show();
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
    public void initData() {
        mTitle.setTitle("修改密码", "完成");
    }

    @Override
    public void processClick(View v) {

    }
}
