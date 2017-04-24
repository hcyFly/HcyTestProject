package com.cn.burus.hcytestproject.designmode.imageloaderframework;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 图片下载框架
 * Created by chengyou.huang on 2017/4/24.
 */

public class ImageLoader {

    //线程池，线程数量为CPU核的数量
    ExecutorService mExecutorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    //图片缓存类 依赖于抽象，并默认内存缓存
    ImageCache mImageCache = new MemoryCache();

    //注入缓存实现 依赖于抽象(使用时 自己决定使用缓存策略 在此设置 也可以自己实现缓存)
    public void setImageCache(ImageCache cache) {
        mImageCache = cache;
    }

    /**
     * 显示图片
     *
     * @param imageUrl
     * @param imageView
     */
    public void displayImage(String imageUrl, ImageView imageView) {
        Bitmap bitmap = mImageCache.get(imageUrl);
        if (bitmap != null) {
            imageView.setImageBitmap(bitmap);
            return;
        }
        //图片没有缓存，提交到线程池下载
        submitloadRequest(imageUrl, imageView);
    }

    /**
     * 网络下载图片
     *
     * @param imageUrl
     * @param imageView
     */
    private void submitloadRequest(final String imageUrl, final ImageView imageView) {

        imageView.setTag(imageUrl);
        mExecutorService.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    Bitmap bitmap = downloadImage(imageUrl);
                    if (bitmap == null) {
                        return;
                    }
                    if (imageView.getTag().equals(imageUrl)) {
                        imageView.setImageBitmap(bitmap);
                    }
                    mImageCache.put(imageUrl, bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * 网络下载图片
     *
     * @param imageUrl
     * @return
     * @throws IOException
     */
    public Bitmap downloadImage(String imageUrl) throws IOException {
        Bitmap bitmap = null;
        try {
            URL url = new URL(imageUrl);
            final HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            bitmap = BitmapFactory.decodeStream(conn.getInputStream());
            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    //
}
