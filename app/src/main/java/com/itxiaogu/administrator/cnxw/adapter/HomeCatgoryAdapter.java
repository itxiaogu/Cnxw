package com.itxiaogu.administrator.cnxw.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.itxiaogu.administrator.cnxw.R;
import com.itxiaogu.administrator.cnxw.bean.HomeCategory;
import com.itxiaogu.administrator.cnxw.utils.UIUtils;

import java.util.List;

/**
 * RecyclerView适配器
 * Created by Administrator on 2017/10/30.
 */

public class HomeCatgoryAdapter extends RecyclerView.Adapter<HomeCatgoryAdapter.ViewHolder> {
    private  static int VIEW_TYPE_L=0;
    private  static int VIEW_TYPE_R=1;
    private List<HomeCategory> datalis;

    public HomeCatgoryAdapter(List<HomeCategory> datalis) {
        this.datalis = datalis;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType==VIEW_TYPE_L){
            return new ViewHolder(UIUtils.inflate(R.layout.template_home_cardview_l,parent));
        }else {
            return new ViewHolder(UIUtils.inflate(R.layout.template_home_cardview_r,parent));
        }
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        viewHolder.textTitle.setText(datalis.get(position).getName());
        viewHolder.imageViewBig.setImageResource(datalis.get(position).getImgBig());
        viewHolder.imageViewSmallTop.setImageResource(datalis.get(position).getImgSmallTop());
        viewHolder.imageViewSmallBottom.setImageResource(datalis.get(position).getImgSmallBottom());
    }

    @Override
    public int getItemCount() {
        return datalis.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position % 2==0){
            return VIEW_TYPE_R;
        }else {
            return VIEW_TYPE_L;
        }
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView textTitle;
        ImageView imageViewBig;
        ImageView imageViewSmallTop;
        ImageView imageViewSmallBottom;
        public ViewHolder(View itemView) {
            super(itemView);
            textTitle = (TextView) itemView.findViewById(R.id.text_title);
            imageViewBig = (ImageView) itemView.findViewById(R.id.imgview_big);
            imageViewSmallTop = (ImageView) itemView.findViewById(R.id.imgview_small_top);
            imageViewSmallBottom = (ImageView) itemView.findViewById(R.id.imgview_small_bottom);
        }
    }
}
