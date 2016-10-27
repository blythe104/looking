package com.looking.classicalparty.moudles.main.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.looking.classicalparty.R;
import com.looking.classicalparty.lib.base.fragment.BaseFragment;
import com.looking.classicalparty.moudles.movie.adapter.SimpleFragmentPagerAdapter;

/**
 * Created by xin on 2016/10/19.
 */
public class MovieFragment extends BaseFragment implements TabLayout.OnTabSelectedListener {

    private TabLayout mTab;
    private ViewPager mViewPager;

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.movie_fragment_layout, null);
        mTab = (TabLayout) view.findViewById(R.id.tab);
        mViewPager = (ViewPager) view.findViewById(R.id.viewpager);
        SimpleFragmentPagerAdapter adapter = new SimpleFragmentPagerAdapter(getFragmentManager(), getActivity());
        mViewPager.setAdapter(adapter);
        mTab.setOnTabSelectedListener(this);
        mTab.setupWithViewPager(mViewPager);
        mTab.setTabMode(TabLayout.MODE_FIXED);
                mTab.setupWithViewPager(mViewPager);
        return view;

    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        //设置viewpager与tab的位置同步变化
        mViewPager.setCurrentItem(tab.getPosition());

    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}
