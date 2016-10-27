package com.looking.classicalparty.lib.ui;


import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import cn.book.keeping.R;
import cn.book.keeping.libs.listener.ITitleBar;


/**
 * Created by  chuangtou on 16/7/18.
 * 国丞创投
 */
public class TitleBar extends BaseUi implements ITitleBar, View.OnClickListener {
    private TextView titleRightTv;
    private TextView titleTv;
    private ImageView titleLeftIv;
    private OnClickListener listener;

    public TitleBar(Context context) {
        super(context);
    }

    public TitleBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TitleBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void initView() {
        rootView = mInflater.inflate(R.layout.layout_title_bar, null);
        this.addView(rootView);
        titleTv = (TextView) findViewById(R.id.tv_title);
        titleRightTv = (TextView) findViewById(R.id.tv_title_right);
        titleLeftIv = (ImageView) findViewById(R.id.iv_title_left);
        titleLeftIv.setOnClickListener(this);
    }

    @Override
    public void setTitle(String title, int Visibility) {
        titleLeftIv.setVisibility(Visibility);
        this.setTitle(title);
    }

    @Override
    public void setTitle(int title, int Visibility) {
        titleLeftIv.setVisibility(Visibility);
        this.setTitle(getResources().getString(title));
    }

    @Override
    public void setTitle(String title) {
        titleTv.setText(title);
    }

    @Override
    public void setTitle(int title) {
        titleTv.setText(getResources().getString(title));
    }

    @Override
    public void setTitle(String title, String rightTag) {
        this.setTitle(title);
        titleRightTv.setText(rightTag);
        titleRightTv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_title_left:
                if (listener != null) {
                    listener.OnLeftClick();
                } else {
                    ((Activity) getContext()).finish();
                }

                break;
            case R.id.tv_title_right:
                if (listener != null) {
                    listener.OnRightClick();
                }
                break;
        }
    }


    public void setOnClickListener(OnClickListener listener) {
        this.listener = listener;
    }

    public interface OnClickListener {
        void OnLeftClick();

        void OnRightClick();
    }
}
