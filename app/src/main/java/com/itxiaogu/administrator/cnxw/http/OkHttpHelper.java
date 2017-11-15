package com.itxiaogu.administrator.cnxw.http;

import android.widget.Toast;

import com.itxiaogu.administrator.cnxw.bean.Banner;
import com.itxiaogu.administrator.cnxw.utils.JsonUtils;
import com.itxiaogu.administrator.cnxw.utils.ThreadManager;
import com.itxiaogu.administrator.cnxw.utils.UIUtils;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2017/11/1.
 */

public class OkHttpHelper {
    private OkHttpClient mHttpClient;
    private  static  OkHttpHelper mInstance;
    static {
        mInstance=new OkHttpHelper();
    }
    public OkHttpHelper(){
        mHttpClient = new OkHttpClient();
        mHttpClient.setConnectTimeout(10, TimeUnit.SECONDS);
        mHttpClient.setReadTimeout(10,TimeUnit.SECONDS);
        mHttpClient.setWriteTimeout(30,TimeUnit.SECONDS);
    }
    public static OkHttpHelper performNetworkRequest(){
        return mInstance;
    }
    /**
     * http get请求数据(在线程池中执行)
     * @param url 请求地址
     * @param callback 回调接口
     */
    public void httpGet(String url,BaseCallback callback){
        httpEnquene(url,OkHttpHelperEnum.GET,null,callback);
    }

    /**
     * http post请求数据(在线程池中执行)
     * @param url 请求地址
     * @param parameter 请求参数
     * @param callback 回调接口
     */
    public void httpPost(String url,Map<String,String> parameter,BaseCallback callback){
        httpEnquene(url,OkHttpHelperEnum.POST,parameter,callback);
    }
    private void httpEnquene(final String url, final OkHttpHelperEnum okHttpHelperEnum, final Map<String,String> parameter, final BaseCallback callback){
        ThreadManager.getThreadPool().execute(new Runnable() {
            @Override
            public void run() {
                UIUtils.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        callback.RequestStart(url);
                    }
                });
                RequestBody body =builderFormData(parameter);//添加请求参数
                Request request=null;
                if (okHttpHelperEnum==OkHttpHelperEnum.GET){
                    request= new Request.Builder().url(url).get().build();
                }else {
                    request= new Request.Builder().url(url).post(body).build();
                }
                mHttpClient.newCall(request).enqueue(new Callback() {//异步请求数据
                    @Override
                    public void onFailure(final Request request, final IOException e) {
                        UIUtils.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                callback.onFailure(request,e);
                            }
                        });
                    }
                    @Override
                    public void onResponse(final Response response) throws IOException {
                        UIUtils.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                callback.onResponse(response);
                            }
                        });
                        if(response.isSuccessful()) {//判断是否请求数据成功
                            try {
                                final String resultStr = response.body().string();
                                UIUtils.runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        callback.onSuccess(response,JsonUtils.getGsonUtils().jsonstrToData(resultStr,callback));
                                    }
                                });
                            } catch (final Exception e){ // Json解析的错误
                                UIUtils.runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        callback.onError(response,response.code(),e);
                                    }
                                });
                            }
                        } else {
                            UIUtils.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    callback.onFail(response,response.code());
                                }
                            });
                        }
                    }
                });
            }
        });
    }
    private RequestBody builderFormData(Map<String,String> params){
        FormEncodingBuilder builder = new FormEncodingBuilder();
        if(params !=null){
            for (Map.Entry<String,String> entry :params.entrySet() ){
                builder.add(entry.getKey(),entry.getValue());
            }
        }
        return  builder.build();
    }
    enum OkHttpHelperEnum{
        GET,POST
    }
}
