package com.looking.classicalparty.moudles.music.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.looking.classicalparty.R;

public class MusicDetailDialog extends Dialog implements View.OnClickListener {
    
    private TextView music_name;
    
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
        
//        WindowManager m = getWindow().getWindowManager();
        //        Display d = m.getDefaultDisplay();
        //        WindowManager.LayoutParams p = getWindow().getAttributes();
        //        p.width = d.getWidth(); //设置dialog的宽度为当前手机屏幕的宽度
        //        getWindow().setAttributes(p);
        
    }
    
    
    
    public void initMusicData( String title) {
        music_name.setText(title);
    }
    
    
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            
        }
        
    }
    
    
}
