package com.looking.classicalparty.moudles.register.bean;

import com.looking.classicalparty.lib.base.Bean.BaseBean;

/**
 * Created by xin on 2016/10/24.
 */

public class RegisterBean extends BaseBean{


    /**
     * user : {"mid":20}
     */

    private UserEntity user;

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public static class UserEntity {
        /**
         * mid : 20
         */

        private int mid;

        public int getMid() {
            return mid;
        }

        public void setMid(int mid) {
            this.mid = mid;
        }
    }
}
