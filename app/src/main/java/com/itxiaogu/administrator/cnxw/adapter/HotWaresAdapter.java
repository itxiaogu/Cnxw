package com.itxiaogu.administrator.cnxw.adapter;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.itxiaogu.administrator.cnxw.R;
import com.itxiaogu.administrator.cnxw.bean.HomeCampaign;
import com.itxiaogu.administrator.cnxw.bean.Wares;
import com.itxiaogu.administrator.cnxw.utils.UIUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 热卖商品adapter
 * Created by Administrator on 2017/11/14.
 */

public class HotWaresAdapter extends BaseRecyclerViewAdapter<Wares,RecyclerViewHolder>{
    public HotWaresAdapter(List<Wares> listdata) {
        super(listdata);
    }
    @Override
    public int getItemView(int viewType) {
        return R.layout.template_hot_wares;
    }
    @Override
    public void convert(RecyclerViewHolder viewholder, Wares data, int viewType) {
        ((SimpleDraweeView)viewholder.getView(R.id.drawee_view)).setImageURI(Uri.parse(data.getImgUrl()));
        viewholder.getTextView(R.id.text_title).setText(data.getName());
        viewholder.getTextView(R.id.text_price).setText("￥"+data.getPrice());
    }
}
