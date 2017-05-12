package com.cn.burus.hcytestproject.httpframework.core;

import android.os.AsyncTask;

import com.cn.burus.hcytestproject.httpframework.Request;
import com.cn.burus.hcytestproject.httpframework.error.AppException;
import com.cn.burus.hcytestproject.httpframework.itf.OnProgressUpdatedListener;

import java.net.HttpURLConnection;

/**
 * Created by chengyou.huang on 2017/5/11.
 */

public class RequestTask extends AsyncTask<Void, Integer, Object> {

    private Request request;

    public RequestTask(Request request) {
        this.request = request;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Object doInBackground(Void... params) {
        if (request.iCallback != null) {
            //请求时返回缓存数据（DB 或其他File  sp等其他  此处
            // 返回为null 则继续网络请求  不为空测使用缓存不再网络请求）
            Object o = request.iCallback.preRequest();
            if (o != null) {
                return o;
            }
        }
        return request(0);
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        request.isCompleted = true;
        if (o instanceof AppException) {
            if (request.onGlobalExceptionListener != null) {
                if (!request.onGlobalExceptionListener.handleException((AppException) o)) {
                    request.iCallback.onFailure((AppException) o);
                }
            } else {
                request.iCallback.onFailure((AppException) o);
            }
        } else {
            request.iCallback.onSuccess(o);
        }
    }

    @Override
    protected void onCancelled() {
        //asynctask 提供的取消执行方法
        super.onCancelled();
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        request.iCallback.onProgressUpdated(values[0], values[1], values[2]);
    }

    /**
     * 请求方法
     *
     * @param retry 重试
     * @return
     */
    public Object request(int retry) {
        try {
//                FIXME: for HttpUrlConnection
            HttpURLConnection connection = null;
            if (request.tool == Request.RequestTool.URLCONNECTION) {
                connection = HttpUrlConnectionUtil.execute(request, !request.enableProgressUpdated ? null : new OnProgressUpdatedListener() {
                    @Override
                    public void onProgressUpdated(int curLen, int totalLen) {
                        publishProgress(Request.STATE_UPLOAD, curLen, totalLen);
                    }
                });
            } else {
//                FIXME : for OkHttpUrlConnection request
                connection = OKHttpUrlConnectionUtil.execute(request, !request.enableProgressUpdated ? null : new OnProgressUpdatedListener() {
                    @Override
                    public void onProgressUpdated(int curLen, int totalLen) {
                        publishProgress(Request.STATE_UPLOAD, curLen, totalLen);
                    }
                });
            }
            if (request.enableProgressUpdated) {
                return request.iCallback.parse(connection, new OnProgressUpdatedListener() {
                    @Override
                    public void onProgressUpdated(int curLen, int totalLen) {
                        //调用此方法 会回调到主线程的 <onProgressUpdate()方法中>
                        publishProgress(Request.STATE_DOWNLOAD, curLen, totalLen);
                    }
                });
            } else {
                //返回服务器数据（在connection中）让parse进行解析
                return request.iCallback.parse(connection);
            }
        } catch (AppException e) {
            if (e.type == AppException.ErrorType.TIMEOUT) {
                //请求超时或服务器响应超时  重试处理
                if (retry < request.maxRetryCount) {
                    retry++;
                    return request(retry);
                }
            }
            return e;
        }
    }
}
