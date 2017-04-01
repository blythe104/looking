package com.looking.classicalparty.moudles.mine.view;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fourmob.datetimepicker.date.DatePickerDialog;
import com.google.gson.Gson;
import com.looking.classicalparty.R;
import com.looking.classicalparty.lib.base.activity.BaseActivity;
import com.looking.classicalparty.lib.constants.ConstantApi;
import com.looking.classicalparty.lib.constants.StringContants;
import com.looking.classicalparty.lib.http.HttpUtils;
import com.looking.classicalparty.lib.http.Param;
import com.looking.classicalparty.lib.http.ResultCallback;
import com.looking.classicalparty.lib.utils.ImageLoaderUtils;
import com.looking.classicalparty.lib.utils.LogUtils;
import com.looking.classicalparty.lib.utils.SharedPreUtils;
import com.looking.classicalparty.lib.widget.CircleImageView;
import com.looking.classicalparty.moudles.mine.PersonBean;
import com.looking.classicalparty.moudles.mine.dialog.ChooiseGenderDialog;
import com.looking.classicalparty.moudles.mine.dialog.ChooisePhotoDialog;
import com.looking.classicalparty.moudles.mine.dialog.NicknameDialog;
import com.looking.classicalparty.moudles.mine.observer.ConcreteSubject;
import com.looking.classicalparty.moudles.mine.observer.Observer;
import com.squareup.okhttp.Request;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;

public class PersonalActivity extends BaseActivity {


    private int CAMERA_REQUEST_CODE = 1001;
    private int PHOTO_REQUEST_CODE = 1002;
    private FrameLayout mFrBack;
    private TextView mTvTitle;
    private CircleImageView circleImageView;
    private LinearLayout mLlChangePhoto;
    private ChooisePhotoDialog chooisePhotoDialog;
    private DatePickerDialog datePickerDialog;
    private ChooiseGenderDialog genderDialog;
    private ChangeDescObserver changeDescObserver;
    private NicknameDialog nicknameDialog;
    private TextView tvNickname;
    private TextView tvSexy;
    private TextView tvSign;
    private TextView tvBirthday;

