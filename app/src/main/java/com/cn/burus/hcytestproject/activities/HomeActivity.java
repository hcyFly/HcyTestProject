package com.cn.burus.hcytestproject.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.cn.burus.hcytestproject.R;
import com.cn.burus.hcytestproject.WelcomeActivity;
import com.cn.burus.hcytestproject.base.BaseActivity;
import com.socks.library.KLog;

public class HomeActivity extends BaseActivity {


    private static final String TAG = "HomeActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
