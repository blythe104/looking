package com.looking.classicalparty.lib.common;

import android.content.Context;

import com.looking.classicalparty.lib.constants.StringContants;
import com.looking.classicalparty.lib.utils.SharedPreUtils;

/**
 * Created by xin on 2016/10/24.
 */
public class UserInfo {
    String token = "";
    private Context context;

    public UserInfo() {
        token = SharedPreUtils.getString(StringContants.TOKEN,"");
    }


    public void clear() {
        SharedPreUtils.saveString(StringContants.TOKEN, "");
    }

    public boolean isLogin() {
        if (null == token) {
            return false;
        }
        if ("".equals(token)) {
            return false;
        }
        if (token.isEmpty()) {
            return false;
        }
        return true;
    }
}
