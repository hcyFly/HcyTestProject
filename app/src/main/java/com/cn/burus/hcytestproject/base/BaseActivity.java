

package com.cn.burus.hcytestproject.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.cn.burus.hcytestproject.activities.HomeActivity;
import com.cn.burus.hcytestproject.widget.SupportMultipleScreensUtil;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //适配
        View rootview = findViewById(android.R.id.content);
        SupportMultipleScreensUtil.scale(rootview);

    }

    protected void setUpData() {
        //初始化
    }

    protected void protectApp() {
        Intent i = new Intent(this, HomeActivity.class);
        i.putExtra("action", "force_kill");
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onResume() {

        super.onResume();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
