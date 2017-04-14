package com.looking.classicalparty.moudles.music.view;

import android.text.Html;
import android.view.View;
import android.widget.TextView;

import com.looking.classicalparty.R;
import com.looking.classicalparty.lib.base.activity.BaseActivity;
import com.looking.classicalparty.lib.ui.TitleBar;

public class SummaryActivity extends BaseActivity {
    
    private TextView tv_summary;
    private String summary;
    private TitleBar titleBar;
    
    @Override
    public void initView() {
        setContentView(R.layout.activity_summary);
        summary = getIntent().getStringExtra("summary");
        titleBar = (TitleBar) findViewById(R.id.title_bar);
        
        tv_summary = (TextView) findViewById(R.id.summary);
        
    }
    
    @Override
    public void initListener() {
        
    }
    
    @Override
    public void initData() {
        titleBar.setTitle("歌词");
        tv_summary.setText(Html.fromHtml(summary).toString());
    }
    
    @Override
    public void processClick(View v) {
        
    }
}
