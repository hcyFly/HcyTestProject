package com.cn.burus.hcytestproject.httpframework.callback;

import com.cn.burus.hcytestproject.httpframework.core.AbstractCallback;
import com.cn.burus.hcytestproject.httpframework.error.AppException;
import com.cn.burus.hcytestproject.httpframework.itf.JsonReaderable;
import com.google.gson.stream.JsonReader;
import com.socks.library.KLog;

import java.io.FileReader;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by Stay on 4/7/15.
 * Powered by www.stay4it.com
 */
public abstract class JsonReaderCallback<T extends JsonReaderable> extends AbstractCallback<T> {
    /**
     * @param path //网络请求服务器返回数据保存的文件路径
     *             (注：此路径需要在请求时 request中设置)
     * @return
     * @throws AppException
     */
    @Override
    protected T bindData(String path) throws AppException {
        try {
            Type type = ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
            T t = ((Class<T>) type).newInstance();

            FileReader in = new FileReader(path);
            JsonReader reader = new JsonReader(in);
            KLog.i("---bindData:" + reader.toString());
            reader.beginObject();
            while (reader.hasNext()) {

                t.readFromJson(reader);

                reader.skipValue();


            }
            reader.endObject();
            return t;

        } catch (Exception e) {
            throw new AppException(AppException.ErrorType.JSON, e.getMessage());
        }
    }
}
