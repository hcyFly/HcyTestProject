package com.cn.burus.hcytestproject.httpframework.itf;

import com.cn.burus.hcytestproject.httpframework.error.AppException;

/**
 * 全局异常监听
 * Created by chengyou.huang on 2017/5/11.
 */

public interface OnGlobalExceptionListener {

    /**
     * @param e 自定义异常类
     * @return boolean
     */
    boolean handleException(AppException e);
}
