package com.itxiaogu.administrator.cnxw.adapter;

import android.view.View;

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

public class HomeCatgoryAdapter extends BaseRecyclerViewAdapter<HomeCampaign,RecyclerViewHolder> {
    private  static int VIEW_TYPE_L=0;
    private  static int VIEW_TYPE_R=1;

    public HomeCatgoryAdapter(List<HomeCampaign> listdata) {
        super(listdata);
    }

    @Override
    public int getItemView(int viewType) {
        if (viewType==VIEW_TYPE_L){
            return R.layout.template_home_cardview_l;
        }else {
            return R.layout.template_home_cardview_r;
        }
    }
    @Override
    public void convert(RecyclerViewHolder viewholder, final HomeCampaign data, int viewType) {
        viewholder.getTextView(R.id.text_title).setText(data.getTitle());
        Picasso.with(UIUtils.getContext()).load(data.getCpOne().getImgUrl()).into(viewholder.getImageView(R.id.imgview_big));
        Picasso.with(UIUtils.getContext()).load(data.getCpTwo().getImgUrl()).into(viewholder.getImageView(R.id.imgview_small_top));
        Picasso.with(UIUtils.getContext()).load(data.getCpThree().getImgUrl()).into(viewholder.getImageView(R.id.imgview_small_bottom));
    }

    @Override
    public int getItemViewType(int position) {
        if (position % 2==0){
            return VIEW_TYPE_R;
        }else {
            return VIEW_TYPE_L;
        }
    }
    private  OnCampaignClickListener mListener;
    private void onClicklistene(View view, int position) {
        if(mListener !=null){
            switch (view.getId()){
                case  R.id.imgview_big:
                    mListener.onClick(view,getListdata().get(position).getCpOne());
                    break;

                case  R.id.imgview_small_top:
                    mListener.onClick(view,getListdata().get(position).getCpTwo());
                    break;

                case  R.id.imgview_small_bottom:
                    mListener.onClick(view,getListdata().get(position).getCpThree());
                    break;
            }
        }
    }
    public void setOnCampaignClickListener(OnCampaignClickListener listener){
        this.mListener = listener;
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClicklistener(View view, int position) {
                onClicklistene(view,position);
            }
        });
    }
    public  interface OnCampaignClickListener{
        void onClick(View view, Campaign campaign);
    }
}
