package com.cn.burus.hcytestproject.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.cn.burus.hcytestproject.R;
import com.cn.burus.hcytestproject.WelcomeActivity;
import com.cn.burus.hcytestproject.base.BaseActivity;
import com.socks.library.KLog;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HomeActivity extends BaseActivity {


    private static final String TAG = "HomeActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        okhttpTest();
    }

    private void okhttpTest() {

        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url("url").build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

            }
        });
    }


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        KLog.i(TAG, "---onNewIntent");
        String action = intent.getStringExtra("action");
        if ("force_kill".equals(action)) {
            protectApp();
        }
    }

    @Override
    protected void setUpData() {
        super.setUpData();
        //放初始化的操作
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void protectApp() {
        WelcomeActivity.actionStart(HomeActivity.this);
        finish();
    }

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, HomeActivity.class);
        context.startActivity(intent);
    }
}
