package com.cn.burus.hcytestproject.downloadframework.core;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * 下载服务（管理下载任务）
 */

public class DownloadService extends Service{


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
