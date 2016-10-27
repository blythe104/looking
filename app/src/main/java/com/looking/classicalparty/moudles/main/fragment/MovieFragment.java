package com.looking.classicalparty.moudles.main.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
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
    private ViewPager mViewPager;

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.movie_fragment_layout, null);
        mTab = (TabLayout) view.findViewById(R.id.tab);
        mViewPager = (ViewPager) view.findViewById(R.id.viewpager);
//        mTab.addTab(mTab.newTab().setText("hello").setIcon(R.mipmap.ic_mine));
//        mTab.addTab(mTab.newTab().setText("hello1").setIcon(R.mipmap.ic_find));
        mTab.addTab(mTab.newTab().setText("hello2"));
        mTab.addTab(mTab.newTab().setText("hello3"));
        mTab.addTab(mTab.newTab().setText("hello3"));
        mTab.addTab(mTab.newTab().setText("hello3"));
        mTab.addTab(mTab.newTab().setText("hello3"));
//        mTab.setupWithViewPager(mViewPager);
        return view;

    }
}
