package com.itxiaogu.administrator.cnxw.adapter;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.itxiaogu.administrator.cnxw.R;
import com.itxiaogu.administrator.cnxw.bean.Wares;
import com.itxiaogu.administrator.cnxw.utils.UIUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/11/14.
 */

public class HotWaresAdapter extends RecyclerView.Adapter<HotWaresAdapter.ViewHolder>{
    private List<Wares> listdata;

    public HotWaresAdapter(List<Wares> listdata) {
        if (listdata!=null){
            this.listdata = listdata;
        }else {
            this.listdata=new ArrayList<>();
        }
    }
    public int getListDataSize(){
        return listdata.size();
    }
    /**
     * 刷新数据
     * @param listdata 新数据
     */
    public void refrehView(List<Wares> listdata){
        this.listdata.clear();
        this.listdata.addAll(listdata);
        notifyItemRangeRemoved(0,listdata.size());//从第0条刷新到第i条
    }

    /**
     * 添加数据
     * @param listdata 需要添加的数据
     */
    public void addData(List<Wares> listdata){
        int position=this.listdata.size();
        if (listdata!=null&&listdata.size()>0){
            this.listdata.addAll(listdata);
            notifyItemRangeChanged(position, this.listdata.size());
        }
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(UIUtils.inflate(R.layout.template_hot_wares,parent));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.draweeView.setImageURI(Uri.parse(listdata.get(position).getImgUrl()));
        holder.textTitle.setText(listdata.get(position).getName());
        holder.textPrice.setText("￥"+listdata.get(position).getPrice());
    }

    @Override
    public int getItemCount() {
        return listdata.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        SimpleDraweeView draweeView;
        TextView textTitle;
        TextView textPrice;
        public ViewHolder(View itemView) {
            super(itemView);
            draweeView = (SimpleDraweeView) itemView.findViewById(R.id.drawee_view);
            textTitle= (TextView) itemView.findViewById(R.id.text_title);
            textPrice= (TextView) itemView.findViewById(R.id.text_price);
        }
    }
}
