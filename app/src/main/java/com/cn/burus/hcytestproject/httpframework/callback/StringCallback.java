package com.cn.burus.hcytestproject.httpframework.callback;

import com.cn.burus.hcytestproject.httpframework.core.AbstractCallback;
import com.cn.burus.hcytestproject.httpframework.error.AppException;

/**
 * Created by hcywff on 2017/5/11.
 */

public abstract class StringCallback extends AbstractCallback<String> {

    @Override
    protected String bindData(String result) throws AppException {
        return result;
    }
}
