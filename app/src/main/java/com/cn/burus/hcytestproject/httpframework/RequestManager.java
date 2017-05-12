package com.cn.burus.hcytestproject.httpframework;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by chengyou.huang on 2017/5/12.
 */

public class RequestManager {

    /**
     * 线程池
     */
    private final ExecutorService mExecutors;
    /**
     * 缓存请求任务
     */
    private HashMap<String, ArrayList<Request>> mCachedRequest;

    private static class RequestManagerHolder {
        private static final RequestManager INSTANCE = new RequestManager();
    }

    private RequestManager() {
        mCachedRequest = new HashMap<String, ArrayList<Request>>();
        mExecutors = Executors.newFixedThreadPool(5);
    }

    public static final RequestManager getInstance() {
        return RequestManagerHolder.INSTANCE;
    }

    public void performRequest(Request request) {
        request.execute(mExecutors);
        if (request.tag == null) {
            return;// no need to cache the request
        }
        if (!mCachedRequest.containsKey(request.tag)) {
            ArrayList<Request> requests = new ArrayList<Request>();
            mCachedRequest.put(request.tag, requests);
        }
        mCachedRequest.get(request.tag).add(request);
    }

    public void cancelRequest(String tag) {
        cancelRequest(tag, false);
    }

    /**
     * 取消指定的请求
     * 在BaseActivity的onStop()方法中调用 RequestManager.getInstance().cancelRequest(toString(),true);
     *
     * @param tag   使用toString() 方法保证唯一性
     * @param force true cancel task with no callback, false cancel task with callback as CancelException
     */
    public void cancelRequest(String tag, boolean force) {
        if (tag == null || "".equals(tag.trim())) {
            return;
        }
        if (mCachedRequest.containsKey(tag)) {
            ArrayList<Request> requests = mCachedRequest.remove(tag);
            for (Request request : requests) {
                if (!request.isCompleted && !request.isCancelled) {
                    request.cancel(force);
                }
            }
        }

    }

    /**
     * 取消所有请求
     */
    public void cancelAll() {
        for (Map.Entry<String, ArrayList<Request>> entry : mCachedRequest.entrySet()) {
            ArrayList<Request> requests = entry.getValue();
            for (Request request : requests) {
                if (!request.isCompleted && !request.isCancelled) {
                    request.cancel(true);
                }
            }
        }
        mCachedRequest.clear();
    }


}
