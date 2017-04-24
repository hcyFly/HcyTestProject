package com.cn.burus.hcytestproject.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.cn.burus.hcytestproject.R;
import com.cn.burus.hcytestproject.base.BaseActivity;

public class ThirdActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
    }


    public static void actionStart(Context context) {
        Intent intent = new Intent(context, ThirdActivity.class);
        context.startActivity(intent);
    }
}
