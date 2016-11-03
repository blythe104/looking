package com.looking.classicalparty.moudles.mine.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xin on 2016/11/2.
 */
//实体被观察者对象
public class ConcreteSubject implements ISubject {
    static List<Observer> observers = new ArrayList<>();
    static ConcreteSubject subject;

    public static ConcreteSubject getInstance() {
        if (subject == null) {
            subject = new ConcreteSubject();
        }

        return subject;
    }

    @Override
    public void register(Observer observer) {
        if (!observers.contains(observer)) {
            observers.add(observer);
        }
    }

    @Override
    public void unRegister(Observer observer) {
        if (observers.contains(observer)) {
            observers.remove(observer);
        }
    }

    @Override
    public void notifyDataChange(String desc) {
        if (observers != null && !observers.isEmpty()) {
            for (Observer observer : observers) {
                observer.update(desc);
            }
        }
    }
}