    @Override
    public void initView() {
        setContentView(R.layout.activity_person);
        initTitle();
        final Calendar calendar = Calendar.getInstance();
        tvNickname = (TextView) findViewById(R.id.tv_nickname);
        tvSexy = (TextView) findViewById(R.id.tv_sexy);
        tvSign = (TextView) findViewById(R.id.tv_sign);
        tvBirthday = (TextView) findViewById(R.id.tv_birthday);
        genderDialog = new ChooiseGenderDialog(this);
        nicknameDialog = new NicknameDialog(this);
        changeDescObserver = new ChangeDescObserver();
        ConcreteSubject.getInstance().register(changeDescObserver);
        datePickerDialog = DatePickerDialog.newInstance((ddg, year, month, day) -> {
                }, //
                calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        chooisePhotoDialog.setPhotoListener(new ChooisePhotoDialog.PhotoListener() {
            @Override
            public void takePhoto() {
                requstPermission(CAMERA_REQUEST_CODE);
            }

            @Override
            public void chooisePhoto() {
                requstPermission(PHOTO_REQUEST_CODE);
            }
        });
    }

    private void nicknameDialog() {
        nicknameDialog.show();
    }

    @Override
    protected void onDestroy() {
        ConcreteSubject.getInstance().unRegister(changeDescObserver);
        super.onDestroy();

    }

    private void loadAvatarImg(String res) {
        ImageLoaderUtils.display(getApplication(), res, R.mipmap.mine_two, circleImageView);
    }

    /**
     * 获取用户详情
     */
    private void getPersonDetail() {
        List<Param> paramList = new ArrayList<>();
        Param key = new Param("key", SharedPreUtils.getString(StringContants.KEY));
        Param token = new Param("Token", SharedPreUtils.getString(StringContants.TOKEN));
        paramList.add(key);
        paramList.add(token);
        HttpUtils.post(ConstantApi.DETAIL, paramList, new ResultCallback() {
            @Override
            public void onSuccess(Object response) {
                PersonBean personBean = new Gson().fromJson(response.toString(), PersonBean.class);
                if (personBean.getResult() == 200) {
                    // TODO: 2017/3/27 获取用户信息填充
                    tvNickname.setText(personBean.getContent().get(0).getMnickname());
                    tvSexy.setText(personBean.getContent().get(0).getMsex() == "1" ? "男" : "女");
                    tvSign.setText(personBean.getContent().get(0).getMsignature());
                    loadAvatarImg(personBean.getContent().get(0).getMavatar());
                } else {
                    Crouton.makeText(PersonalActivity.this, personBean.getResultMsg(), Style.CONFIRM).show();
                }
            }

            @Override
            public void onFailure(Request request, Exception e) {

            }

            @Override
            public void onNoNetWork(String resultMsg) {

            }
        });


    }

    /**
     * 选择性别
     */
    private void chooiseGender() {
        genderDialog.show();
    }

    /**
     * 选择出生日期
     */
    private void chooiseBirthday() {
        datePickerDialog.setYearRange(1985, 2028);

    }

    private void requstPermission(int code) {
        switch (code) {
            case 1001:
                //没有相机权限
                if (PackageManager.PERMISSION_GRANTED != ContextCompat.checkSelfPermission(this, android.Manifest
                        .permission.CAMERA)) {
                    if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(this);
                        builder.setTitle(R.string.app_name).setMessage("申请相机权限").setPositiveButton("确定", (dialog, v)
                                -> {
                            //申请相机权限
                            ActivityCompat.requestPermissions(PersonalActivity.this, new String[]{Manifest.permission
                                    .CAMERA}, CAMERA_REQUEST_CODE);
                            // 跳转到系统的界面
                            Intent intent = new Intent();
                            intent.setAction("android.intent.action.MAIN");
                            intent.setClassName("com.android.settings", "com.android.settings.ManageApplications");
                            startActivity(intent);
                        }).setNegativeButton("暂不申请", null).show();
                    } else {
                        //申请相机权限
                        Crouton.makeText(PersonalActivity.this, "没有相机权限", Style.CONFIRM).show();
                        ActivityCompat.requestPermissions(PersonalActivity.this, new String[]{Manifest.permission
                                .CAMERA}, CAMERA_REQUEST_CODE);
                    }
                } else {
                    //调用相机拍照
                    //                    Toast.makeText(this, "相机权限已经开启", Toast.LENGTH_SHORT).show();
                    Intent camareIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    camareIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(Environment
                            .getExternalStorageDirectory(), "head.jpg")));
                    startActivityForResult(camareIntent, CAMERA_REQUEST_CODE);
                }
                break;
            case 1002:
                Crouton.makeText(PersonalActivity.this, "获取系统相册", Style.CONFIRM).show();
                break;
        }

    }

    /**
     * 出来权限申请成功或者失败的操作
     *
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[]
            grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == CAMERA_REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Intent camareIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                camareIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(Environment
                        .getExternalStorageDirectory(), "head.jpg")));
                startActivityForResult(camareIntent, CAMERA_REQUEST_CODE);
            } else {
                if (!ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)) {
                    Crouton.makeText(this, "相机权限已经被禁止", Style.CONFIRM).show();
                }
            }
        }
    }

    private void initTitle() {
        mFrBack = (FrameLayout) findViewById(R.id.fr_back);
        mTvTitle = (TextView) findViewById(R.id.tv_title);
        chooisePhotoDialog = new ChooisePhotoDialog(this);
        circleImageView = (CircleImageView) findViewById(R.id.circle_image);
        mLlChangePhoto = (LinearLayout) findViewById(R.id.ll_change_photo);
        mTvTitle.setText("个人资料");

    }

    @Override
    public void initListener() {
        mFrBack.setOnClickListener(this);
        mLlChangePhoto.setOnClickListener(this);
        circleImageView.setOnClickListener(this);
    }

    @Override
    public void initData() {
        getPersonDetail();
    }


    @Override
    public void processClick(View v) {
        switch (v.getId()) {
            case R.id.fr_back:
                finish();
                break;
            case R.id.ll_change_photo:
                chooisePhotoDialog.show();
                break;
        }

    }

    private class ChangeDescObserver implements Observer {
        private String getDesc;

        @Override
        public void update(String desc) {
            getDesc = desc;
            LogUtils.d("desc------", getDesc);
            SharedPreUtils.saveString("sign", getDesc);
        }
    }
}
