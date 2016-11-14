package com.looking.classicalparty.moudles.about.view;

import android.view.View;
import android.widget.ImageView;

import com.looking.classicalparty.R;
import com.looking.classicalparty.lib.base.activity.BaseActivity;
import com.looking.classicalparty.lib.ui.TitleBar;

public class AboutUsActivity extends BaseActivity {

    private TitleBar mTitleBar;
    private ImageView mImgCode;


    @Override
    public void initView() {
        setContentView(R.layout.activity_about_us);
        mTitleBar = (TitleBar) findViewById(R.id.title_bar);
        //二维码位置
        mImgCode = (ImageView) findViewById(R.id.img_code);

    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {
        mTitleBar.setTitle("关于我们");
        mTitleBar.setOnClickListener(new TitleBar.OnClickListener() {
            @Override
            public void OnLeftClick() {
                finish();
            }

            @Override
            public void OnRightClick() {

            }
        });
//        setImgCode();
    }

//    /**
//     * 设置二维码到指定位置
//     */
//    private void setImgCode() {
//
//        String content = "hello welcome!";
//        try {
//            if (!TextUtils.isEmpty(content)) {
//                LogoConfig logoConfig = new LogoConfig();
//                Bitmap logoBitmap = logoConfig.modifyLogo(BitmapFactory.decodeResource(getResources(), R.mipmap
//                        .img_logo), BitmapFactory.decodeResource(getResources(), R.mipmap.img_logo));
//                Bitmap codeBitmap = logoConfig.createCode(content, logoBitmap);
//                mImgCode.setImageBitmap(codeBitmap);
//            } else {
//                Crouton.makeText(this, "请输入要生成的字符串", Style.CONFIRM).show();
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }


    @Override
    public void processClick(View v) {

    }
}
