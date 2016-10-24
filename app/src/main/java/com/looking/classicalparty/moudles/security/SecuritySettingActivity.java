package com.looking.classicalparty.moudles.security;

import android.content.Intent;
import android.view.View;

import com.looking.classicalparty.R;
import com.looking.classicalparty.lib.base.activity.BaseActivity;
import com.looking.classicalparty.lib.constants.StringContants;
import com.looking.classicalparty.lib.utils.SharedPreUtils;
import com.looking.classicalparty.lib.widget.CustomerMenuView;
import com.looking.classicalparty.lib.widget.ItemData;

public class SecuritySettingActivity extends BaseActivity {


    private CustomerMenuView mCustomSecurity;

    @Override
    public void initView() {
        setContentView(R.layout.activity_security_setting);
        mCustomSecurity = (CustomerMenuView) findViewById(R.id.custom_security);
        // TODO: 2016/10/23 图片需要更换
        mCustomSecurity.addDivider().addItem(R.mipmap.ic_person_msg, "密码修改", "change").addItem(R.mipmap
                .ic_person_msg, "清除缓存", "del").addItem(R.mipmap.ic_person_msg, "去评分", "score").addDivider().addItem(R
                .mipmap.img_btn_login, "退出登录", "exit").build();
        mCustomSecurity.setItemClickListener(new CustomerMenuView.OnItemListener() {
            @Override
            public void itemClick(View v) {
                switch (((ItemData) v.getTag()).flag) {
                    case "exit":
                        SharedPreUtils.saveString(StringContants.TOKEN, "");
                        finish();
                        break;
                    case "change":
                        startActivity(new Intent(SecuritySettingActivity.this, ChangeLoginPwdActivity.class));
                        break;
                }

            }
        });
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void processClick(View v) {

    }
}
