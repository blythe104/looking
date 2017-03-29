package com.looking.classicalparty.moudles.find.bean;

/**
 * Created by xin on 2017/3/29.
 */

public class MusicBean {

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
