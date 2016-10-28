package com.looking.classicalparty.moudles.mine.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import com.looking.classicalparty.R;

/**
 * Created by xin on 2016/10/28.
 */

public class ChooiseGenderDialog extends Dialog {

    public ChooiseGenderDialog(Context context) {
        this(context, R.style.base_dialog_theme);
    }

    public ChooiseGenderDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_gender_layout);
    }
}
