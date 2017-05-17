package com.cn.burus.hcytestproject.downloadframework.notify;

import com.cn.burus.hcytestproject.downloadframework.entities.DownloadEntry;

import java.util.Observable;
import java.util.Observer;

/**
 * 数据变化观察者
 */

public abstract class DataWatcher implements Observer {
    @Override
    public void update(Observable observable, Object data) {
        if (data instanceof DownloadEntry) {
            notifyUpdate((DownloadEntry) data);
        }
    }

    public abstract void notifyUpdate(DownloadEntry data);
}
