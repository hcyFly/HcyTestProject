package com.cn.burus.hcytestproject.httpframework.itf;

/**
 * 进度更新监听
 * Created by chengyou.huang on 2017/5/11.
 */

public interface OnProgressUpdatedListener {

    /**
     * @param curLen   当前进度
     * @param totalLen 总长度
     */
     void onProgressUpdated(int curLen, int totalLen);
}
