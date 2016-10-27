package com.looking.classicalparty.lib.listener;

/**
 * Created by  chuangtou on 16/7/18.
 * 国丞创投
 */
public interface ITitleBar {

    void setTitle(String title, int Visibility);
    void setTitle(int title, int Visibility);

    void setTitle(String title);
    void setTitle(int title);

    void setTitle(String title, String rightTag);

}
