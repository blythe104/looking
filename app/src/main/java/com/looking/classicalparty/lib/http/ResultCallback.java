package com.looking.classicalparty.lib.http;

import com.google.gson.internal.$Gson$Types;
import com.squareup.okhttp.Request;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * User: YangHai (1454025171@qq.com)
 * Date: 2016-10-19
 * Time: 10:50
 */
public abstract class ResultCallback<T> {
    Type mType;

    public ResultCallback() {
        mType = getSuperclassTypeParameter(getClass());
    }

    static Type getSuperclassTypeParameter(Class<?> subclass) {
        Type superclass = subclass.getGenericSuperclass();
        if (superclass instanceof Class) {
            throw new RuntimeException("Missing type parameter.");
        }
        ParameterizedType parameterized = (ParameterizedType) superclass;
        return $Gson$Types.canonicalize(parameterized.getActualTypeArguments()[0]);
    }

    /**
     * 请求成功回调
     *
     * @param response
     */
    public abstract void onSuccess(T response);

    /**
     * 请求失败回调
     *
     * @param e
     */
    public abstract void onFailure(Request request, Exception e);

    public void onLoading(long total, long current, boolean isUploading) {
    }
}
