package com.itxiaogu.administrator.cnxw.http;

import com.google.gson.internal.$Gson$Types;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * http网络请求
 * @param <T> 服务器返回的数据
 */
public abstract class BaseCallback<T> {
    public   Type mType;

    static Type getSuperclassTypeParameter(Class<?> subclass)
    {
        Type superclass = subclass.getGenericSuperclass();
        if (superclass instanceof Class)
        {
            throw new RuntimeException("Missing type parameter.");
        }
        ParameterizedType parameterized = (ParameterizedType) superclass;
        return $Gson$Types.canonicalize(parameterized.getActualTypeArguments()[0]);
    }

    /**
     * 开始访问网络
     * @param url
     */
    public abstract void RequestStart(String url);

    public BaseCallback()
    {
        mType = getSuperclassTypeParameter(getClass());
    }
    /**
     * 请求失败时候调用
     * @param request
     * @param e
     */
    public abstract  void onFailure(Request request, Exception e) ;

    /**
     *请求成功时调用此方法
     * @param response
     */
    public abstract  void onResponse(Response response);

    /**
     *
     * 状态码大于200，小于300 时调用此方法（请求数据正常）
     * @param response
     * @param t
     * @throws IOException
     */
    public abstract void onSuccess(Response response,T t) ;

    /**
     * 请求数据失败
     * @param response
     * @param code 状态码
     */
    public abstract void onFail(Response response,int code);
    /**
     * 状态码400，404，403，500等时调用此方法（服务器异常）
     * @param response
     * @param code
     * @param e
     */
    public abstract void onError(Response response, int code,Exception e) ;

}