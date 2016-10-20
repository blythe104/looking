package com.looking.classicalparty.lib.widget;

import android.graphics.Bitmap;

/**
 * Created by xin on 2016/8/11.
 */
public class ItemData {
    public ItemData() {
    }

    public ItemData(int imgId,String content,String flag){
        this.imgId = imgId;
        this.flag = flag;
        this.content = content;
    }

    public ItemData(Bitmap bitmap,String content,String flag){
        this.bitmap = bitmap;
        this.flag = flag;
        this.content = content;
    }
    public ItemData(int imgId,String content,String flag,String desc){
        this.imgId = imgId;
        this.flag = flag;
        this.content = content;
        this.desc=desc;
    }
    public int imgId;
    public Bitmap bitmap;
    public  String flag;
    public  String content ;
    public String desc;
}
