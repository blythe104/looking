package com.looking.classicalparty.lib.common;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.looking.classicalparty.R;

/**
 * Created by xin on 2016/10/24.
 */
public class TipDialog extends Dialog implements View.OnClickListener {
    private tipsListener listener;
    private TextView mTvTitle;
    private Button mBtnTipsOne;
    private String title;
    private String desc;
    private boolean isShow;
    private TextView mTvTips;
    private Button mBtnTipTwo;

    public TipDialog(Context context) {
        this(context, R.style.base_dialog_theme);
    }

    public TipDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tips_dialog_layout);
        mBtnTipsOne = (Button) findViewById(R.id.btn_tips_one);
        mBtnTipTwo = (Button) findViewById(R.id.btn_tips_two);
        mBtnTipsOne.setOnClickListener(this);
        mBtnTipTwo.setOnClickListener(this);
    }

    public void setTipsListener(tipsListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_tips_one:
                if (listener != null) {
                    listener.getAppData();
                }
                break;
            case R.id.btn_tips_two:
                dismiss();
                break;
        }

    }

    public interface tipsListener {
        void getAppData();

    }
}
