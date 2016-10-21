package com.looking.classicalparty.moudles.login.presenter;


import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import com.looking.classicalparty.moudles.login.bean.UserBean;
import com.looking.classicalparty.moudles.login.model.IUserModel;
import com.looking.classicalparty.moudles.login.model.UserModelImpl;
import com.looking.classicalparty.moudles.login.view.IUserView;

/**
 * Created by MVPHelper on 2016/10/19
 */

public class UserPresenterImpl implements IUserPresenter {
    Handler handler;
    private IUserModel userModel;
    private IUserView mUserView;
    private Context context;

    public UserPresenterImpl(Context context, IUserView iUserView) {
        this.mUserView = iUserView;
        this.context = context;
        handler = new Handler(Looper.getMainLooper());
        userModel = new UserModelImpl(this);
    }


    @Override
    public void login(String userName, String pwd) {
        userModel.checkUserValidity(userName, pwd);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

            }
        }, 1000);
    }

    @Override
    public void Success(final UserBean userBean) {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(context, userBean.getResultMsg(), Toast.LENGTH_SHORT).show();
            }
        }, 1000);
    }

    @Override
    public void serviceError(final String errorMsg) {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(context, errorMsg, Toast.LENGTH_SHORT).show();
            }
        }, 1000);
    }

    @Override
    public void otherError(final String errorMsg) {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(context, errorMsg, Toast.LENGTH_SHORT).show();
            }
        }, 1000);
    }


    @Override
    public void clear() {
        mUserView.onClearText();
    }


}
