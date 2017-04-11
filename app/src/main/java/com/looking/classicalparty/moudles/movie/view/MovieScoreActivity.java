package com.looking.classicalparty.moudles.movie.view;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
import com.looking.classicalparty.moudles.movie.bean.commentBean;
import com.squareup.okhttp.Request;

import java.util.ArrayList;
import java.util.List;

import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;

public class MovieScoreActivity extends BaseActivity {
    private TitleBar titleBar;
    private TextView movie_title;
    private TextView director;
    private TextView introduce;
    private TextView actors;
    private TextView score;
    private Bundle bundle;
    private String vid;
    private EditText movie_score;
    private ImageView movieImg;

    @Override
    public void initView() {
        setContentView(R.layout.activity_movie_score);
        bundle = getIntent().getExtras();
        titleBar = (TitleBar) findViewById(R.id.title_bar);
        movie_title = (TextView) findViewById(R.id.movie_title);
        director = (TextView) findViewById(R.id.director);
        introduce = (TextView) findViewById(R.id.introduce);
        actors = (TextView) findViewById(R.id.actors);
        score = (TextView) findViewById(R.id.score);
        movie_score = (EditText) findViewById(R.id.movie_score);
        movieImg = (ImageView) findViewById(R.id.movie_img);
        vid = bundle.get("id").toString();
    }

    @Override
    public void initListener() {
        titleBar.setOnClickListener(new TitleBar.OnClickListener() {
            @Override
            public void OnLeftClick() {
                finish();
            }

            @Override
            public void OnRightClick() {
                //提交影评
                setMovieScore();
            }
        });

    }

    @Override
    public void initData() {
        titleBar.setTitle("影评", "完成");
        movie_title.setText(bundle.get("title").toString());
        director.setText(bundle.get("director").toString());
        actors.setText(bundle.get("actors").toString());
        score.setText(bundle.get("score").toString());
        ImageLoaderUtils.display(MovieScoreActivity.this,"http://www.jingdian.party/"+bundle.get("cover_path").toString(),movieImg);


    }

    @Override
    public void processClick(View v) {

    }

    private void setMovieScore() {
        String movieContent = movie_score.getText().toString();
        if (TextUtils.isEmpty(movieContent)) {
            Crouton.makeText(MovieScoreActivity.this, "影评内容不能为空哦！", Style.CONFIRM).show();
        } else {
            List<Param> paramList = new ArrayList<>();
            Param key = new Param("key", SharedPreUtils.getString(StringContants.KEY));
            Param token = new Param("token", SharedPreUtils.getString(StringContants.TOKEN));
            Param id = new Param("vid", vid);
            Param commentId = new Param("commentId", "0");
            Param content = new Param("content", movieContent);
            paramList.add(key);
            paramList.add(id);
            paramList.add(commentId);
            paramList.add(content);
            paramList.add(token);
            HttpUtils.post(ConstantApi.COMMENT, paramList, new ResultCallback() {
                @Override
                public void onSuccess(Object response) {
                    commentBean commentBean = new Gson().fromJson(response.toString(), commentBean.class);
                    Log.d("commentBean--", response.toString());
                    if (commentBean.getResult() == 200) {
//                        Crouton.makeText(MovieScoreActivity.this, "影评提交成功了", Style.CONFIRM).show();
                        Toast.makeText(MovieScoreActivity.this, "影评提交成功了", Toast.LENGTH_SHORT).show();
                        
                        finish();
                    } else {
                        Toast.makeText(MovieScoreActivity.this, commentBean.getResultMsg(), Toast.LENGTH_SHORT).show();
//                        Crouton.makeText(MovieScoreActivity.this, "oh,sorry~~评论失败喽", Style.CONFIRM).show();
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
}
