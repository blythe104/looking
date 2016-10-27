package com.looking.classicalparty.moudles.main.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.looking.classicalparty.R;
import com.looking.classicalparty.lib.base.fragment.BaseFragment;

/**
 * Created by xin on 2016/10/19.
 */
public class MovieFragment extends BaseFragment {

    private TabLayout mTab;

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        //布局测试
//        LinearLayout linearLayout = new LinearLayout(getContext());
//        linearLayout.setMinimumHeight(100);
//        linearLayout.setMinimumWidth(100);
//        TextView textView = new TextView(getContext());
//        textView.setText("hello video");
//        linearLayout.addView(textView);
//        linearLayout.setGravity(Gravity.CENTER);
        View view = inflater.inflate(R.layout.movie_fragment_layout, null);
        mTab = (TabLayout) view.findViewById(R.id.tab);
        return view;

    }
}
