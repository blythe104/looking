package com.looking.classicalparty.moudles.music.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.looking.classicalparty.R;
import com.looking.classicalparty.lib.constants.StringContants;
import com.looking.classicalparty.lib.utils.ImageLoaderUtils;
import com.looking.classicalparty.moudles.music.service.PlayerService;

public class MusicDialog extends Dialog implements View.OnClickListener {
    
    private ImageButton mBtnListener;
    private ImageButton mBtnStop;
    
    private String path;
    private TextView musicName;
    private ImageView ivClose;
    private ImageView ivMusicIcon;
    private Context context;
    private TextView tvSinger;
    
    public MusicDialog(Context context) {
        this(context, R.style.DialogTheme);
        this.context = context;
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
        ivMusicIcon = (ImageView) findViewById(R.id.iv_music_icon);
        tvSinger = (TextView) findViewById(R.id.tv_singer);
        
        
        ivClose.setOnClickListener(this);
        mBtnStop.setOnClickListener(this);
        mBtnListener.setOnClickListener(this);
        
        WindowManager m = getWindow().getWindowManager();
        Display d = m.getDefaultDisplay();
        WindowManager.LayoutParams p = getWindow().getAttributes();
        p.width = d.getWidth(); //设置dialog的宽度为当前手机屏幕的宽度
        getWindow().setAttributes(p);
        
    }
    
    
    public void initMusicData(String imgPath, String path, String title, String singer) {
        this.path = path;
        ImageLoaderUtils.display(context, imgPath, R.mipmap.mine_two, ivMusicIcon);
        musicName.setText(title);
        tvSinger.setText(singer);
    }
    
    
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_listener: {
                Intent intent = new Intent();
                intent.putExtra("path", path);
                intent.putExtra("MSG", StringContants.PLAY_MSG);
                intent.setClass(getContext(), PlayerService.class);
                getContext().startService(intent);       //启动服务
            }
            break;
            case R.id.btn_stop: {
                Intent intent = new Intent();
                intent.putExtra("path", path);
                intent.putExtra("MSG", StringContants.STOP_MSG);
                intent.setClass(getContext(), PlayerService.class);
                getContext().startService(intent);
            }
            break;
            //            case R.id.iv_close:
            //                dismiss();
            //                break;
        }
        
    }
    
}
