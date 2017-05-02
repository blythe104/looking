package com.looking.classicalparty.lib.base.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.looking.classicalparty.R;

public class LoadingDialog extends AlertDialog {

    private TextView tipsLoadingMsg;
    private String message = null;
    private RelativeLayout dialogBgRl;

    public LoadingDialog(Context context) {
        this(context, "请稍后...");
    }

    public LoadingDialog(Context context, String message) {
        super(context, R.style.DialogTheme);
        this.message = message;
        this.message = "";
        this.setCancelable(true);
        this.setCanceledOnTouchOutside(false);
    }

    public LoadingDialog(Context context, int theme, String message) {
        super(context, theme);
        this.message = message;
        this.setCancelable(true);
        this.setCanceledOnTouchOutside(false);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.dialog_loading);
        dialogBgRl = (RelativeLayout) findViewById(R.id.rl_dialog_bg);
        tipsLoadingMsg = (TextView) findViewById(R.id.tips_loading_msg);
        dialogBgRl.getBackground().setAlpha(120);
        dialogBgRl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoadingDialog.this.dismiss();
            }
        });
        tipsLoadingMsg.setText(this.message);
    }

    public void setText(String message) {
        this.message = message;
        tipsLoadingMsg.setText(this.message);
    }

    public void setText(int resId) {
        setText(getContext().getResources().getString(resId));
    }
}
