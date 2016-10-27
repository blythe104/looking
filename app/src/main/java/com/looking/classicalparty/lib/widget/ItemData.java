package com.looking.classicalparty.lib.widget;

/**
 * Created by xin on 2016/8/11.
 */
public class ItemData {
    public int imgId;
    public int bitmap;
    public String flag;
    public String content;
    public String desc;
    public boolean isVisible;

    public ItemData() {
    }

    public ItemData(int imgId, String content, String flag) {
        this.imgId = imgId;
        this.flag = flag;
        this.content = content;
    }

    /**
     * 设置后面图片的路径
     *
     * @param content
     * @param flag
     * @param bitmap
     */
    public ItemData(String content, String flag, int bitmap) {
        this.bitmap = bitmap;
        this.flag = flag;
        this.content = content;
    }

    public ItemData(int imgId, String content, String flag, String desc) {
        this.imgId = imgId;
        this.flag = flag;
        this.content = content;
        this.desc = desc;
    }

    public ItemData(int imgId, String content, String flag, boolean boo) {
        this.imgId = imgId;
        this.flag = flag;
        this.content = content;
        this.isVisible = boo;
    }

    //布局无图片
    public ItemData(String content, String flag, boolean boo) {
        this.flag = flag;
        this.content = content;
        this.isVisible = boo;
    }

    //布局无图片有描述
    public ItemData(String content, String desc, String flag, boolean boo) {
        this.flag = flag;
        this.desc = desc;
        this.content = content;
        this.isVisible = boo;
    }

    //布局有图片有描述
    public ItemData(int imgId, String content, String desc, String flag, boolean boo) {
        this.imgId = imgId;
        this.flag = flag;
        this.desc = desc;
        this.content = content;
        this.isVisible = boo;
    }
}
