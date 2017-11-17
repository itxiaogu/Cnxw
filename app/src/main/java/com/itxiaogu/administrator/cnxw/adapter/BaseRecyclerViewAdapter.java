package com.itxiaogu.administrator.cnxw.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.itxiaogu.administrator.cnxw.utils.UIUtils;

import java.util.List;

/**
 * RecyclerViewAdapter封装
 * Created by Administrator on 2017/11/16.
 */

public abstract class BaseRecyclerViewAdapter<T,H extends RecyclerViewHolder> extends RecyclerView.Adapter<RecyclerViewHolder>{
    protected List<T> listdata;
    private OnItemClickListener mOnItemClickListener = null;
    private OnClickListener mOnClickListener = null;
    public BaseRecyclerViewAdapter(List<T> listdata){
        this.listdata=listdata;
    }
    public List<T>  getListdata(){
        return listdata;
    }
    public void setListdata(List<T> listdata){
        this.listdata=listdata;
    }
    /**
     * 刷新数据
     * @param listdata 新数据
     */
    public void refrehView(List<T> listdata){
        this.listdata.clear();
        this.listdata.addAll(listdata);
        notifyItemRangeRemoved(0,listdata.size());//从第0条刷新到第i条
    }

    /**
     * 添加数据
     * @param listdata 需要添加的数据
     */
    public void addData(List<T> listdata){
        int position=this.listdata.size();
        if (listdata!=null&&listdata.size()>0){
            this.listdata.addAll(listdata);
            notifyItemRangeChanged(position, this.listdata.size());
        }
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecyclerViewHolder(UIUtils.inflate(getItemView(viewType),parent),mOnItemClickListener,mOnClickListener);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        convert((H)holder, listdata.get(position),position);
    }
    @Override
    public int getItemCount() {
        return listdata.size();
    }

    /**
     * item单击事件监听
     * @param listener
     */
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }
    public  interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    /**
     * view 单击事件监听
     * @param listener
     */
    public void setOnClickListener(OnClickListener listener) {
        this.mOnClickListener = listener;
    }
    public  interface OnClickListener {
        void onClicklistener(View view, int position);
    }

    /**
     * item 布局
     * @param viewType 布局id
     * @return
     */
    public abstract int getItemView( int viewType);

    /**
     * view 与 data 数据绑定
     * @param viewholder
     * @param data
     * @param viewType
     */
    public abstract void convert(H viewholder,T data, int viewType);
}
