package com.looking.classicalparty.moudles.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.looking.classicalparty.R;
import com.looking.classicalparty.lib.utils.OkHttpUtils;
import com.squareup.okhttp.Request;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        OkHttpUtils.Param h = new OkHttpUtils.Param("jj", "jk");
        List<OkHttpUtils.Param> paramList = new ArrayList<>();
        paramList.add(h);
        OkHttpUtils.post("hhh", paramList, new OkHttpUtils.ResultCallback() {
            @Override
            public void onSuccess(Object response) {

            }

            @Override
            public void onFailure(Request request, Exception e) {

            }

            @Override
            public void onServiceError(Request request, Exception e) {

            }

            @Override
            public void onNoNetWork(Request request, Exception e) {

            }
        });
    }

}
