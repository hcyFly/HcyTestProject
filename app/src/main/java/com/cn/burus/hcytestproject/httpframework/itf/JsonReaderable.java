package com.cn.burus.hcytestproject.httpframework.itf;

import com.cn.burus.hcytestproject.httpframework.error.AppException;
import com.google.gson.stream.JsonReader;

/**
 * 处理json 超大数据时使用
 */
public interface JsonReaderable {
    void readFromJson(JsonReader reader) throws AppException;

}
