package com.cn.burus.hcytestproject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.cn.burus.hcytestproject.activities.HomeActivity;
import com.cn.burus.hcytestproject.activities.LoginActivity;
import com.cn.burus.hcytestproject.base.BaseActivity;
import com.cn.burus.hcytestproject.base.MyApplication;
import com.cn.burus.hcytestproject.designmode.imageloaderframework.DoubleCache;
import com.cn.burus.hcytestproject.designmode.imageloaderframework.ImageLoader;
import com.cn.burus.hcytestproject.httpframework.Request;
import com.cn.burus.hcytestproject.httpframework.RequestManager;
import com.cn.burus.hcytestproject.httpframework.callback.JsonCallback;
import com.cn.burus.hcytestproject.httpframework.callback.JsonReaderCallback;
import com.cn.burus.hcytestproject.httpframework.error.AppException;
import com.cn.burus.hcytestproject.modle.Weather;
import com.cn.burus.hcytestproject.test_ipc.IpcActivity;
import com.cn.burus.hcytestproject.utils.SystemInfo;
import com.socks.library.KLog;

import java.io.File;

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
    @BindView(R.id.but_hcyokhttp)
    Button mButHcyokhttp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        ButterKnife.bind(this);
        mImageLoader.setImageCache(new DoubleCache());
    }


    @OnClick({R.id.img_glide_test, R.id.img_glide_test2, R.id.img_welcome, R.id.but_my_load_image, R.id.but_hcyokhttp})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_glide_test:
                KLog.i(TAG, "--onclick  img glide test");
                String url = "http://img.ph.126.net/YqgqRE1WmIvbWtD2Z9civw==/1075797361005026541.jpg";
                Glide.with(this).load(url).into(mImgGlideTest2);
                break;
            case R.id.img_glide_test2:
                KLog.i(TAG, "--onclick  img glide test2");
                //String url2 = "http://p1.pstatp.com/large/166200019850062839d3";
                //http://img.ph.126.net/ocT0cPlMSiTs2BgbZ8bHFw==/631348372762626203.jpg
                String url2 = "http://img.ph.126.net/ocT0cPlMSiTs2BgbZ8bHFw==/631348372762626203.jpg";
                Glide.with(this).load(url2).diskCacheStrategy(DiskCacheStrategy.NONE).into(mImgGlideTest);
                break;
            case R.id.but_my_load_image:
                KLog.i(TAG, "--onclick  but_my_load_image");
                String urlmyloadimage = "http://img.ph.126.net/YqgqRE1WmIvbWtD2Z9civw==/1075797361005026541.jpg";
                mImageLoader.displayImage(urlmyloadimage, mImgMyLoadiamgeTest);
                break;
            case R.id.img_welcome:
                //test code java
//                LoginActivity.actionStart(WelcomeActivity.this);

                IpcActivity.actionStart(WelcomeActivity.this);

                break;

            case R.id.but_hcyokhttp:
                // TODO: 2017/5/12   test hcy okhttp
//                hcyokhttpTest1();
                hcyokhttpTestPath();


                break;
        }
    }

    private void hcyokhttpTest1() {
        String urlhcy = "http://guolin.tech/api/weather?cityid=shenzhen&key=3799d79d735340ac9accef410a7f5316";
        final Request request = new Request(urlhcy, Request.RequestMethod.GET);
        request.setCallback(new JsonCallback<Weather>() {

            @Override
            public void onSuccess(Weather result) {
                KLog.i(TAG, "---onSuccess:" + result.getHeWeather().get(0).getBasic().getCity());
            }

            @Override
            public void onFailure(AppException e) {
                KLog.i(TAG, "---onFailure:" + e.toString());
            }
        });
        request.maxRetryCount = 3;
        RequestManager.getInstance().performRequest(request);
    }

    private void hcyokhttpTestPath() {
        String urlhcy = "http://guolin.tech/api/weather?cityid=shenzhen&key=3799d79d735340ac9accef410a7f5316";
        final Request request = new Request(urlhcy, Request.RequestMethod.GET);
        String path = SystemInfo.getDiskCacheDir(MyApplication.globalContext) + File.separator + "json.txt";
        KLog.i(TAG, "---:" + path);
        request.setCallback(new JsonReaderCallback<Weather>() {


            //子线程处理服务器返回数据 再回调到onSeuccess
            @Override
            public Weather postRequest(Weather weather) {
                return super.postRequest(weather);
            }

            //子线程处理 请求前是否有缓存数据 返回null 则继续网络请求否则 使用缓存数据 回调到onSuccess
            @Override
            public Weather preRequest() {
                return super.preRequest();
            }

            @Override
            public void onSuccess(Weather result) {
                if (result == null) {
                    KLog.i(TAG, "---onSuccess: result==null");
                } else {
                    KLog.i(TAG, "---onSuccess:" + result.toString());
                }
            }

            @Override
            public void onFailure(AppException e) {
                KLog.i(TAG, "---onFailure:" + e.toString());
            }
        }.setCachePath(path));
        request.maxRetryCount = 0;
        RequestManager.getInstance().performRequest(request);
    }

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, HomeActivity.class);
        context.startActivity(intent);
    }
}
