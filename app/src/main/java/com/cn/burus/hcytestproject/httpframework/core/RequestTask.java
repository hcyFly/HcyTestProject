package com.cn.burus.hcytestproject.httpframework.core;

import android.os.AsyncTask;

import com.cn.burus.hcytestproject.httpframework.Request;

/**
 * Created by chengyou.huang on 2017/5/11.
 */

public class RequestTask extends AsyncTask<Void,Integer,Object>{

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
        return null;
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
    }
}
