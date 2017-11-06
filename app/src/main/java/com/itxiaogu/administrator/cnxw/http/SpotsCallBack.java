package com.itxiaogu.administrator.cnxw.http;

import android.content.Context;

import com.itxiaogu.administrator.cnxw.utils.UIUtils;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import dmax.dialog.SpotsDialog;

/**
 * Created by Administrator on 2017/11/2.
 */

public abstract class SpotsCallBack<T> extends BaseCallback<T> {
    private SpotsDialog mDialog;

    public SpotsCallBack(Context context) {
        mDialog = new SpotsDialog(context,"拼命加载中...");
    }
    @Override
    public void RequestStart(String url) {
        showDialog();
    }
    @Override
    public void onFailure(Request request, Exception e) {
        dismissDialog();
        onError(null,-1,e);
    }

    @Override
    public void onResponse(Response response) {
        dismissDialog();
    }

    @Override
    public void onFail(Response response, int code) {
        onError(response,code,new Exception("访问服务器成功，获取数据失败"));
    }
    public  void showDialog(){
        mDialog.show();
    }

    public  void dismissDialog(){
        mDialog.dismiss();
    }
}
