package com.looking.classicalparty.moudles.main.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.looking.classicalparty.R;
import com.looking.classicalparty.lib.base.fragment.BaseFragment;
import com.looking.classicalparty.lib.widget.CustomerMenuView;

/**
 * Created by xin on 2016/10/19.
 */
public class MineFragment extends BaseFragment {

    private CustomerMenuView mCustomMenu;

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mine_layout, null);
        mCustomMenu = (CustomerMenuView) view.findViewById(R.id.custom_menu);
        mCustomMenu.addItem(R.mipmap.ic_radio,"我的","");
        mCustomMenu.addItem(R.mipmap.ic_radio,"我的","");
        mCustomMenu.addItem(R.mipmap.ic_radio,"我的","");
        mCustomMenu.addItem(R.mipmap.ic_radio,"我的","");
        return view;

    }

}
