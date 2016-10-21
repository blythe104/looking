package com.looking.classicalparty.moudles.main.fragment;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
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
    private Toolbar mToolBar;

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mine_layout, null);
        mToolBar = (Toolbar) view.findViewById(R.id.toolbar);

        mCustomMenu = (CustomerMenuView) view.findViewById(R.id.custom_menu);
        mCustomMenu.addDivider().addItem(R.mipmap.ic_person_msg, "个人信息", "").addItem(R.mipmap.ic_secure_setting,
                "安全设置", "").addDivider().addItem(R.mipmap.ic_feedback, "意见反馈", "").addItem(R.mipmap.ic_about_us,
                "关于我们", "").addItem(R.mipmap.ic_app_version, "版本信息", "").build();
        return view;

    }

    @Override
    protected void loadData() {
        super.loadData();
        mToolBar.setTitle("Classical");
    }

}
