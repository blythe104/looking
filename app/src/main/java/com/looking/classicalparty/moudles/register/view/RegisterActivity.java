package com.looking.classicalparty.moudles.register.view;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.looking.classicalparty.R;
import com.looking.classicalparty.lib.base.activity.BaseActivity;

public class RegisterActivity extends BaseActivity {


    private TextView mTvNext;

    @Override
    public void initView() {
        setContentView(R.layout.activity_register);
        mTvNext = (TextView) findViewById(R.id.tv_next);
    }

    @Override
    public void initListener() {
        mTvNext.setOnClickListener(this);

    }

    @Override
    public void initData() {

    }

    @Override
    public void processClick(View v) {
        switch (v.getId()) {
            case R.id.tv_next:
                startActivity(new Intent(this, RegisterDetailActivity.class));
                break;
        }

    }
}
