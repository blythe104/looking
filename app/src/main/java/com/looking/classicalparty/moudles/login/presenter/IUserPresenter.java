package com.looking.classicalparty.moudles.login.presenter;

import com.looking.classicalparty.moudles.login.bean.UserBean;

/**
 * Created by xin on 2016/10/19.
 */
public interface IUserPresenter {
    void login(String userName, String pwd);

    void Success(UserBean userBean);

    void serviceError(String errorMsg);

    void otherError(String errorMsg);

    void toRegister();
}
