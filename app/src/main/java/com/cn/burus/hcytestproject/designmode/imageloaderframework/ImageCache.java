package com.cn.burus.hcytestproject.designmode.imageloaderframework;

import android.graphics.Bitmap;

/**
 * 图片缓存接口
 * Created by chengyou.huang on 2017/4/24.
 */

public interface ImageCache {
    /**
     * 根据url 获取bitmap
     *
     * @param url
     * @return
     */
    public Bitmap get(String url);

    /**
     * 存储bitmap
     *
     * @param url
     * @param bmp
     */
    public void put(String url, Bitmap bmp);
}
