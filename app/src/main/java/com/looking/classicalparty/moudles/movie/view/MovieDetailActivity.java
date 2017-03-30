package com.looking.classicalparty.moudles.movie.view;

import android.view.View;

import com.looking.classicalparty.R;
import com.looking.classicalparty.lib.base.activity.BaseActivity;
import com.looking.classicalparty.lib.ui.TitleBar;

public class MovieDetailActivity extends BaseActivity {


    private TitleBar mTitleBar;

    @Override
    public void initView() {
        setContentView(R.layout.activity_movie_detail);
        mTitleBar = (TitleBar) findViewById(R.id.title_bar);
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {
        mTitleBar.setTitle("电影详情");

    }

    @Override
    public void processClick(View v) {

    }
}
