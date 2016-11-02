package com.looking.classicalparty.moudles.mine.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.looking.classicalparty.R;
import com.looking.classicalparty.moudles.mine.observer.ConcreteSubject;

import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;

/**
 * Created by xin on 2016/11/2.
 */

public class NicknameDialog extends Dialog {

    private EditText mEtNickname;
    private Button mBtnCommit;

    public NicknameDialog(Context context) {
        this(context, R.style.base_dialog_theme);
    }

    public NicknameDialog(Context context, int theme) {
        super(context, theme);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nick_name_dialog);
        mEtNickname = (EditText) findViewById(R.id.et_nickname);
        mBtnCommit = (Button) findViewById(R.id.btn_commit);
        initListener();
    }

    private void initListener() {
        mBtnCommit.setOnClickListener(new View.OnClickListener() {
            String nickname = mEtNickname.getText().toString().trim();

            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(nickname)) {
                    Crouton.makeText(getOwnerActivity(), "当前内容不能为空", Style.CONFIRM).show();
                } else {
                    ConcreteSubject.getInstance().notifyDataChange(nickname);
                    dismiss();
                }
            }
        });
    }
}
