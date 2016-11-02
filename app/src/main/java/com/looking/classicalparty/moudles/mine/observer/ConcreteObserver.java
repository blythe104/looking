package com.looking.classicalparty.moudles.mine.observer;

/**
 * Created by xin on 2016/11/2.
 * 实体观察者对象
 */


public class ConcreteObserver implements Observer {
    private String desco;

    @Override
    public void update(String desc) {
        this.desco = desc;
    }
}
