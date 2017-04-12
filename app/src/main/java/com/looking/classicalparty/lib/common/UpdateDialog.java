package com.looking.classicalparty.lib.common;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.looking.classicalparty.R;

/**
 * Created by xin on 2016/10/24.
 */
public class UpdateDialog extends Dialog implements View.OnClickListener {
    private Button btnUpdate;
    private Button btnCancel;
    private UpdateListener listener;
    
    public UpdateDialog(Context context) {
        this(context, R.style.base_dialog_theme);
    }
    
    public UpdateDialog(Context context, int themeResId) {
        super(context, themeResId);
    }
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tips_dialog_layout);
        btnUpdate = (Button) findViewById(R.id.btn_update);
        btnCancel = (Button) findViewById(R.id.btn_cancel);
        btnUpdate.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
    }
    
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_update:
                if (listener != null) {
                    listener.update();
                }
                break;
            case R.id.btn_cancel:
                dismiss();
                break;
        }
        
    }
    
    public void setUpdateListener(UpdateListener listener) {
        this.listener = listener;
    }
    
    public interface tipsListener {
        void getAppData();
        
        
    }
    
    public interface UpdateListener {
        public void update();
        
        public void closeDialog();
    }
}
