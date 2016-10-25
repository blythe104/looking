package com.looking.classicalparty.moudles.mine.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.looking.classicalparty.R;
import com.looking.classicalparty.lib.base.fragment.BaseFragment;
import com.looking.classicalparty.lib.widget.CustomerMenuView;
import com.looking.classicalparty.lib.widget.ItemData;
import com.looking.classicalparty.moudles.feedback.FeedBackActivity;
import com.looking.classicalparty.moudles.security.SecuritySettingActivity;

import static com.looking.classicalparty.R.id.toolbar;

/**
 * Created by xin on 2016/10/19.
 */
public class MineFragment extends BaseFragment {

    private CustomerMenuView mCustomMenu;
    private Toolbar mToolBar;

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mine_layout, null);
        mToolBar = (Toolbar) view.findViewById(toolbar);

        mCustomMenu = (CustomerMenuView) view.findViewById(R.id.custom_menu);
        mCustomMenu.addDivider().addItem(R.mipmap.ic_person_msg, "个人信息", "personmsg").addItem(R.mipmap
                .ic_secure_setting, "安全设置", "security").addDivider().addItem(R.mipmap.ic_feedback, "意见反馈",
                "feedback").addItem(R.mipmap.ic_about_us, "关于我们", "aboutus").addItem(R.mipmap.ic_app_version, "版本信息",
                "version").build();
        mCustomMenu.setItemClickListener(new CustomerMenuView.OnItemListener() {
            @Override
            public void itemClick(View v) {
                switch (((ItemData) v.getTag()).flag) {
                    case "personmsg":
                        break;
                    case "security":
                        startActivity(new Intent(getActivity(), SecuritySettingActivity.class));
                        break;
                    case "feedback":
                        startActivity(new Intent(getActivity(), FeedBackActivity.class));
                        break;
                    case "aboutus":
                        break;
                    case "version":
                        break;
                }

            }
        });

        return view;

    }

    @Override
    protected void loadData() {
        super.loadData();
        mToolBar.setTitle("Classical");
        mToolBar.setTitleTextColor(Color.parseColor("#FFFFFF"));
    }


}
