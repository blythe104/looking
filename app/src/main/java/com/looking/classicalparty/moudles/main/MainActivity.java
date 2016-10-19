package com.looking.classicalparty.moudles.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.looking.classicalparty.R;
import com.looking.classicalparty.lib.http.HttpUtils;
import com.looking.classicalparty.lib.http.ResultCallback;
import com.squareup.okhttp.Request;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        HttpUtils.post("", null, new ResultCallback() {
            @Override
            public void onSuccess(Object response) {

            }

            @Override
            public void onFailure(Request request, Exception e) {

            }
        });
    }

}
