package com.cn.burus.hcytestproject.httpframework.core;

import com.cn.burus.hcytestproject.httpframework.error.AppException;
import com.cn.burus.hcytestproject.httpframework.itf.ICallback;
import com.cn.burus.hcytestproject.httpframework.itf.OnProgressUpdatedListener;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.zip.GZIPInputStream;
import java.util.zip.InflaterInputStream;

/**
 * Created by hcywff on 2017/5/11.
 */

public abstract class AbstractCallback<T> implements ICallback<T> {

    /**
     * 路径
     */
    private String path;
    /**
     * 是否取消请求
     */
    private volatile boolean isCancelled;

    @Override
    public T parse(HttpURLConnection connection) throws AppException {
        //返回 object 类型
        return parse(connection, null);
    }

    @Override
    public T parse(HttpURLConnection connection, OnProgressUpdatedListener listener) throws AppException {
        try {
            checkIfCancelled();
            InputStream is = null;
            int status = connection.getResponseCode();
            // TODO:normally 200 represent success, but you should know 200-299 all means success, please ensure with your backend colleagues
            if (status == HttpURLConnection.HTTP_OK) {
                //请求返回成功
                // support gzip || deflate(获取后台返回的数据流信息)
                String encoding = connection.getContentEncoding();
                if (encoding != null && "gzip".equalsIgnoreCase(encoding))
                    is = new GZIPInputStream(connection.getInputStream());
                else if (encoding != null
                        && "deflate".equalsIgnoreCase(encoding))
                    is = new InflaterInputStream(connection.getInputStream());
                else {
                    is = connection.getInputStream();
                }

                if (path == null) {
                    //返回普通数据流
                    ByteArrayOutputStream out = new ByteArrayOutputStream();
                    byte[] buffer = new byte[2048];
                    int len;
                    while ((len = is.read(buffer)) != -1) {
                        checkIfCancelled();
                        out.write(buffer, 0, len);
                    }
                    is.close();
                    out.flush();
                    out.close();
                    String result = new String(out.toByteArray());
                    T t = bindData(result);
                    return postRequest(t);
                } else {
                    //返回文件类型数据流
                    FileOutputStream out = new FileOutputStream(path);
                    int totalLen = connection.getContentLength();
                    int curLen = 0;
                    byte[] buffer = new byte[2048];
                    int len;
                    int percent = 0;
                    while ((len = is.read(buffer)) != -1) {
                        checkIfCancelled();
                        out.write(buffer, 0, len);
                        if (curLen * 100l / totalLen > percent) {
                            curLen += len;
                            if (listener != null) {
                                listener.onProgressUpdated(curLen, totalLen);
                            }
                            percent = (int) (curLen * 100l / totalLen);
                        }
                    }
                    is.close();
                    out.flush();
                    out.close();
                    T t = bindData(path);
                    return postRequest(t);
                }
            } else {
                //后台返回异常
                throw new AppException(status, connection.getResponseMessage());
            }
        } catch (IOException e) {
            throw new AppException(AppException.ErrorType.SERVER, e.getMessage());
        }
    }

    protected void checkIfCancelled() throws AppException {
        if (isCancelled) {
            throw new AppException(AppException.ErrorType.CANCEL, "the request has been cancelled");
        }
    }

    @Override
    public void cancel() {
        isCancelled = true;
    }

    @Override
    public void onProgressUpdated(int state, int curLen, int totalLen) {

    }

    @Override
    public T postRequest(T t) {
        return t;
    }

    @Override
    public T preRequest() {
        return null;
    }

    protected abstract T bindData(String result) throws AppException;


    /**
     * 设置保存下载文件路径
     *
     * @param path
     * @return
     */
    public ICallback setCachePath(String path) {
        this.path = path;
        return this;
    }
}
