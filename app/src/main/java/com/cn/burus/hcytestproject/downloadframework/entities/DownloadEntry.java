package com.cn.burus.hcytestproject.downloadframework.entities;

import com.cn.burus.hcytestproject.downloadframework.DownloadConfig;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.File;
import java.io.Serializable;
import java.util.HashMap;

/**
 * 描述下载文件状态的实体类
 */
@DatabaseTable(tableName = "downloadentry")
public class DownloadEntry implements Serializable, Cloneable {
    @DatabaseField(id = true)
    public String id;//主键
    @DatabaseField
    public String name;//文件名称
    @DatabaseField
    public String url;//下载 url
    @DatabaseField
    public int currentLength;//已下载的进度
    @DatabaseField
    public int totalLength;//文件总长度
    @DatabaseField
    public DownloadStatus status = DownloadStatus.idle;//下载的状态
    @DatabaseField
    public boolean isSupportRange = false;//是否支持断点续传
    @DatabaseField(dataType = DataType.SERIALIZABLE)
    public HashMap<Integer, Integer> ranges;//如支持断点续传，记录其已下载的位置
    @DatabaseField
    public int percent;//下载的百分比

    public DownloadEntry() {

    }

    public DownloadEntry(String url) {
        this.url = url;
        this.id = url;
        this.name = url.substring(url.lastIndexOf("/") + 1);
    }


    /**
     * 重置 将已下载的临时文件删除
     */
    public void reset() {
        currentLength = 0;
        ranges = null;
        percent = 0;
        File file = DownloadConfig.getConfig().getDownloadFile(url);
        if (file.exists()) {
            file.delete();
        }
    }

    /**
     * enum 文件下载的状态
     * 闲置/等待/链接/下载中/暂停/恢复/取消/完成/错误
     */
    public enum DownloadStatus {
        idle, waiting, connecting, downloading, paused, resumed, cancelled, completed, error
    }

    @Override
    public String toString() {
        return name + " is " + status.name() + " with " + currentLength + "/" + totalLength;
    }

    @Override
    public boolean equals(Object o) {
        return o.hashCode() == this.hashCode();
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
