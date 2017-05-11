package com.cn.burus.hcytestproject.httpframework.itf;

import com.cn.burus.hcytestproject.httpframework.error.AppException;
import com.cn.burus.hcytestproject.utils.T;

import java.net.HttpURLConnection;

/**
 * Created by chengyou.huang on 2017/5/11.
 */

public interface ICallback<T> {

    void onSuccess(T result);

    void onFailure(AppException e);


    /**
     * invoked on sub thread
     *
     * @param t serialized by SubCallbacks
     * @return final result by calling onSuccess(t)
     */
    T postRequest(T t);

    /**
     * invoked on sub thread
     *
     * @return if not null, will skip the http request, call {@link #onSuccess(Object)} directly
     */
    T preRequest();

    /**
     * 解析服务器返回数据（有进度监听）
     *
     * @param connection
     * @param listener
     * @return
     * @throws AppException
     */
    T parse(HttpURLConnection connection, OnProgressUpdatedListener listener) throws AppException;

    /**
     * 解析服务器返回数据
     *
     * @param connection
     * @return
     * @throws AppException
     */
    T parse(HttpURLConnection connection) throws AppException;

    /**
     * 进度更新
     *
     * @param state    上传/下载 状态
     * @param curLen   当前进度
     * @param totalLen 总长度
     */
    void onProgressUpdated(int state, int curLen, int totalLen);


    void cancel();
}
