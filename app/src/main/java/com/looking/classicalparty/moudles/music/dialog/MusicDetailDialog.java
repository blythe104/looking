package com.looking.classicalparty.moudles.music.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.looking.classicalparty.R;
import com.looking.classicalparty.moudles.music.view.SummaryActivity;

public class MusicDetailDialog extends Dialog implements View.OnClickListener {
    
    private TextView music_name;
    private TextView tv_singer;
    private TextView look_detail;
    private String summary;
    
    
    public MusicDetailDialog(Context context) {
        this(context, R.style.DialogTheme);
    }
    
    public MusicDetailDialog(Context context, int themeResId) {
        super(context, themeResId);
    }
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.music_detail_dialog);
        music_name = (TextView) findViewById(R.id.music_name);
        tv_singer = (TextView) findViewById(R.id.tv_singer);
        look_detail = (TextView) findViewById(R.id.look_detail);
        look_detail.setOnClickListener(this);
    }
    
    
    public void initMusicData(String title, String singer, String summary) {
        music_name.setText(title);
        tv_singer.setText(singer);
        this.summary=summary;
    }
    
    
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.look_detail:
                Intent intent=new Intent(getContext(), SummaryActivity.class);
                intent.putExtra("summary",summary);
                getContext().startActivity(intent);
                break;
        }
        
    }
    
    
}
