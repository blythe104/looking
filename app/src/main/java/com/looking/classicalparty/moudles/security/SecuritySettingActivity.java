package com.looking.classicalparty.moudles.security;

import android.view.View;

import com.looking.classicalparty.R;
import com.looking.classicalparty.lib.base.activity.BaseActivity;
import com.looking.classicalparty.lib.constants.StringContants;
import com.looking.classicalparty.lib.ui.TitleBar;
import com.looking.classicalparty.lib.utils.DataCleanUtils;
import com.looking.classicalparty.lib.utils.SharedPreUtils;
import com.looking.classicalparty.lib.widget.CustomerMenuView;
import com.looking.classicalparty.lib.widget.ItemData;

public class SecuritySettingActivity extends BaseActivity {
    
    
    private static final int EXIT = 1001;
    private CustomerMenuView mCustomSecurity;
    private TitleBar mTitleBar;
    private String fileSize;
    
    @Override
    public void initView() {
        setContentView(R.layout.activity_security_setting);
        mCustomSecurity = (CustomerMenuView) findViewById(R.id.custom_security);
        mTitleBar = (TitleBar) findViewById(R.id.title_bar);
        try {
            fileSize = DataCleanUtils.getTotalCacheSize(getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }
        // TODO: 2016/10/23 图片需要更换
        mCustomSecurity.addDivider()
                // .addItem(R.mipmap.ic_change_pwd, "密码修改", "change")//
                //.addItem(R.mipmap.ic_clean, "清除缓存", "del", fileSize)//
                .addItem(R.mipmap.ic_give_score, "去评分", "score")//
                .addDivider()//
                .addItem(R.mipmap.ic_clean, "退出登录", "exit")//
                .build();
        mCustomSecurity.setItemClickListener(new CustomerMenuView.OnItemListener() {
            @Override
            public void itemClick(View v) {
                switch (((ItemData) v.getTag()).flag) {
                    case "exit":
                        SharedPreUtils.saveString(StringContants.TOKEN, "");
                        setResult(EXIT);
                        finish();
                        break;
//                    case "change":
                    //                        startActivity(new Intent(SecuritySettingActivity.this,
                    // ChangeLoginPwdActivity.class));
                    //                        break;
                    //                    case "del":
                    //                        // TODO: 2016/10/26 需用的缓存大小,需要将此数据更新到Menu中
                    //
                    //                        DataCleanUtils.cleanApplicationData(getApplicationContext());
                    //                        DataCleanUtils.cleanSharedPreference(getApplicationContext());
                    //                        Crouton.makeText(SecuritySettingActivity.this, "清除缓存成功", Style.CONFIRM)
                    // .show();
                    //                        break;
                }
                
            }
            
        });
        mTitleBar.setOnClickListener(new TitleBar.OnClickListener() {
            @Override
            public void OnLeftClick() {
                finish();
            }
            
            @Override
            public void OnRightClick() {
                
            }
        });
    }
    
    @Override
    public void initListener() {
        
    }
    
    @Override
    public void initData() {
        mTitleBar.setTitle("安全设置");
    }
    
    @Override
    public void processClick(View v) {
        
    }
}
