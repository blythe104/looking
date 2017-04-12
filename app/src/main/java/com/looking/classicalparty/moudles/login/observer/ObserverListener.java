package com.looking.classicalparty.moudles.login.observer;

/**
 * Created by xin on 2017/4/12.
 * 使用观察者模式，实现登录和退出的同步
 */

public interface ObserverListener {
    void observerIsLogin(int isLogin);
}
