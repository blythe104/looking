package com.looking.classicalparty.moudles.main.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.looking.classicalparty.R;
import com.looking.classicalparty.lib.base.fragment.BaseFragment;
import com.looking.classicalparty.lib.constants.ConstantApi;
import com.looking.classicalparty.lib.constants.StringContants;
import com.looking.classicalparty.lib.http.HttpUtils;
import com.looking.classicalparty.lib.http.Param;
import com.looking.classicalparty.lib.http.ResultCallback;
import com.looking.classicalparty.lib.utils.SharedPreUtils;
import com.looking.classicalparty.moudles.movie.adapter.SimpleFragmentPagerAdapter;
import com.looking.classicalparty.moudles.movie.bean.CategoryBean;
import com.squareup.okhttp.Request;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xin on 2016/10/19.
 */
public class MovieFragment extends BaseFragment implements TabLayout.OnTabSelectedListener {

    List<CategoryBean.CategoryEntity> categoryBeen;
    private TabLayout mTab;
    private ViewPager mViewPager;
    private SimpleFragmentPagerAdapter fragmentPagerAdapter;

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.movie_fragment_layout, null);
        mTab = (TabLayout) view.findViewById(R.id.tab);
        mViewPager = (ViewPager) view.findViewById(R.id.viewpager);
        categoryBeen = new ArrayList<>();

        fragmentPagerAdapter = new SimpleFragmentPagerAdapter(getFragmentManager(), getActivity(), categoryBeen);
        mViewPager.setAdapter(fragmentPagerAdapter);
        mTab.setOnTabSelectedListener(this);
        mTab.setupWithViewPager(mViewPager);

        return view;

    }

    @Override
    protected void loadData() {
        super.loadData();
        getCategory();
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

    /**
     * 获取类别列表
     */
    private void getCategory() {
        List<Param> paramList = new ArrayList<>();
        Param key = new Param("key", SharedPreUtils.getString(StringContants.KEY));
        paramList.add(key);

        HttpUtils.post(ConstantApi.CATEGORY, paramList, new ResultCallback() {
            @Override
            public void onSuccess(Object response) {
                CategoryBean categoryBean = new Gson().fromJson(response.toString(), CategoryBean.class);
                if (categoryBean.getResult() == 200) {
                    categoryBeen.addAll(categoryBean.getCategory());
                }
                fragmentPagerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Request request, Exception e) {

            }

            @Override
            public void onNoNetWork(String resultMsg) {

            }
        });
    }
}
