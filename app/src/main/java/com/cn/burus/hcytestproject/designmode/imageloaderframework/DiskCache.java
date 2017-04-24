package com.cn.burus.hcytestproject.designmode.imageloaderframework;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.bumptech.glide.disklrucache.DiskLruCache;
import com.cn.burus.hcytestproject.base.MyApplication;
import com.cn.burus.hcytestproject.utils.SystemInfo;
import com.socks.library.KLog;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * SD开缓存类
 * Created by chengyou.huang on 2017/4/24.
 */

public class DiskCache implements ImageCache {

    private static final String TAG = "DiskCache";

    //SD卡缓存路径  /
    static String cacheDir = SystemInfo.getDiskCacheDir(MyApplication.globalContext);
    DiskLruCache.Editor mEditor = null;

    @Override
    public Bitmap get(String url) {
        String urlName = url.substring(url.lastIndexOf("/") + 1);
        KLog.i(TAG, cacheDir + urlName);
        return BitmapFactory.decodeFile(cacheDir + urlName);
    }

    @Override
    public void put(String url, Bitmap bmp) {

        FileOutputStream fileOutputStream = null;
        try {
            String urlName = url.substring(url.lastIndexOf("/") + 1);
            KLog.i(TAG, cacheDir + urlName);
            fileOutputStream = new FileOutputStream(cacheDir + urlName);
            bmp.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            CloseUtils.closeQuietly(fileOutputStream);
        }
    }
}

