package com.looking.classicalparty.moudles.security;

import android.view.View;

import com.looking.classicalparty.R;
import com.looking.classicalparty.lib.base.activity.BaseActivity;
import com.looking.classicalparty.lib.ui.TitleBar;

import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;

public class ChangeLoginPwdActivity extends BaseActivity {
    
    
    private TitleBar mTitle;
    
    @Override
    public void initView() {
        setContentView(R.layout.activity_change_login_pwd);
        mTitle = (TitleBar) findViewById(R.id.title_bar);
    }
    
    @Override
    public void initListener() {
        mTitle.setOnClickListener(new TitleBar.OnClickListener() {
            @Override
            public void OnLeftClick() {
                finish();
                
            }
            
            @Override
            public void OnRightClick() {
                // TODO: 2016/10/28 添加修改密码接口 
                Crouton.makeText(ChangeLoginPwdActivity.this, "修改密码完成", Style.CONFIRM).show();
            }
        });
        
    }
    
    @Override
    public void initData() {
        mTitle.setTitle("修改密码", "完成");
    }
    
    @Override
    public void processClick(View v) {
        
    }
}
