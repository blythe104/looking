package com.looking.classicalparty.moudles.movie.view;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.looking.classicalparty.R;
import com.looking.classicalparty.lib.base.activity.BaseActivity;
import com.looking.classicalparty.lib.constants.ConstantApi;
import com.looking.classicalparty.lib.constants.StringContants;
import com.looking.classicalparty.lib.http.HttpUtils;
import com.looking.classicalparty.lib.http.Param;
import com.looking.classicalparty.lib.http.ResultCallback;
import com.looking.classicalparty.lib.ui.TitleBar;
import com.looking.classicalparty.lib.utils.ImageLoaderUtils;
import com.looking.classicalparty.lib.utils.SharedPreUtils;
import com.looking.classicalparty.moudles.movie.bean.MovieDetailBean;
import com.squareup.okhttp.Request;

import java.util.ArrayList;
import java.util.List;

public class MovieDetailActivity extends BaseActivity {


    private TitleBar mTitleBar;
    private String vid;
    private TextView movie_title;
    private TextView director;
    private TextView introduce;
    private TextView actors;
    private TextView score;

    private LinearLayout llScore;
    private MovieDetailBean movieDetailBean;
    private ImageView movieImg;

    @Override
    public void initView() {
        setContentView(R.layout.activity_movie_detail);
        mTitleBar = (TitleBar) findViewById(R.id.title_bar);
        vid = getIntent().getStringExtra("id");
        movie_title = (TextView) findViewById(R.id.movie_title);
        director = (TextView) findViewById(R.id.director);
        introduce = (TextView) findViewById(R.id.introduce);
        actors = (TextView) findViewById(R.id.actors);
        score = (TextView) findViewById(R.id.score);
        movieImg = (ImageView) findViewById(R.id.movie_img);

        llScore = (LinearLayout) findViewById(R.id.ll_score);
    }

    @Override
    public void initListener() {
        llScore.setOnClickListener(this);

    }

    @Override
    public void initData() {
        mTitleBar.setTitle("电影详情");

        getMovieDetail(vid);

    }

    @Override
    public void processClick(View v) {
        switch (v.getId()) {
            case R.id.ll_score:
                Intent scoreIntent = new Intent(this, MovieScoreActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("title", movieDetailBean.getVideoDetail().getTitle());
                bundle.putString("director", movieDetailBean.getVideoDetail().getDirector());
                bundle.putString("actors", movieDetailBean.getVideoDetail().getActors());
                bundle.putString("score", movieDetailBean.getVideoDetail().getScores());
                bundle.putString("id", movieDetailBean.getVideoDetail().getId());
                bundle.putString("cover_path",movieDetailBean.getVideoDetail().getCover_path());
                scoreIntent.putExtras(bundle);
                startActivity(scoreIntent);
                break;
        }

    }

    private void getMovieDetail(String vid) {
        List<Param> paramList = new ArrayList<>();
        Param key = new Param("key", SharedPreUtils.getString(StringContants.KEY));
        Param id = new Param("id", vid);
        paramList.add(key);
        paramList.add(id);
        HttpUtils.post(ConstantApi.VIDEODETAIL, paramList, new ResultCallback() {
            @Override
            public void onSuccess(Object response) {
                movieDetailBean = new Gson().fromJson(response.toString(), MovieDetailBean.class);
                Log.d("movieDeatil",response.toString());
                if (movieDetailBean.getResult() == 200) {
                    ImageLoaderUtils.display(MovieDetailActivity.this,"http://www.jingdian.party/"+movieDetailBean.getVideoDetail().getCover_path(),movieImg);
                    movie_title.setText(movieDetailBean.getVideoDetail().getTitle());
                    introduce.setText(Html.fromHtml(movieDetailBean.getVideoDetail().getSummary()).toString());
                    director.setText(movieDetailBean.getVideoDetail().getDirector());
                    actors.setText(movieDetailBean.getVideoDetail().getActors());
                    score.setText(movieDetailBean.getVideoDetail().getScores());
                }
            }

            @Override
            public void onFailure(Request request, Exception e) {

            }

            @Override
            public void onNoNetWork(String resultMsg) {

            }
        });
    }
}
