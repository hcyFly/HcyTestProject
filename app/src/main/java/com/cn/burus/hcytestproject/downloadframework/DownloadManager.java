package com.cn.burus.hcytestproject.downloadframework;

import android.content.Context;
import android.content.Intent;

import com.cn.burus.hcytestproject.downloadframework.core.DownloadService;
import com.cn.burus.hcytestproject.downloadframework.entities.DownloadEntry;
import com.cn.burus.hcytestproject.downloadframework.utilities.Constants;

/**
 * 下载管理类(入口)
 * <p>
 * Created by chengyou.huang on 2017/5/16.
 */

public class DownloadManager {

    public void add(Context context, DownloadEntry entry) {
        Intent intent = new Intent(context, DownloadService.class);
        intent.putExtra(Constants.KEY_DOWNLOAD_ENTRY, entry);
        context.startService(intent);
    }


    public void pause() {
    }

    public void resume() {
    }

    public void cancel() {
    }

}
