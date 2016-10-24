package com.looking.classicalparty.moudles.login.model;


import com.google.gson.Gson;
import com.looking.classicalparty.lib.constants.ConstantApi;
import com.looking.classicalparty.lib.constants.StringContants;
import com.looking.classicalparty.lib.http.HttpUtils;
import com.looking.classicalparty.lib.http.Param;
import com.looking.classicalparty.lib.http.ResultCallback;
import com.looking.classicalparty.lib.utils.SharedPreUtils;
import com.looking.classicalparty.moudles.login.bean.UserBean;
import com.looking.classicalparty.moudles.login.presenter.IUserPresenter;
import com.squareup.okhttp.Request;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MVPHelper on 2016/10/19
 */

public class UserModelImpl implements IUserModel {
    private IUserPresenter presenter;


    public UserModelImpl(IUserPresenter presenter) {
        this.presenter = presenter;
    }


    @Override
    public void checkUserValidity(String name, String passwd) {

        List<Param> paramList = new ArrayList<>();
        Param userName = new Param("username", name);
        Param password = new Param("password", passwd);
        Param key = new Param("Key", SharedPreUtils.getString(StringContants.KEY,""));
        paramList.add(userName);
        paramList.add(password);
        paramList.add(key);
        //访问网络接口
        HttpUtils.post(ConstantApi.login, paramList, new ResultCallback() {
            @Override
            public void onSuccess(Object response) {
                UserBean userBean = new Gson().fromJson(response.toString(), UserBean.class);
                presenter.Success(userBean);
                SharedPreUtils.saveString(StringContants.TOKEN, userBean.getToken());
            }

            @Override
            public void onFailure(Request request, Exception e) {
                presenter.serviceError("");
            }

            @Override
            public void onNoNetWork(String resultMsg) {
                presenter.serviceError("无网络");

            }


            @Override
            public void onServiceError(String request) {
                super.onServiceError(request);
                presenter.serviceError(request);
            }
        });
    }
}
