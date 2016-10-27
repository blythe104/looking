package com.looking.classicalparty.moudles.about.view;

import android.view.View;

import com.looking.classicalparty.R;
import com.looking.classicalparty.lib.base.activity.BaseActivity;
import com.looking.classicalparty.lib.ui.TitleBar;

public class AboutUsActivity extends BaseActivity {


    private TitleBar mTitleBar;

    @Override
    public void initView() {
        setContentView(R.layout.activity_about_us);
        mTitleBar = (TitleBar) findViewById(R.id.title_bar);
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {
        mTitleBar.setTitle("关于我们");
        mTitleBar.setOnClickListener(new TitleBar.OnClickListener() {
            @Override
            public void OnLeftClick() {
                finish();
            }

            @Override
            public void OnRightClick() {

            }
        });
    }

    @Override
    public void processClick(View v) {

    }
}
