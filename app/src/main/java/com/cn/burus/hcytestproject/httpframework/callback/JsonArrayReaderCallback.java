package com.cn.burus.hcytestproject.httpframework.callback;

import com.cn.burus.hcytestproject.httpframework.core.AbstractCallback;
import com.cn.burus.hcytestproject.httpframework.error.AppException;
import com.cn.burus.hcytestproject.httpframework.itf.JsonReaderable;
import com.google.gson.stream.JsonReader;

import java.io.FileReader;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * test okhttp
 */
public abstract class JsonArrayReaderCallback<T extends JsonReaderable> extends AbstractCallback<ArrayList<T>> {
    @Override
    protected ArrayList<T> bindData(String path) throws AppException {
        try {

            Type type = ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
            ArrayList<T> ts = new ArrayList<T>();
            T t;

            FileReader in = new FileReader(path);
            JsonReader reader = new JsonReader(in);
            String node;
            reader.beginObject();
            while (reader.hasNext()) {
                node = reader.nextName();
                if ("data".equalsIgnoreCase(node)) {
                    reader.beginArray();
                    while (reader.hasNext()) {
                        t = ((Class<T>) type).newInstance();
                        t.readFromJson(reader);
                        ts.add(t);
                    }
                    reader.endArray();
                } else {
                    reader.skipValue();
                }
            }
            reader.endObject();
            return ts;

        } catch (Exception e) {
            throw new AppException(AppException.ErrorType.JSON, e.getMessage());
        }
    }
}
