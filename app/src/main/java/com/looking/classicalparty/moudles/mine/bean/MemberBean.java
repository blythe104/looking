package com.looking.classicalparty.moudles.mine.bean;

import com.looking.classicalparty.lib.base.Bean.BaseBean;

/**
 * Created by xin on 2017/4/20.
 */

public class MemberBean extends BaseBean {
    
    private Userinfo Userinfo;
    
    public Userinfo getUserinfo() {
        return Userinfo;
    }
    
    public void setUserinfo(Userinfo Userinfo) {
        this.Userinfo = Userinfo;
    }
    
    public static class Userinfo {
        
        private String mnickname;
        private String mid;
        private String mbirthday;
        private String mgender;
        private String msignature;
        private String mmobile;
        
        public String getMbirthday() {
            return mbirthday;
        }
        
        public void setMbirthday(String mbirthday) {
            this.mbirthday = mbirthday;
        }
        
        public String getMgender() {
            return mgender;
        }
        
        public void setMgender(String mgender) {
            this.mgender = mgender;
        }
        
        public String getMsignature() {
            return msignature;
        }
        
        public void setMsignature(String msignature) {
            this.msignature = msignature;
        }
        
        public String getMmobile() {
            return mmobile;
        }
        
        public void setMmobile(String mmobile) {
            this.mmobile = mmobile;
        }
        
        public String getMnickname() {
            return mnickname;
        }
        
        public void setMnickname(String mnickname) {
            this.mnickname = mnickname;
        }
        
        public String getMid() {
            return mid;
        }
        
        public void setMid(String mid) {
            this.mid = mid;
        }
    }
}
