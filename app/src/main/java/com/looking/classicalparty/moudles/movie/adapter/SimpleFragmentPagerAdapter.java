package com.looking.classicalparty.moudles.movie.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.looking.classicalparty.moudles.movie.bean.CategoryBean;
import com.looking.classicalparty.moudles.movie.fragment.PageFragment;

import java.util.List;

public class SimpleFragmentPagerAdapter extends FragmentPagerAdapter {

    private Context context;
    private List<CategoryBean.CategoryEntity> categoryEntities;

    public SimpleFragmentPagerAdapter(FragmentManager fm, Context context, List<CategoryBean.CategoryEntity>
            categoryEntities) {
        super(fm);
        this.context = context;
        this.categoryEntities = categoryEntities;
    }

    @Override
    public Fragment getItem(int position) {
        return PageFragment.newInstance(position + 1);
    }

    @Override
    public int getCount() {
        return categoryEntities.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return categoryEntities.get(position).getCtitle();
    }
}
