package com.looking.classicalparty.moudles.movie.bean;

import com.looking.classicalparty.lib.base.Bean.BaseBean;

import java.util.List;

/**
 * Created by xin on 2017/3/30.
 */

public class GetVideoBean extends BaseBean {

    private int currPage;
    private int totalPage;
    private int totalCount;
    private List<ActivityEntity> Activity;

    public int getCurrPage() {
        return currPage;
    }

    public void setCurrPage(int currPage) {
        this.currPage = currPage;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<ActivityEntity> getActivity() {
        return Activity;
    }

    public void setActivity(List<ActivityEntity> Activity) {
        this.Activity = Activity;
    }

    public static class ActivityEntity {
        private String id;
        private String title;
        private String director;
        private String proper_age;
        private String scores;
        private String style;
        private String decade;
        private String actors;
        private String summary;
        private String cover_path;
        private String v_path;
        private String is_hot;
        private String is_del;
        private String create_time;
        private String sort;
        private String whereis;
        private String vlink;
        private String muid;
        private String vstatus;
        private String vamuid;
        private String vscreate_time;
        private String vcategory;
        private String support_count;
        private String comment_count;
        private String musername;
        private String username;
        private String views_count;
        private String repost_count;
        private String download_count;
        private String ctitle;
        private String click_count;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDirector() {
            return director;
        }

        public void setDirector(String director) {
            this.director = director;
        }

        public String getProper_age() {
            return proper_age;
        }

        public void setProper_age(String proper_age) {
            this.proper_age = proper_age;
        }

        public String getScores() {
            return scores;
        }

        public void setScores(String scores) {
            this.scores = scores;
        }

        public String getStyle() {
            return style;
        }

        public void setStyle(String style) {
            this.style = style;
        }

        public String getDecade() {
            return decade;
        }

        public void setDecade(String decade) {
            this.decade = decade;
        }

        public String getActors() {
            return actors;
        }

        public void setActors(String actors) {
            this.actors = actors;
        }

        public String getSummary() {
            return summary;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }

        public String getCover_path() {
            return cover_path;
        }

        public void setCover_path(String cover_path) {
            this.cover_path = cover_path;
        }

        public String getV_path() {
            return v_path;
        }

        public void setV_path(String v_path) {
            this.v_path = v_path;
        }

        public String getIs_hot() {
            return is_hot;
        }

        public void setIs_hot(String is_hot) {
            this.is_hot = is_hot;
        }

        public String getIs_del() {
            return is_del;
        }

        public void setIs_del(String is_del) {
            this.is_del = is_del;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getSort() {
            return sort;
        }

        public void setSort(String sort) {
            this.sort = sort;
        }

        public String getWhereis() {
            return whereis;
        }

        public void setWhereis(String whereis) {
            this.whereis = whereis;
        }

        public String getVlink() {
            return vlink;
        }

        public void setVlink(String vlink) {
            this.vlink = vlink;
        }

        public String getMuid() {
            return muid;
        }

        public void setMuid(String muid) {
            this.muid = muid;
        }

        public String getVstatus() {
            return vstatus;
        }

        public void setVstatus(String vstatus) {
            this.vstatus = vstatus;
        }

        public String getVamuid() {
            return vamuid;
        }

        public void setVamuid(String vamuid) {
            this.vamuid = vamuid;
        }

        public String getVscreate_time() {
            return vscreate_time;
        }

        public void setVscreate_time(String vscreate_time) {
            this.vscreate_time = vscreate_time;
        }

        public String getVcategory() {
            return vcategory;
        }

        public void setVcategory(String vcategory) {
            this.vcategory = vcategory;
        }

        public String getSupport_count() {
            return support_count;
        }

        public void setSupport_count(String support_count) {
            this.support_count = support_count;
        }

        public String getComment_count() {
            return comment_count;
        }

        public void setComment_count(String comment_count) {
            this.comment_count = comment_count;
        }

        public String getMusername() {
            return musername;
        }

        public void setMusername(String musername) {
            this.musername = musername;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getViews_count() {
            return views_count;
        }

        public void setViews_count(String views_count) {
            this.views_count = views_count;
        }

        public String getRepost_count() {
            return repost_count;
        }

        public void setRepost_count(String repost_count) {
            this.repost_count = repost_count;
        }

        public String getDownload_count() {
            return download_count;
        }

        public void setDownload_count(String download_count) {
            this.download_count = download_count;
        }

        public String getCtitle() {
            return ctitle;
        }

        public void setCtitle(String ctitle) {
            this.ctitle = ctitle;
        }

        public String getClick_count() {
            return click_count;
        }

        public void setClick_count(String click_count) {
            this.click_count = click_count;
        }
    }
}
