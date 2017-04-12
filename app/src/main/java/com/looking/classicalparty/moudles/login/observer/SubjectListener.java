package com.looking.classicalparty.moudles.login.observer;

/**
 * Created by xin on 2017/4/12.
 */

public interface SubjectListener {
    void add(ObserverListener observerListener);
    
    void notifyObserver(int status);
    
    void remove(ObserverListener observerListener);
}
