package com.looking.classicalparty.moudles.mine.view;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
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

import com.looking.classicalparty.R;
import com.looking.classicalparty.lib.base.activity.BaseActivity;
import com.looking.classicalparty.lib.widget.CircleImageView;
import com.looking.classicalparty.lib.widget.CustomerMenuView;
import com.looking.classicalparty.moudles.mine.dialog.ChooisePhotoDialog;

import java.io.File;

import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;

public class PersonalActivity extends BaseActivity {


    private int CAMERA_REQUEST_CODE = 1001;
    private int PHOTO_REQUEST_CODE = 1002;
    private FrameLayout mFrBack;
    private TextView mTvTitle;
    private CustomerMenuView mCustomMenu;
    private CircleImageView circleImageView;
    private LinearLayout mLlChangePhoto;
    private ChooisePhotoDialog chooisePhotoDialog;

    @Override
    public void initView() {
        setContentView(R.layout.activity_person);
        initTitle();
        mCustomMenu.addDivider()//
                .addItem(R.mipmap.ic_nickname1, "昵称", "twory", "nickname", false)//
                .addItem(R.mipmap.ic_sex, "性别", "男", "sex", false)//
                .addItem(R.mipmap.ic_birthday, "生日", "202020", "birthday", false)//
                .addItem(R.mipmap.ic_sign, "个性签名", "909090909", "sign", false)//
                .build();

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

    private void requstPermission(int code) {
        switch (code) {
            case 1001:
                //没有相机权限
                if (PackageManager.PERMISSION_GRANTED != ContextCompat.checkSelfPermission(this, android.Manifest
                        .permission.CAMERA)) {
                    if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(this);
                        builder.setTitle(R.string.app_name).setMessage("申请相机权限").setPositiveButton("确定", new
                                DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //申请相机权限
                                ActivityCompat.requestPermissions(PersonalActivity.this, new String[]{Manifest
                                        .permission.CAMERA}, CAMERA_REQUEST_CODE);
                                // 跳转到系统的界面
                                Intent intent = new Intent();
                                intent.setAction("android.intent.action.MAIN");
                                intent.setClassName("com.android.settings", "com.android.settings.ManageApplications");
                                startActivity(intent);
                            }
                        }).setNegativeButton("暂不申请", null).show();
                    } else {
                        //申请相机权限
                        Crouton.makeText(PersonalActivity.this, "没有相机全向", Style.CONFIRM).show();
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
                //                Crouton.makeText(PersonalActivity.this, "相机权限已经申请", Style.CONFIRM).show();
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
        mCustomMenu = (CustomerMenuView) findViewById(R.id.custom_menu);
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
}
