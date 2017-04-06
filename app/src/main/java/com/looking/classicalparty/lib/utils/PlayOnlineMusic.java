package com.looking.classicalparty.lib.utils;

import android.app.Activity;
import android.text.TextUtils;

import com.looking.classicalparty.moudles.find.bean.MusicBean;

import java.io.File;

public abstract class PlayOnlineMusic extends PlayMusic {
    private MusicBean mOnlineMusic;

    public PlayOnlineMusic(Activity activity, MusicBean onlineMusic) {
        super(activity, 3);
        mOnlineMusic = onlineMusic;
    }

    @Override
    protected void getPlayInfo() {
        String artist = mOnlineMusic.getMusername();
        String title = mOnlineMusic.getTitle();

        // 下载封面
        String albumFileName = FileUtils.getAlbumFileName(artist, title);
        File albumFile = new File(FileUtils.getAlbumDir(), albumFileName);
        String picUrl = "http://www.jingdian.party/" + mOnlineMusic.getCover_path();
        if (!albumFile.exists() && !TextUtils.isEmpty(picUrl)) {
            downloadAlbum(picUrl, albumFileName);
        } else {
            mCounter++;
        }

        music = new MusicBean();
        music.setTitle(title);
        music.setMusername(artist);

    }

    private void downloadAlbum(String picUrl, String fileName) {

    }
}
