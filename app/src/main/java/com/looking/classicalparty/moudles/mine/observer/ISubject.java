package com.looking.classicalparty.moudles.mine.observer;

/**
 * Created by xin on 2016/11/2.
 */

public interface ISubject {
    //注册
    void register(Observer observer);

    //反注册
    void unRegister(Observer observer);

    //通知数据更新
    void notifyDataChange(String desc);
}
