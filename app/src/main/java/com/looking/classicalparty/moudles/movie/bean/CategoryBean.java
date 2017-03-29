package com.looking.classicalparty.moudles.movie.bean;

import com.looking.classicalparty.lib.base.Bean.BaseBean;

import java.util.List;

/**
 * Created by xin on 2017/3/29.
 */

public class CategoryBean extends BaseBean {

    private List<CategoryEntity> category;

    public List<CategoryEntity> getCategory() {
        return category;
    }

    public void setCategory(List<CategoryEntity> category) {
        this.category = category;
    }

    public static class CategoryEntity {

        private String cid;
        private String ctitle;
        private String cpid;
        private String csort;
        private String cuid;
        private String cstatus;
        private String ccreate_time;
        private String ccover_path;
        private String username;

        public String getCid() {
            return cid;
        }

        public void setCid(String cid) {
            this.cid = cid;
        }

        public String getCtitle() {
            return ctitle;
        }

        public void setCtitle(String ctitle) {
            this.ctitle = ctitle;
        }

        public String getCpid() {
            return cpid;
        }

        public void setCpid(String cpid) {
            this.cpid = cpid;
        }

        public String getCsort() {
            return csort;
        }

        public void setCsort(String csort) {
            this.csort = csort;
        }

        public String getCuid() {
            return cuid;
        }

        public void setCuid(String cuid) {
            this.cuid = cuid;
        }

        public String getCstatus() {
            return cstatus;
        }

        public void setCstatus(String cstatus) {
            this.cstatus = cstatus;
        }

        public String getCcreate_time() {
            return ccreate_time;
        }

        public void setCcreate_time(String ccreate_time) {
            this.ccreate_time = ccreate_time;
        }

        public String getCcover_path() {
            return ccover_path;
        }

        public void setCcover_path(String ccover_path) {
            this.ccover_path = ccover_path;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }
    }
}
