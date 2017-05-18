package com.cn.burus.hcytestproject.downloadframework;

import android.content.Context;
import android.content.Intent;

import com.cn.burus.hcytestproject.downloadframework.core.DownloadService;
import com.cn.burus.hcytestproject.downloadframework.entities.DownloadEntry;
import com.cn.burus.hcytestproject.downloadframework.notify.DataChanger;
import com.cn.burus.hcytestproject.downloadframework.notify.DataWatcher;
import com.cn.burus.hcytestproject.downloadframework.utilities.Constants;

import java.io.File;

/**
 * 下载管理类(入口)
 * <p>
 * Created by chengyou.huang on 2017/5/16.
 */

public class DownloadManager {

    private static DownloadManager mInstance;
    private final Context context;

    private long mLastOperatedTime = 0;

    private DownloadManager(Context context) {
        this.context = context;
        context.startService(new Intent(context, DownloadService.class));
    }

    public synchronized static DownloadManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new DownloadManager(context);
        }
        return mInstance;
    }


    public void add(DownloadEntry entry) {
        if (!checkIfExecutable())
            return;
        Intent intent = new Intent(context, DownloadService.class);
        intent.putExtra(Constants.KEY_DOWNLOAD_ENTRY, entry);
        intent.putExtra(Constants.KEY_DOWNLOAD_ACTION, Constants.KEY_DOWNLOAD_ACTION_ADD);
        context.startService(intent);
    }

    private boolean checkIfExecutable() {
        long tmp = System.currentTimeMillis();
        if (tmp - mLastOperatedTime > DownloadConfig.getConfig().getMinOperateInterval()) {
            mLastOperatedTime = tmp;
            return true;
        }
        return false;
    }

    public void pause(DownloadEntry entry) {
        if (!checkIfExecutable())
            return;
        Intent intent = new Intent(context, DownloadService.class);
        intent.putExtra(Constants.KEY_DOWNLOAD_ENTRY, entry);
        intent.putExtra(Constants.KEY_DOWNLOAD_ACTION, Constants.KEY_DOWNLOAD_ACTION_PAUSE);
        context.startService(intent);
    }

    public void resume(DownloadEntry entry) {
        if (!checkIfExecutable())
            return;
        Intent intent = new Intent(context, DownloadService.class);
        intent.putExtra(Constants.KEY_DOWNLOAD_ENTRY, entry);
        intent.putExtra(Constants.KEY_DOWNLOAD_ACTION, Constants.KEY_DOWNLOAD_ACTION_RESUME);
        context.startService(intent);
    }

    public void cancel(DownloadEntry entry) {
        if (!checkIfExecutable())
            return;
        Intent intent = new Intent(context, DownloadService.class);
        intent.putExtra(Constants.KEY_DOWNLOAD_ENTRY, entry);
        intent.putExtra(Constants.KEY_DOWNLOAD_ACTION, Constants.KEY_DOWNLOAD_ACTION_CANCEL);
        context.startService(intent);
    }

    public void pauseAll() {
        if (!checkIfExecutable())
            return;
        Intent intent = new Intent(context, DownloadService.class);
//        intent.putExtra(Constants.KEY_DOWNLOAD_ENTRY, entry);
        intent.putExtra(Constants.KEY_DOWNLOAD_ACTION, Constants.KEY_DOWNLOAD_ACTION_PAUSE_ALL);
        context.startService(intent);
    }

    public void recoverAll() {
        if (!checkIfExecutable())
            return;
        Intent intent = new Intent(context, DownloadService.class);
//        intent.putExtra(Constants.KEY_DOWNLOAD_ENTRY, entry);
        intent.putExtra(Constants.KEY_DOWNLOAD_ACTION, Constants.KEY_DOWNLOAD_ACTION_RECOVER_ALL);
        context.startService(intent);
    }

    public void addObserver(DataWatcher watcher) {
        DataChanger.getInstance(context).addObserver(watcher);

    }

    public void removeObserver(DataWatcher watcher) {
        DataChanger.getInstance(context).deleteObserver(watcher);
    }

    public DownloadEntry queryDownloadEntry(String id) {
        return DataChanger.getInstance(context).queryDownloadEntryById(id);
    }

    public boolean containsDownloadEntry(String id) {
        return DataChanger.getInstance(context).containsDownloadEntry(id);
    }

    public void deleteDownloadEntry(boolean forceDelete,String id) {
        DataChanger.getInstance(context).deleteDownloadEntry(id);
        if (forceDelete){
            File file = DownloadConfig.getConfig().getDownloadFile(id);
            if (file.exists())
                file.delete();
        }
    }

}
