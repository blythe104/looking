package com.looking.classicalparty.moudles.music.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.looking.classicalparty.R;
import com.looking.classicalparty.lib.constants.StringContants;
import com.looking.classicalparty.moudles.music.service.PlayerService;

public class MusicDialog extends Dialog implements View.OnClickListener {
    
    private ImageButton mBtnListener;
    private ImageButton mBtnStop;
    
    private String path;
    private String title;
    private TextView musicName;
    private ImageView ivClose;
    
    public MusicDialog(Context context) {
        this(context, R.style.DialogTheme);
    }
    
    public MusicDialog(Context context, int themeResId) {
        super(context, themeResId);
    }
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listener_music_item);
        mBtnListener = (ImageButton) findViewById(R.id.btn_listener);
        mBtnStop = (ImageButton) findViewById(R.id.btn_stop);
        musicName = (TextView) findViewById(R.id.music_name);
        ivClose = (ImageView) findViewById(R.id.iv_close);
        
        
        musicName.setText(title);
        ivClose.setOnClickListener(this);
        mBtnStop.setOnClickListener(this);
        mBtnListener.setOnClickListener(this);
        
    }
    
    public void initMusicData(String path, String title) {
        this.path = path;
        this.title = title;
    }
    
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_listener:
                Intent intent = new Intent();
                intent.putExtra("path", path);
                intent.putExtra("MSG", StringContants.PLAY_MSG);
                intent.setClass(getContext(), PlayerService.class);
                getContext().startService(intent);       //启动服务
                break;
            case R.id.btn_stop:
                Intent intentstop = new Intent();
                intentstop.putExtra("path", path);
                intentstop.putExtra("MSG", StringContants.STOP_MSG);
                intentstop.setClass(getContext(), PlayerService.class);
                getContext().startService(intentstop);
                break;
            case R.id.iv_close:
                dismiss();
                break;
        }
        
    }
    
}
