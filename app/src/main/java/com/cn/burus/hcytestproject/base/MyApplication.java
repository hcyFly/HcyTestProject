package com.cn.burus.hcytestproject.base;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;

import com.cn.burus.hcytestproject.widget.SupportMultipleScreensUtil;
import com.socks.library.KLog;

/**
 * Created by chengyou.huang on 2017/4/13.
 */

public class MyApplication extends Application {

    private static final String TAG = "MyApplication";
    public static Context globalContext;
    public static int countAty = 0;

    private MyApplication() {
    }

    public static MyApplication getInstance() {
        return MyapplicationHolder.myApplication;
    }

    /**
     * 静态内部类(饿汉单例模式 推荐写法)
     */
    private static class MyapplicationHolder {
        private static final MyApplication myApplication = new MyApplication();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        globalContext = getApplicationContext();
        SupportMultipleScreensUtil.init(globalContext);
        KLog.init(true);
        registerActivityLifecycleCallbacks(new AppStatusTracker());
        KLog.i(TAG, "onCreate complete");
    }


    public class AppStatusTracker implements Application.ActivityLifecycleCallbacks {

        @Override
        public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
            KLog.i(activity.toString(), "countAty---onActivityCreated:" + countAty);
        }

        @Override
        public void onActivityStarted(Activity activity) {
            countAty++;
            KLog.i(activity.toString(), "countAty---onActivityStarted:" + countAty);
        }

        @Override
        public void onActivityResumed(Activity activity) {

            KLog.i(activity.toString(), "countAty---onActivityResumed:" + countAty);
        }

        @Override
        public void onActivityPaused(Activity activity) {
            KLog.i(activity.toString(), "countAty---onActivityPaused:" + countAty);
        }

        @Override
        public void onActivityStopped(Activity activity) {
            countAty--;
            KLog.i(activity.toString(), "countAty---onActivityStopped:" + countAty);
        }

        @Override
        public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
            KLog.i(activity.toString(), "countAty---onActivitySaveInstanceState:" + countAty);
        }

        @Override
        public void onActivityDestroyed(Activity activity) {
            KLog.i(activity.toString(), "countAty---onActivityDestroyed:" + countAty);
            if (countAty == 0) {
                countAty--;
                //标识app被强杀或是主动退出app
                KLog.i(activity.toString(), "countAty---onActivityDestroyed---kill:" + countAty);
            }
        }
    }

}
