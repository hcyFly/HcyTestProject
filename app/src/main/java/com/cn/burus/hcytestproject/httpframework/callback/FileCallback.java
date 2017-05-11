package com.cn.burus.hcytestproject.httpframework.callback;

import com.cn.burus.hcytestproject.httpframework.core.AbstractCallback;
import com.cn.burus.hcytestproject.httpframework.error.AppException;

/**
 * Created by hcywff on 2017/5/12.
 */

public abstract class FileCallback extends AbstractCallback<String> {

    /**
     * @param result
     * @return 返回为下载文件保存在SD 或手机上的路径
     * @throws AppException
     */
    @Override
    protected String bindData(String result) throws AppException {
        return result;
    }
}
