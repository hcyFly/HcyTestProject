package com.cn.burus.hcytestproject.httpframework.core;

import android.webkit.URLUtil;

import com.cn.burus.hcytestproject.httpframework.Request;
import com.cn.burus.hcytestproject.httpframework.error.AppException;
import com.cn.burus.hcytestproject.httpframework.itf.OnProgressUpdatedListener;
import com.cn.burus.hcytestproject.httpframework.upload.UploadUtil;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.OkUrlFactory;

/**
 * Created by chengyou.huang on 2017/5/11.
 */

public class OKHttpUrlConnectionUtil {

    private static OkHttpClient mClient;

    private static void initializeOkHttp() {
        mClient = new OkHttpClient();
    }

    public synchronized static HttpURLConnection execute(Request request, OnProgressUpdatedListener listener) throws AppException {

        if (!URLUtil.isNetworkUrl(request.url)) {
            throw new AppException(AppException.ErrorType.MANUAL, "the url :" + request.url + " is not valid");
        }
        if (mClient == null) {
            initializeOkHttp();
        }
        switch (request.method) {
            case GET:
            case DELETE:
                return get(request);
            case POST:
            case PUT:
                return post(request, listener);
        }
        return null;
    }

    /**
     * post 请求
     *
     * @param request
     * @param listener
     * @return
     * @throws AppException
     */
    private static HttpURLConnection post(Request request, OnProgressUpdatedListener listener) throws AppException {
        HttpURLConnection connection = null;
        OutputStream os = null;
        try {
            request.checkIfCancelled();

            connection = new OkUrlFactory(mClient).open(new URL(request.url));
            connection.setRequestMethod(request.method.name());
            connection.setConnectTimeout(15 * 3000);
            connection.setReadTimeout(15 * 3000);
            connection.setDoOutput(true);

            addHeader(connection, request.headers);
            request.checkIfCancelled();

            os = connection.getOutputStream();
            if (request.filePath != null) {
                UploadUtil.upload(os, request.filePath);
            } else if (request.fileEntities != null) {
                UploadUtil.upload(os, request.content, request.fileEntities, listener);
            } else if (request.content != null) {
                os.write(request.content.getBytes());
            } else {
                throw new AppException(AppException.ErrorType.MANUAL, "the post request has no post content");
            }

            request.checkIfCancelled();
        } catch (InterruptedIOException e) {
            throw new AppException(AppException.ErrorType.TIMEOUT, e.getMessage());
        } catch (IOException e) {
            throw new AppException(AppException.ErrorType.SERVER, e.getMessage());
        } finally {
            try {
                os.flush();
                os.close();
            } catch (IOException e) {
                throw new AppException(AppException.ErrorType.IO, "the post outputstream can't be closed");
            }
        }

        return connection;
    }

    /**
     * get请求
     *
     * @param request
     * @return
     * @throws AppException
     */
    private static HttpURLConnection get(Request request) throws AppException {

        try {
            request.checkIfCancelled();

            HttpURLConnection connection = new OkUrlFactory(mClient).open(new URL(request.url));
            connection.setRequestMethod(request.method.name());
            connection.setConnectTimeout(15 * 3000);
            connection.setReadTimeout(15 * 3000);
            addHeader(connection, request.headers);

            request.checkIfCancelled();
            return connection;
        } catch (MalformedURLException e) {
            throw new AppException(AppException.ErrorType.SERVER, e.getMessage());
        } catch (ProtocolException e) {
            throw new AppException(AppException.ErrorType.SERVER, e.getMessage());
        }
    }

    /**
     * 添加请求头
     *
     * @param connection
     * @param headers
     */
    private static void addHeader(HttpURLConnection connection, Map<String, String> headers) {
        if (headers == null || headers.size() == 0)
            return;

        for (Map.Entry<String, String> entry : headers.entrySet()) {
            connection.addRequestProperty(entry.getKey(), entry.getValue());
        }
    }
}
