package com.itxiaogu.administrator.cnxw.utils;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.itxiaogu.administrator.cnxw.myapplication.MyApplication;


/**
 * Created by Administrator on 2017/1/6.
 */

public class UIUtils {
    public static Context getContext(){
        return MyApplication.getmContext();
    }
    public static int getMainThreadId(){
        return MyApplication.getMainThreadId();
    }
    public static Handler getHandler(){
        return MyApplication.getmHandler();
    }
    /********************* 获取资源*****************************/
    /**
     * 根据id获取字符串
     */
    public static String getString(int id) {
        return getContext().getResources().getString(id);
    }
    /**
     * 根据id获取图片
     */
    public static Drawable getDrawable(int id) {
        return getContext().getResources().getDrawable(id);
    }

    /**
     * 根据id获取颜色值
     */
    public static int getColor(int id) {
        return getContext().getResources().getColor(id);
    }

    /**
     * 获取颜色状态集合
     */
    public static ColorStateList getColorStateList(int id) {
        return getContext().getResources().getColorStateList(id);
    }

    /**
     * 根据id获取尺寸
     */
    public static int getDimen(int id) {
        return getContext().getResources().getDimensionPixelSize(id);
    }

    /**
     * 根据id获取字符串数组
     */
    public static String[] getStringArray(int id) {
        return getContext().getResources().getStringArray(id);
    }
    /********************* 数据转换*****************************/
    /**
     * dp转px
     */
    public static int dip2px(float dp) {
        float density = getContext().getResources().getDisplayMetrics().density;
        return (int) (density * dp + 0.5);
    }

    /**
     * px转dp
     */
    public static float px2dip(float px) {
        float density = getContext().getResources().getDisplayMetrics().density;
        return px / density;
    }
    /********************* 其他*****************************/
    /**
     * 加载布局文件
     */
    public static View inflate(int layoutId) {
        return inflate(getContext(), layoutId);
    }
    public static View inflate(Context context,int layoutId) {
        return View.inflate(context, layoutId, null);
    }
    public static View inflate(int layoutId, ViewGroup viewGroup) {
        return  LayoutInflater.from(viewGroup.getContext()).inflate(layoutId,viewGroup,false);
    }
    /**
     * 判断当前是否运行在主线程
     * @return
     */
    public static boolean isRunOnUiThread() {
        return getMainThreadId() == android.os.Process.myTid();
    }

    /**
     * 保证当前的操作运行在UI主线程
     *
     * @param runnable
     */
    public static void runOnUiThread(Runnable runnable) {
        if (isRunOnUiThread()) {
            runnable.run();
        } else {
            getHandler().post(runnable);
        }
    }

    /** 检查是否有网络 */
    public static boolean isNetworkAvailable(Context context) {
        NetworkInfo info = getNetworkInfo(context);
        if (info != null) {
            return info.isAvailable();
        }else{
            return false;
        }
    }

    private static NetworkInfo getNetworkInfo(Context context) {

        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo();
    }

}
