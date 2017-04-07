package com.looking.classicalparty.moudles.find.bean;

import com.looking.classicalparty.lib.base.Bean.BaseBean;

import java.util.List;

/**
 * Created by xin on 2016/11/14.
 * 发现页面的数据集合
 */

public class FindBean extends BaseBean {
    
    private ContentEntity content;
    
    public ContentEntity getContent() {
        return content;
    }
    
    public void setContent(ContentEntity content) {
        this.content = content;
    }
    
    public static class ContentEntity {
        private List<BannerBean> image;
        private List<ReviewsBean> reviews;
        private List<MusicBean> music;
        
        public List<BannerBean> getBanner() {
            return image;
        }
        
        public void setBanner(List<BannerBean> image) {
            this.image = image;
        }
        
        public List<ReviewsBean> getReviews() {
            return reviews;
        }
        
        public void setReviews(List<ReviewsBean> reviews) {
            this.reviews = reviews;
        }
        
        public List<MusicBean> getMusic() {
            return music;
        }
        
        public void setMusic(List<MusicBean> music) {
            this.music = music;
        }
    }
}
