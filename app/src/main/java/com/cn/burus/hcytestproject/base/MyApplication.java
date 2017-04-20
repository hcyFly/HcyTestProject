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

    private static volatile MyApplication myApplication;
    public static Context globalContext;
    public static int countAty = 0;
    public static int mAppStatus = -1;

    public MyApplication() {
    }

    public static MyApplication getInstance() {

        if (myApplication == null) {
            synchronized (MyApplication.class) {
                if (myApplication == null) {
                    myApplication = new MyApplication();
                }
            }
        }
        return myApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        globalContext = this;
        SupportMultipleScreensUtil.init(globalContext);
        KLog.init(true);
        registerActivityLifecycleCallbacks(new AppStatusTracker());
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
