package com.itxiaogu.administrator.cnxw.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.itxiaogu.administrator.cnxw.R;
import com.itxiaogu.administrator.cnxw.bean.Campaign;
import com.itxiaogu.administrator.cnxw.bean.HomeCampaign;
import com.itxiaogu.administrator.cnxw.utils.UIUtils;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * RecyclerView适配器
 * Created by Administrator on 2017/10/30.
 */

public class HomeCatgoryAdapter extends RecyclerView.Adapter<HomeCatgoryAdapter.ViewHolder> {
    private  static int VIEW_TYPE_L=0;
    private  static int VIEW_TYPE_R=1;
    private List<HomeCampaign> datalis;
    private  OnCampaignClickListener mListener;
    public HomeCatgoryAdapter(List<HomeCampaign> datalis) {
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
        viewHolder.textTitle.setText(datalis.get(position).getTitle());
        Picasso.with(UIUtils.getContext()).load(datalis.get(position).getCpOne().getImgUrl()).into(viewHolder.imageViewBig);
        Picasso.with(UIUtils.getContext()).load(datalis.get(position).getCpTwo().getImgUrl()).into(viewHolder.imageViewSmallTop);
        Picasso.with(UIUtils.getContext()).load(datalis.get(position).getCpThree().getImgUrl()).into(viewHolder.imageViewSmallBottom);
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

   public  class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
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
            imageViewBig.setOnClickListener(this);
            imageViewSmallTop.setOnClickListener(this);
            imageViewSmallBottom.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            HomeCampaign homeCampaign = datalis.get(getLayoutPosition());
            if(mListener !=null){

                switch (v.getId()){

                    case  R.id.imgview_big:
                        mListener.onClick(v,homeCampaign.getCpOne());
                        break;

                    case  R.id.imgview_small_top:
                        mListener.onClick(v,homeCampaign.getCpTwo());
                        break;

                    case  R.id.imgview_small_bottom:
                        mListener.onClick(v,homeCampaign.getCpThree());
                        break;
                }
            }
        }
    }
    public void setOnCampaignClickListener(OnCampaignClickListener listener){
        this.mListener = listener;
    }
    public  interface OnCampaignClickListener{
        void onClick(View view,Campaign campaign);
    }
}
