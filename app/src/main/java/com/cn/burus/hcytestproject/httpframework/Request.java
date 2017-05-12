package com.cn.burus.hcytestproject.httpframework;

import android.os.Build;

import com.cn.burus.hcytestproject.httpframework.core.RequestTask;
import com.cn.burus.hcytestproject.httpframework.entites.FileEntity;
import com.cn.burus.hcytestproject.httpframework.error.AppException;
import com.cn.burus.hcytestproject.httpframework.itf.ICallback;
import com.cn.burus.hcytestproject.httpframework.itf.OnGlobalExceptionListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;

/**
 * Created by chengyou.huang on 2017/5/11.
 */

public class Request {

    //请求结果回调
    public ICallback iCallback;
    //是否需要进度回调
    public boolean enableProgressUpdated = false;
    //全局异常集中处理监听
    public OnGlobalExceptionListener onGlobalExceptionListener;
    //标识
    public String tag;
    //请求任务
    private RequestTask task;
    //是否请求完成
    public boolean isCompleted;
    //最大重链次数
    public int maxRetryCount = 3;
    //请求的url
    public String url;
    //内容
    public String content;
    //添加的请求头信息
    public Map<String, String> headers;
    //是否取消请求
    public volatile boolean isCancelled;
    //请求方式 get post ...
    public RequestMethod method;
    //请求使用的连接类（原生 httpurlconnection  或  okhttp）
    public RequestTool tool;
    //上传状态
    public static final int STATE_UPLOAD = 1;
    //下载状态
    public static final int STATE_DOWNLOAD = 2;
    //保存文件路径
    public String filePath;
    //上传文件封装实体类
    public ArrayList<FileEntity> fileEntities;

    public void setCallback(ICallback iCallback) {
        this.iCallback = iCallback;
    }

    public void enableProgressUpdated(boolean enable) {
        this.enableProgressUpdated = enable;
    }

    public void setGlobalExceptionListener(OnGlobalExceptionListener onGlobalExceptionListener) {
        this.onGlobalExceptionListener = onGlobalExceptionListener;
    }

    /**
     * 检查请求任务是否已取消请求
     *
     * @throws AppException
     */
    public void checkIfCancelled() throws AppException {
        if (isCancelled) {
            throw new AppException(AppException.ErrorType.CANCEL, "the request has been cancelled");
        }
    }

    public void cancel(boolean force) {
        isCancelled = true;
        iCallback.cancel();
        if (force && task != null) {
            task.cancel(force);
        }
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public void execute(Executor mExecutors) {
        task = new RequestTask(this);
        if (Build.VERSION.SDK_INT > 11) {
            //系统版本 > 3.0 AsyceTask中的线程池默认使用同步队列（只有一个线程 效率低）
            //因此这里我们自己传一个线程池（默认固定为 5 ）
            task.executeOnExecutor(mExecutors);
        } else {
            task.execute();
        }
    }


    public Request(String url, RequestMethod method) {
        this.url = url;
        this.method = method;
        this.tool = RequestTool.URLCONNECTION;
    }

    public Request(String url, RequestMethod method, RequestTool tool) {
        this.url = url;
        this.method = method;
        this.tool = tool;
    }

    public Request(String url) {
        this.url = url;
        this.method = RequestMethod.GET;
        this.tool = RequestTool.URLCONNECTION;
    }

    public Request(String url, RequestTool tool) {
        this.url = url;
        this.method = RequestMethod.GET;
        this.tool = tool;
    }

    /**
     * 添加请求头
     *
     * @param key
     * @param value
     */
    public void addHeader(String key, String value) {
        if (headers == null) {
            headers = new HashMap<String, String>();
        }
        headers.put(key, value);
    }


    //枚举  标识网络请求方式类型
    public enum RequestMethod {
        GET, POST, PUT, DELETE
    }

    // 枚举 求使用的连接实现方式（原生 或 三方okhttp）
    public enum RequestTool {
        OKHTTP, URLCONNECTION
    }
}
