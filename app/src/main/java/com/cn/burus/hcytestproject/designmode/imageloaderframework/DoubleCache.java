package com.cn.burus.hcytestproject.designmode.imageloaderframework;

import android.graphics.Bitmap;

import com.socks.library.KLog;

/**
 * 双缓存（内存和SD卡）
 * Created by chengyou.huang on 2017/4/24.
 */

public class DoubleCache implements ImageCache {

    ImageCache mMemoryCache = new MemoryCache();
    ImageCache mDiskCache = new DiskCache();

    @Override
    public Bitmap get(String url) {

        Bitmap bitmap = mMemoryCache.get(url);
        if (bitmap == null) {
            bitmap = mDiskCache.get(url);
        }
        return bitmap;
    }

    @Override
    public void put(String url, Bitmap bmp) {
        mMemoryCache.put(url, bmp);
        KLog.i("DiskCache", " mDiskCache.put--");
        mDiskCache.put(url, bmp);
    }
}
