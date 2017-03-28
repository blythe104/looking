package com.looking.classicalparty.moudles.find.bean;

import com.looking.classicalparty.lib.base.Bean.BaseBean;

import java.util.List;

/**
 * Created by xin on 2016/11/14.
 */

public class FindBean extends BaseBean {

    /**
     * firstimage : http://www.jingdian.party/public/images/header-bg.jpg
     * reviews : [{"id":"148","title":"从你的全世界路过","director":"张一白","cover_path":"http://www.jingdian
     * .party/public/images/upload/2016-10-16/thumb_14766235780658.jpg","vscreate_time":"1476623268",
     * "musername":"wuyuer0220@sina.com","url":"http://www.jingdian.party/api/Single?id=148"}]
     * music : [{"id":"21","title":"樱吹雪","director":"周传雄","cover_path":"http://www.jingdian
     * .party/public/images/upload/2016-09-08/1473329050.jpg","v_path":"http://www.jingdian
     * .party/public/videos/upload/2016-09-06/14731475630163.mp3","vscreate_time":null,"musername":"不安分的年轻人"}]
     */

    private ContentEntity content;

    public ContentEntity getContent() {
        return content;
    }

    public void setContent(ContentEntity content) {
        this.content = content;
    }

    public static class ContentEntity {
        private String firstimage;
        /**
         * id : 148
         * title : 从你的全世界路过
         * director : 张一白
         * cover_path : http://www.jingdian.party/public/images/upload/2016-10-16/thumb_14766235780658.jpg
         * vscreate_time : 1476623268
         * musername : wuyuer0220@sina.com
         * url : http://www.jingdian.party/api/Single?id=148
         */

        private List<ReviewsEntity> reviews;
        /**
         * id : 21
         * title : 樱吹雪
         * director : 周传雄
         * cover_path : http://www.jingdian.party/public/images/upload/2016-09-08/1473329050.jpg
         * v_path : http://www.jingdian.party/public/videos/upload/2016-09-06/14731475630163.mp3
         * vscreate_time : null
         * musername : 不安分的年轻人
         */

        private List<MusicEntity> music;

        public String getFirstimage() {
            return firstimage;
        }

        public List<ReviewsEntity> getReviews() {
            return reviews;
        }

        public List<MusicEntity> getMusic() {
            return music;
        }

        public static class ReviewsEntity {
            private String id;
            private String title;
            private String director;
            private String cover_path;
            private String vscreate_time;
            private String musername;
            private String url;

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

            public String getCover_path() {
                return cover_path;
            }

            public void setCover_path(String cover_path) {
                this.cover_path = cover_path;
            }

            public String getVscreate_time() {
                return vscreate_time;
            }

            public void setVscreate_time(String vscreate_time) {
                this.vscreate_time = vscreate_time;
            }

            public String getMusername() {
                return musername;
            }

            public void setMusername(String musername) {
                this.musername = musername;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }

        public static class MusicEntity {
            private String id;
            private String title;
            private String director;
            private String cover_path;
            private String v_path;
            private Object vscreate_time;
            private String musername;

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

            public Object getVscreate_time() {
                return vscreate_time;
            }

            public void setVscreate_time(Object vscreate_time) {
                this.vscreate_time = vscreate_time;
            }

            public String getMusername() {
                return musername;
            }

            public void setMusername(String musername) {
                this.musername = musername;
            }
        }
    }
}
