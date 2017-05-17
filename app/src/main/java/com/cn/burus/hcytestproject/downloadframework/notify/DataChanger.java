package com.cn.burus.hcytestproject.downloadframework.notify;

import android.content.Context;

import com.cn.burus.hcytestproject.downloadframework.db.DBController;
import com.cn.burus.hcytestproject.downloadframework.entities.DownloadEntry;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Observable;

/**
 * 被观察者
 */

public class DataChanger extends Observable {

    private static DataChanger mInstance;
    private final Context context;
    private LinkedHashMap<String, DownloadEntry> mOperatedEntries;

    private DataChanger(Context context) {
        this.context = context;
        mOperatedEntries = new LinkedHashMap<>();
    }

    public synchronized static DataChanger getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new DataChanger(context);
        }
        return mInstance;
    }


    /**
     * 更新下载文件状态类信息
     *
     * @param entry
     */
    public void postStatus(DownloadEntry entry) {
        mOperatedEntries.put(entry.id, entry);
        DBController.getInstance(context).newOrUpdate(entry);
        setChanged();
        notifyObservers(entry);
    }

    /**
     * 查询所有下载文件的状态类列表 信息
     *
     * @return list
     */
    public ArrayList<DownloadEntry> queryAllRecoverableEntries() {
        ArrayList<DownloadEntry> mRecoverableEntries = null;
        for (Map.Entry<String, DownloadEntry> entry : mOperatedEntries.entrySet()) {
            if (entry.getValue().status == DownloadEntry.DownloadStatus.paused) {
                if (mRecoverableEntries == null) {
                    mRecoverableEntries = new ArrayList<>();
                }
                mRecoverableEntries.add(entry.getValue());
            }
        }
        return mRecoverableEntries;
    }

    /**
     * 根据url 查询某个下载文件的状态类信息
     *
     * @param id
     * @return
     */
    public DownloadEntry queryDownloadEntryById(String id) {
        return mOperatedEntries.get(id);
    }


    /**
     * 添加到队列中
     *
     * @param key
     * @param value
     */
    public void addToOperatedEntryMap(String key, DownloadEntry value) {
        mOperatedEntries.put(key, value);
    }

    /**
     * 查询已下载文件中是否包含有要查询的id
     *
     * @param id 下载的url
     * @return boolean
     */
    public boolean containsDownloadEntry(String id) {
        return mOperatedEntries.containsKey(id);
    }

    /**
     * 删除下载的指定文件
     *
     * @param id
     */
    public void deleteDownloadEntry(String id) {
        mOperatedEntries.remove(id);
        DBController.getInstance(context).deleteById(id);
    }
}
