package com.looking.classicalparty.moudles.mine.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.looking.classicalparty.R;

/**
 * Created by xin on 2016/10/26.
 */

public class ChooisePhotoDialog extends Dialog implements View.OnClickListener {

    private ImageButton mTakePhoto;
    private ImageButton mChooisePhoto;
    private Button mBtnCanel;
    private PhotoListener listener;

    public ChooisePhotoDialog(Context context) {
        this(context, R.style.base_dialog_theme);
    }

    public ChooisePhotoDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chooise_photo_layout);
        mTakePhoto = (ImageButton) findViewById(R.id.take_photo);
        mChooisePhoto = (ImageButton) findViewById(R.id.chooise_photos);
        mBtnCanel = (Button) findViewById(R.id.btn_canel);
        mTakePhoto.setOnClickListener(this);
        mChooisePhoto.setOnClickListener(this);
        mBtnCanel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_canel:
                dismiss();
                break;
            case R.id.take_photo:
                if (listener != null) {
                    listener.takePhoto();
                }
                dismiss();
                break;
            case R.id.chooise_photos:
                if (listener != null) {
                    listener.chooisePhoto();
                }
                dismiss();
                break;
        }

    }

    public void setPhotoListener(PhotoListener photoListener) {
        this.listener = photoListener;
    }

    public interface PhotoListener {
        void takePhoto();

        void chooisePhoto();
    }
}
