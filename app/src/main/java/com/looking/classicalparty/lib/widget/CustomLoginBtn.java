package com.looking.classicalparty.lib.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.looking.classicalparty.R;

/**
 * Created by xin on 2016/10/20.
 */
public class CustomLoginBtn extends LinearLayout implements CustomBaseInterface {

    private ImageView ivCus;
    private TextView tvCus;

    public CustomLoginBtn(Context context) {
        super(context);
        initView(context);
    }

    public CustomLoginBtn(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public CustomLoginBtn(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        View.inflate(context, R.layout.custom_btn_style, this);
        setBackgroundResource(R.drawable.selector_login_btn);
        ivCus = (ImageView) findViewById(R.id.iv_cus);
        tvCus = (TextView) findViewById(R.id.tv_cus);
    }

    //设置图标和内容
    @Override
    public void setMenuName(String menuName) {
        tvCus.setText(menuName);
    }

    @Override
    public void setIcon(int rid) {
        ivCus.setImageResource(rid);
    }


}
