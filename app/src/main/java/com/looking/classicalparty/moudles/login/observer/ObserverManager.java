package com.looking.classicalparty.moudles.login.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xin on 2017/4/12.
 */

public class ObserverManager implements SubjectListener {
    private static ObserverManager observerManager;
    private List<ObserverListener> list = new ArrayList<>();
    
    public static ObserverManager getInstance() {
        if (null == observerManager) {
            synchronized (ObserverManager.class) {
                if (null == observerManager) {
                    observerManager = new ObserverManager();
                }
            }
        }
        return observerManager;
    }
    
    @Override
    public void add(ObserverListener observerListener) {
        list.add(observerListener);
        
    }
    
    @Override
    public void notifyObserver(int status) {
        for (ObserverListener observerListener : list) {
            observerListener.observerIsLogin(status);
        }
    }
    
    @Override
    public void remove(ObserverListener observerListener) {
        list.remove(observerListener);
        
    }
}
