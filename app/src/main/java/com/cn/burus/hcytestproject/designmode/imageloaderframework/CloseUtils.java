package com.cn.burus.hcytestproject.designmode.imageloaderframework;

import java.io.Closeable;
import java.io.IOException;

/**
 * 对需要使用后必须要关闭的类 统一处理关门的类
 * Created by chengyou.huang on 2017/4/24.
 */

public final class CloseUtils {

    public CloseUtils() {
    }

    public static void closeQuietly(Closeable closeable) {

        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
