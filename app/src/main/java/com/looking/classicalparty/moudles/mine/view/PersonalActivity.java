package com.looking.classicalparty.moudles.mine.view;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.looking.classicalparty.R;
import com.looking.classicalparty.lib.base.activity.BaseActivity;
import com.looking.classicalparty.lib.widget.CircleImageView;
import com.looking.classicalparty.lib.widget.CustomerMenuView;

public class PersonalActivity extends BaseActivity {


    private FrameLayout mFrBack;
    private TextView mTvTitle;
    private CustomerMenuView mCustomMenu;
    private CircleImageView circleImageView;

    @Override
    public void initView() {
        setContentView(R.layout.activity_person);
        circleImageView = new CircleImageView(this);
        initTitle();
        mCustomMenu = (CustomerMenuView) findViewById(R.id.custom_menu);
        mCustomMenu.addDivider().addItem(R.mipmap.ic_nickname1, "昵称", "twory", "nickname", false).addItem(R.mipmap
                .ic_sex, "性别", "男", "sex", false).addItem(R.mipmap.ic_birthday, "生日", "202020", "birthday", false)
                .addItem(R.mipmap.ic_sign, "个性签名", "909090909", "sign", false).build();
    }

    private void initTitle() {
        mFrBack = (FrameLayout) findViewById(R.id.fr_back);
        mTvTitle = (TextView) findViewById(R.id.tv_title);
        mTvTitle.setText("个人资料");
    }

    @Override
    public void initListener() {
        mFrBack.setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void processClick(View v) {
        switch (v.getId()) {
            case R.id.fr_back:
                finish();
                break;
        }

    }
}
