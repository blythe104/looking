package com.looking.classicalparty.moudles.main.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.looking.classicalparty.R;
import com.looking.classicalparty.lib.base.fragment.BaseFragment;

/**
 * Created by xin on 2016/10/19.
 */
public class FindFragment extends BaseFragment {

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.find_fragment_layout,null);
        return view;
    }
}
