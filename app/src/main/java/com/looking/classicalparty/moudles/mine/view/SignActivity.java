package com.looking.classicalparty.moudles.mine.view;

import android.view.View;

import com.looking.classicalparty.R;
import com.looking.classicalparty.lib.base.activity.BaseActivity;
import com.looking.classicalparty.lib.ui.TitleBar;

import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;

public class SignActivity extends BaseActivity {


    private TitleBar mTitleBar;

    @Override
    public void initView() {
        setContentView(R.layout.activity_sign);
        mTitleBar = (TitleBar) findViewById(R.id.title_bar);
    }

    @Override
    public void initListener() {
        mTitleBar.setOnClickListener(new TitleBar.OnClickListener() {
            @Override
            public void OnLeftClick() {
                finish();

            }

            @Override
            public void OnRightClick() {
                Crouton.makeText(SignActivity.this, "哒哒哒，签名已显示咯", Style.CONFIRM).show();
            }
        });

    }

    @Override
    public void initData() {
        mTitleBar.setTitle("签名", "完成");

    }

    @Override
    public void processClick(View v) {

    }
}
