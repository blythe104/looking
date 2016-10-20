package com.looking.classicalparty.moudles.login.bean;

import com.looking.classicalparty.lib.base.Bean.BaseBean;

/**
 * Created by xin on 2016/10/19.
 */
public class UserBean extends BaseBean {
    private String username;
    private String password;

    public UserBean(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }


    public String getPassword() {
        return password;
    }

}
