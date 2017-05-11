package com.cn.burus.hcytestproject.httpframework.callback;

import com.cn.burus.hcytestproject.httpframework.core.AbstractCallback;
import com.cn.burus.hcytestproject.httpframework.error.AppException;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by hcywff on 2017/5/11.
 */

public abstract class JsonCallback<T> extends AbstractCallback<T> {

    @Override
    protected T bindData(String result) throws AppException {

        try {
            Gson gson = new Gson();
            //根据泛型获取具体类型
            Type type = ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
            return gson.fromJson(result, type);
        } catch (JsonSyntaxException e) {
            //json转化异常
            throw new AppException(AppException.ErrorType.JSON,e.getMessage());
        }
    }
}
