package com.looking.classicalparty.moudles.feedback;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.looking.classicalparty.R;
import com.looking.classicalparty.lib.base.activity.BaseActivity;
import com.looking.classicalparty.lib.constants.ConstantApi;
import com.looking.classicalparty.lib.http.HttpUtils;
import com.looking.classicalparty.lib.http.Param;
import com.looking.classicalparty.lib.http.ResultCallback;
import com.looking.classicalparty.lib.ui.TitleBar;
import com.squareup.okhttp.Request;

import java.util.ArrayList;
import java.util.List;

import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;

public class FeedBackActivity extends BaseActivity {

    private EditText mEtFeedback;
    private TextView mTvTextNum;
    private TextWatcher watcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            int number = editable.length();
            mTvTextNum.setText("" + number);
            int editStart = mEtFeedback.getSelectionStart();
            int editEnd = mEtFeedback.getSelectionEnd();
            if (number > 200) {
                Crouton.makeText(FeedBackActivity.this, "你输入的字数已经超过了限制！", Style.CONFIRM).show();
                editable.delete(editStart - 1, editEnd);
                int tempSelection = editEnd;
                mEtFeedback.setText(editable);
                mEtFeedback.setSelection(tempSelection);
            }

        }
    };
    private TitleBar mTitleBar;

    @Override
    public void initView() {
        setContentView(R.layout.activity_feed_back);
        mEtFeedback = (EditText) findViewById(R.id.et_feedback);
        mEtFeedback.addTextChangedListener(watcher);
        mTvTextNum = (TextView) findViewById(R.id.tv_textnum);
        mTitleBar = (TitleBar) findViewById(R.id.title_bar);
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {
        mTitleBar.setTitle("意见反馈", "完成");
        mTitleBar.setOnClickListener(new TitleBar.OnClickListener() {
            @Override
            public void OnLeftClick() {
                finish();
            }

            @Override
            public void OnRightClick() {
                // TODO: 2016/10/27 需要调用反馈接口
                String feedback = mEtFeedback.getText().toString().trim();
                if (TextUtils.isEmpty(feedback)) {
                    Crouton.makeText(FeedBackActivity.this, "哇哦，您木有输入消息，提交没什么用呢！", Style.CONFIRM).show();
                } else {
                    Crouton.makeText(FeedBackActivity.this, "谢谢么么哒的反馈消息", Style.CONFIRM).show();
                }
            }
        });
    }

    /**
     * 提交留言信息
     *
     * @param feedback
     */
    private void putMessage(String feedback) {
        List<Param> paramList = new ArrayList<>();
        Param message = new Param("feedback", feedback);
        paramList.add(message);
        HttpUtils.post(ConstantApi.MESSAGE, paramList, new ResultCallback() {
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
    public void processClick(View v) {

    }
}
