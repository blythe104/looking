package com.looking.classicalparty.lib.base.dialog;

import android.app.Dialog;
import android.content.Context;

import com.looking.classicalparty.R;

/**
 * Created by xin on 2016/10/18.
 */
public class BaseDialog extends Dialog {
    public BaseDialog(Context context) {
        super(context, R.style.base_dialog_theme);
    }
}
