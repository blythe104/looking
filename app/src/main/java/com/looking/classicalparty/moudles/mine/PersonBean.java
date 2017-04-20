package com.looking.classicalparty.moudles.mine;

import com.looking.classicalparty.lib.base.Bean.BaseBean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by xin on 2017/3/27.
 */

public class PersonBean extends BaseBean {


    private List<User> content;

    public List<User> getContent() {
        return content;
    }

    public void setContent(List<User> content) {
        this.content = content;
    }

    public static class User implements Serializable {
        /**
         * mid : 11
         * musername : blythe
         * mmobile : 18721816797
         * mcreate_time : null
         * mstatus : 1
         * mnickname : An_Ran
         * muid : null
         * mbirthday : null
         * msex : null
         * mfans : null
         * mfollowed : null
         * msignature : null
         * mavatar : http://www.jingdian.party/public/images/userbg6.jpg
         */

        private String mid;
        private String musername;
        private String mmobile;
        private String mcreate_time;
        private String mstatus;
        private String mnickname;
        private String muid;
        private String mbirthday;
        private String msex;
        private String mfans;
        private String mfollowed;
        private String msignature;
        private String mavatar;

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

        public String getMmobile() {
            return mmobile;
        }

        public void setMmobile(String mmobile) {
            this.mmobile = mmobile;
        }

        public String getMcreate_time() {
            return mcreate_time;
        }

        public void setMcreate_time(String mcreate_time) {
            this.mcreate_time = mcreate_time;
        }

        public String getMstatus() {
            return mstatus;
        }

        public void setMstatus(String mstatus) {
            this.mstatus = mstatus;
        }

        public String getMnickname() {
            return mnickname;
        }

        public void setMnickname(String mnickname) {
            this.mnickname = mnickname;
        }

        public String getMuid() {
            return muid;
        }

        public void setMuid(String muid) {
            this.muid = muid;
        }

        public String getMbirthday() {
            return mbirthday;
        }

        public void setMbirthday(String mbirthday) {
            this.mbirthday = mbirthday;
        }

        public String getMsex() {
            return msex;
        }

        public void setMsex(String msex) {
            this.msex = msex;
        }

        public String getMfans() {
            return mfans;
        }

        public void setMfans(String mfans) {
            this.mfans = mfans;
        }

        public String getMfollowed() {
            return mfollowed;
        }

        public void setMfollowed(String mfollowed) {
            this.mfollowed = mfollowed;
        }

        public String getMsignature() {
            return msignature;
        }

        public void setMsignature(String msignature) {
            this.msignature = msignature;
        }

        public String getMavatar() {
            return mavatar;
        }

        public void setMavatar(String mavatar) {
            this.mavatar = mavatar;
        }
    }
}
