package com.itxiaogu.administrator.cnxw.myapplication;

import android.app.Application;
import android.content.Context;
import android.os.Handler;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * 自定义Application
 * Created by Administrator on 2017/1/6.
 */

public class MyApplication extends Application {
    private static Context mContext;
    private static int mainThreadId;//main线程id
    private static Handler mHandler;
    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
        mContext=getApplicationContext();
        mainThreadId=android.os.Process.myTid();//获取当前线程id
        mHandler=new Handler();
    }
    public static Context getmContext() {
        return mContext;
    }

    public static int getMainThreadId() {
        return mainThreadId;
    }

    public static Handler getmHandler() {
        return mHandler;
    }
}
