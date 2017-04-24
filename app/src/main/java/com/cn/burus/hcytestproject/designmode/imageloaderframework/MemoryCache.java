package com.cn.burus.hcytestproject.designmode.imageloaderframework;

import android.graphics.Bitmap;
import android.util.LruCache;

/**
 * 内存缓存类
 * Created by chengyou.huang on 2017/4/24.
 */

public class MemoryCache implements ImageCache {

    private LruCache<String, Bitmap> mMemoryCache;

    public MemoryCache() {
        initLruCache();
    }

    private void initLruCache() {
        //获取可用最大内存的四分之一作为内存缓存
        final int CacheSize = (int) (Runtime.getRuntime().maxMemory() / 1024) / 4;
        mMemoryCache = new LruCache<String, Bitmap>(CacheSize) {
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getRowBytes() * value.getHeight() / 1024;
            }
        };
    }

    @Override
    public Bitmap get(String url) {
        return mMemoryCache.get(url);
    }

    @Override
    public void put(String url, Bitmap bmp) {
        mMemoryCache.put(url, bmp);
    }
}
