package com.looking.classicalparty.moudles.movie.view;

import android.os.Bundle;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

import com.looking.classicalparty.R;
import com.looking.classicalparty.lib.base.activity.BaseActivity;
import com.looking.classicalparty.lib.ui.TitleBar;

public class MovieScoreActivity extends BaseActivity {
    private RatingBar ratingBar;
    private TitleBar titleBar;
    private TextView movie_title;
    private TextView director;
    private TextView introduce;
    private TextView actors;
    private TextView score;
    private Bundle bundle;

    @Override
    public void initView() {
        setContentView(R.layout.activity_movie_score);
        bundle = getIntent().getExtras();
        ratingBar = (RatingBar) findViewById(R.id.ratingbar);
        titleBar = (TitleBar) findViewById(R.id.title_bar);
        movie_title = (TextView) findViewById(R.id.movie_title);
        director = (TextView) findViewById(R.id.director);
        introduce = (TextView) findViewById(R.id.introduce);
        actors = (TextView) findViewById(R.id.actors);
        score = (TextView) findViewById(R.id.score);
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {
        titleBar.setTitle("影评");
        movie_title.setText(bundle.get("title").toString());
        director.setText(bundle.get("director").toString());
        actors.setText(bundle.get("actors").toString());
        score.setText(bundle.get("score").toString());

    }

    @Override
    public void processClick(View v) {

    }
}
