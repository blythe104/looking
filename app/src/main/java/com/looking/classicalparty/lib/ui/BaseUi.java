package com.looking.classicalparty.lib.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.looking.classicalparty.lib.listener.IUIListener;


/**
 * Created by  chuangtou on 16/7/18.
 * 国丞创投
 */
public abstract class BaseUi extends LinearLayout implements IUIListener {

    protected LayoutInflater mInflater;
    protected View rootView;

    public BaseUi(Context context) {
        super(context);
        mInflater = LayoutInflater.from(getContext());
        initView();
    }

    public BaseUi(Context context, AttributeSet attrs) {
        super(context, attrs);
        mInflater = LayoutInflater.from(getContext());
        initView();
    }

    public BaseUi(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mInflater = LayoutInflater.from(getContext());
        initView();
    }
}
