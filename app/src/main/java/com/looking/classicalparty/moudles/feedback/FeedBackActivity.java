package com.looking.classicalparty.moudles.feedback;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.looking.classicalparty.R;
import com.looking.classicalparty.lib.base.activity.BaseActivity;

public class FeedBackActivity extends BaseActivity {

    private EditText mEtFeedback;
    private TextWatcher watcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };
    private TextView mTvTextNum;

    @Override
    public void initView() {
        setContentView(R.layout.activity_feed_back);
        mEtFeedback = (EditText) findViewById(R.id.et_feedback);
        mEtFeedback.addTextChangedListener(watcher);
        mTvTextNum = (TextView) findViewById(R.id.tv_textnum);
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void processClick(View v) {

    }
}
