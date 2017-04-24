package com.cn.burus.hcytestproject.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.cn.burus.hcytestproject.R;
import com.cn.burus.hcytestproject.WelcomeActivity;
import com.cn.burus.hcytestproject.base.BaseActivity;

public class SecondActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
    }


    public static void actionStart(Context context) {
        Intent intent = new Intent(context, SecondActivity.class);
        context.startActivity(intent);
    }
}
