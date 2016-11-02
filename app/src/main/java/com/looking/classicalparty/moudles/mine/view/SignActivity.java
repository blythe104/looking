package com.looking.classicalparty.moudles.mine.view;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.looking.classicalparty.R;
import com.looking.classicalparty.lib.base.activity.BaseActivity;
import com.looking.classicalparty.lib.http.HttpUtils;
import com.looking.classicalparty.lib.http.Param;
import com.looking.classicalparty.lib.http.ResultCallback;
import com.looking.classicalparty.lib.ui.TitleBar;
import com.looking.classicalparty.lib.utils.SharedPreUtils;
import com.squareup.okhttp.Request;

import java.util.ArrayList;
import java.util.List;

import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;

public class SignActivity extends BaseActivity {


    private TitleBar mTitleBar;
    private EditText mEtSign;
    private TextView mTvNum;
    TextWatcher watcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            int number = s.length();
            mTvNum.setText("" + number);
            int editStart = mEtSign.getSelectionStart();
            int editEnd = mEtSign.getSelectionEnd();
            if (number > 20) {
                Crouton.makeText(SignActivity.this, "你输入的字数已经超过了限制！", Style.CONFIRM).show();
                s.delete(editStart - 1, editEnd);
                int tempSelection = editEnd;
                mEtSign.setText(s);
                mEtSign.setSelection(tempSelection);
            }

        }
    };

    @Override
    public void initView() {
        setContentView(R.layout.activity_sign);
        mTitleBar = (TitleBar) findViewById(R.id.title_bar);
        mEtSign = (EditText) findViewById(R.id.et_sign);
        mTvNum = (TextView) findViewById(R.id.tv_textnum);
        mEtSign.addTextChangedListener(watcher);
    }

    @Override
    public void initListener() {
        mTitleBar.setOnClickListener(new TitleBar.OnClickListener() {
            @Override
            public void OnLeftClick() {
                finish();
            }

            @Override
            public void OnRightClick() {
                Crouton.makeText(SignActivity.this, "哒哒哒，签名已显示咯", Style.CONFIRM).show();
                // TODO: 2016/11/2 调用接口
                //                saveSign(mEtSign.getText().toString().trim());
                SharedPreUtils.saveString("sign", mEtSign.getText().toString().trim());
            }
        });

    }

    /**
     * \
     * 保存用户的签名
     *
     * @param sign
     */
    private void saveSign(String sign) {
        List<Param> list = new ArrayList<>();
        Param content = new Param("sign", sign);
        list.add(content);
        HttpUtils.post("sign", list, new ResultCallback() {
            @Override
            public void onSuccess(Object response) {

            }

            @Override
            public void onFailure(Request request, Exception e) {

            }

            @Override
            public void onNoNetWork(String resultMsg) {

            }
        });
    }

    @Override
    public void initData() {
        mTitleBar.setTitle("签名", "完成");

    }

    @Override
    public void processClick(View v) {

    }

}
