package com.cn.burus.hcytestproject.httpframework.itf;

import com.cn.burus.hcytestproject.httpframework.error.AppException;

import java.net.HttpURLConnection;

/**
 * Created by chengyou.huang on 2017/5/11.
 */

public interface ICallback<T> {

    void onSuccess(T result);

    void onFailure(AppException e);


    /**
     * invoked on sub thread
     * 实现此方法处理服务器返回数据(在子线程处理一些耗时的转化操作)
     * 会在回调 onSuccess()方法之前处理数据
     *
     * @param t serialized by SubCallbac     * @return final result by calling onSuccess(t)
     */
    T postRequest(T t);

    /**
     * invoked on sub thread
     * 网络请求之前 查询此请求是否有缓存数据 实现此方法操作
     * 亦或根据实际项目策略规则 首先使用缓存优先 或是超出一定时间再去获取网络数据 灵活使用
     * 在此提供此功能而已
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
