package com.itxiaogu.administrator.cnxw.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Administrator on 2017/11/16.
 */

public class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private SparseArray<View> views;

    private BaseRecyclerViewAdapter.OnItemClickListener mOnItemClickListener ;//item点击事件监听
    private BaseRecyclerViewAdapter.OnClickListener mOnClickListener ;//item点击事件监听

    public RecyclerViewHolder(View itemView, BaseRecyclerViewAdapter.OnItemClickListener onItemClickListener,BaseRecyclerViewAdapter.OnClickListener mOnClickListener){
        super(itemView);
        itemView.setOnClickListener(this);
        this.mOnItemClickListener =onItemClickListener;
        this.mOnClickListener=mOnClickListener;
        this.views = new SparseArray<View>();
    }

    public TextView getTextView(int viewId) {
        return retrieveView(viewId);
    }

    public Button getButton(int viewId) {
        return retrieveView(viewId);
    }

    public ImageView getImageView(int viewId) {
        return retrieveView(viewId);
    }

    public View getView(int viewId) {
        return retrieveView(viewId);
    }


    protected <T extends View> T retrieveView(int viewId) {
        View view = views.get(viewId);
        if (view == null) {
            view = itemView.findViewById(viewId);
            view.setOnClickListener(this);
            views.put(viewId, view);
        }
        return (T) view;
    }

    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            mOnItemClickListener.onItemClick(v,getLayoutPosition());
        }
        if (mOnClickListener != null) {
            mOnClickListener.onClicklistener(v,getLayoutPosition());
        }
    }

}
