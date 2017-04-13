package com.cn.burus.hcytestproject;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.cn.burus.hcytestproject.activities.LoginActivity;
import com.cn.burus.hcytestproject.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WelcomeActivity extends BaseActivity {


    @BindView(R.id.img_welcome)
    ImageView mImgWelcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        ButterKnife.bind(this);

    }


    @OnClick(R.id.img_welcome)
    public void onViewClicked() {
        startActivity(new Intent(WelcomeActivity.this, LoginActivity.class));
    }
}
