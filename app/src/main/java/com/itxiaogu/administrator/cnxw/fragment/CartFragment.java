package com.itxiaogu.administrator.cnxw.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.itxiaogu.administrator.cnxw.R;


/**
 * 购物车
 * Created by Ivan on 15/9/22.
 */
public class CartFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return  inflater.inflate(R.layout.fragment_cart,container,false);
    }
}
