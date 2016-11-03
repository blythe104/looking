package com.looking.classicalparty.moudles.login.bean;

import com.looking.classicalparty.lib.base.Bean.BaseBean;

/**
 * Created by xin on 2016/10/19.
 */
public class UserBean extends BaseBean {


    /**
     * token :
     * user :
     */

    private String token;
    /**
     * mid : 11
     * musername : blythe
     */

    private UserEntity user;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }


    public static class UserEntity {
        private String mid;
        private String musername;

        public String getMid() {
            return mid;
        }

        public void setMid(String mid) {
            this.mid = mid;
        }

        public String getMusername() {
            return musername;
        }

        public void setMusername(String musername) {
            this.musername = musername;
        }
    }
}
