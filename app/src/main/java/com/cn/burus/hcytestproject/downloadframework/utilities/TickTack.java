package com.cn.burus.hcytestproject.downloadframework.utilities;

/**
 * 时钟
 */
public class TickTack {
    private static TickTack mInstance;
    private long mLastStamp;

    private TickTack() {

    }

    public synchronized static TickTack getInstance() {
        if (mInstance == null) {
            mInstance = new TickTack();
        }
        return mInstance;
    }

    public synchronized boolean needToNotify() {
        long stamp = System.currentTimeMillis();
        if (stamp - mLastStamp > 1000) {
            mLastStamp = stamp;
            return true;
        }
        return false;
    }
}
