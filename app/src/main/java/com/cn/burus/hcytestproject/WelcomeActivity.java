package com.cn.burus.hcytestproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.cn.burus.hcytestproject.activities.LoginActivity;
import com.cn.burus.hcytestproject.base.BaseActivity;
import com.cn.burus.hcytestproject.designmode.imageloaderframework.DoubleCache;
import com.cn.burus.hcytestproject.designmode.imageloaderframework.ImageLoader;
import com.socks.library.KLog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WelcomeActivity extends BaseActivity {

    private static final String TAG = "WelcomeActivity";
    @BindView(R.id.img_welcome)
    ImageView mImgWelcome;
    @BindView(R.id.img_glide_test)
    ImageView mImgGlideTest;
    @BindView(R.id.img_glide_test2)
    ImageView mImgGlideTest2;
    @BindView(R.id.img_my_loadiamge_test)
    ImageView mImgMyLoadiamgeTest;
    //图片加载框架  my
    ImageLoader mImageLoader = new ImageLoader();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        ButterKnife.bind(this);
        mImageLoader.setImageCache(new DoubleCache());
    }


    @OnClick({R.id.img_glide_test, R.id.img_glide_test2, R.id.img_welcome, R.id.but_my_load_image})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_glide_test:
                KLog.i(TAG, "--onclick  img glide test");
                String url = "http://cn.bing.com/az/hprichbg/rb/Dongdaemun_ZH-CN10736487148_1920x1080.jpg";
                Glide.with(this).load(url).into(mImgGlideTest2);
                break;
            case R.id.img_glide_test2:
                KLog.i(TAG, "--onclick  img glide test2");
//                String url2 = "http://p1.pstatp.com/large/166200019850062839d3";//git imge
                String url2 = "http://cn.bing.com/az/hprichbg/rb/Dongdaemun_ZH-CN10736487148_1920x1080.jpg";
                Glide.with(this).load(url2).diskCacheStrategy(DiskCacheStrategy.NONE).into(mImgGlideTest);
                break;
            case R.id.but_my_load_image:
                KLog.i(TAG, "--onclick  but_my_load_image");
                String urlmyloadimage = "http://cn.bing.com/az/hprichbg/rb/Dongdaemun_ZH-CN10736487148_1920x1080.jpg";
                mImageLoader.displayImage(urlmyloadimage, mImgMyLoadiamgeTest);
                break;
            case R.id.img_welcome:
                //test code java
                startActivity(new Intent(WelcomeActivity.this, LoginActivity.class));
                break;
        }
    }


}
