package com.looking.classicalparty.lib.ui;

import android.content.Context;
import android.os.CountDownTimer;
import android.util.AttributeSet;
import android.widget.Button;

import com.looking.classicalparty.R;

/**
 * Created by xin on 2016/11/21.
 * 倒计时按钮
 */

public class CountDownButton extends Button {

    private CountDownTimer countDown;

    public CountDownButton(Context context) {
        super(context);
        initView();
    }


    public CountDownButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public CountDownButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        this.setText("获取验证码");
        this.setBackground(getResources().getDrawable(R.drawable.shape_my_task_center_btn));
        this.setTextColor(getResources().getColor(R.color.colorAccent));
        this.setTextSize(13);
        countDown = new CountDownTimer(60000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                CountDownButton.this.setClickable(false);
                CountDownButton.this.setText(millisUntilFinished / 1000 + "s");
                CountDownButton.this.setTextColor(getResources().getColor(R.color
                        .divider_gray));
                CountDownButton.this.setBackground(getResources().getDrawable(R.drawable
                        .shape_my_get_smscode_btn));
            }

            @Override
            public void onFinish() {
                CountDownButton.this.setText("获取验证码");
                CountDownButton.this.setClickable(true);
                CountDownButton.this.setBackgroundResource(R.drawable.shape_my_task_center_btn);
                CountDownButton.this.setTextColor(0xff2493e7);
            }
        };
    }

    public void start() {
        countDown.start();
    }


}
