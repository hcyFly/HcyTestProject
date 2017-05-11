package com.cn.burus.hcytestproject.httpframework.itf;

import com.cn.burus.hcytestproject.httpframework.error.AppException;
import com.cn.burus.hcytestproject.utils.T;

/**
 * Created by chengyou.huang on 2017/5/11.
 */

public interface ICallback {

    void onSuccess(T result);

    void onFailure(AppException e);


    void cancel();
}
