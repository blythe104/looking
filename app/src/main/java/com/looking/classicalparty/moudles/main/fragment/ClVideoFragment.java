package com.looking.classicalparty.moudles.main.fragment;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.looking.classicalparty.lib.base.fragment.BaseFragment;

/**
 * Created by xin on 2016/10/19.
 */
public class ClVideoFragment extends BaseFragment {
    @Override
    public View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //布局测试
        LinearLayout linearLayout = new LinearLayout(getContext());
        linearLayout.setMinimumHeight(100);
        linearLayout.setMinimumWidth(100);
        TextView textView = new TextView(getContext());
        textView.setText("hello video");
        linearLayout.addView(textView);
        linearLayout.setGravity(Gravity.CENTER);
        return linearLayout;

    }
}
