package com.itxiaogu.administrator.cnxw.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/10/30.
 */

public class HomeCategory implements Serializable {
    private String name;
    private int imgBig;
    private int imgSmallTop;
    private int imgSmallBottom;

    public HomeCategory(String name, int imgBig, int imgSmallTop, int imgSmallBottom) {
        this.name = name;
        this.imgBig = imgBig;
        this.imgSmallTop = imgSmallTop;
        this.imgSmallBottom = imgSmallBottom;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImgBig() {
        return imgBig;
    }

    public void setImgBig(int imgBig) {
        this.imgBig = imgBig;
    }

    public int getImgSmallTop() {
        return imgSmallTop;
    }

    public void setImgSmallTop(int imgSmallTop) {
        this.imgSmallTop = imgSmallTop;
    }

    public int getImgSmallBottom() {
        return imgSmallBottom;
    }

    public void setImgSmallBottom(int imgSmallBottom) {
        this.imgSmallBottom = imgSmallBottom;
    }
}
