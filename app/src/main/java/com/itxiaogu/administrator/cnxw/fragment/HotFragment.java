package com.itxiaogu.administrator.cnxw.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.itxiaogu.administrator.cnxw.Contants;
import com.itxiaogu.administrator.cnxw.R;
import com.itxiaogu.administrator.cnxw.adapter.DividerItemDecortion;
import com.itxiaogu.administrator.cnxw.adapter.HotWaresAdapter;
import com.itxiaogu.administrator.cnxw.bean.Page;
import com.itxiaogu.administrator.cnxw.bean.Wares;
import com.itxiaogu.administrator.cnxw.http.OkHttpHelper;
import com.itxiaogu.administrator.cnxw.http.SpotsCallBack;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.squareup.okhttp.Response;

import java.util.List;


/**
 * 热卖
 * Created by Ivan on 15/9/22.
 */
public class HotFragment extends Fragment {
    private  static final int STATE_NORMAL=0;//正常加载
    private  static final int STATE_REFREH=1;//下拉刷新
    private  static final int STATE_MORE=2;//上拉加载更多
    private int state=STATE_NORMAL;//当前状态
    private int currPage=1;//第几页
    private int totalCount=1;//总数据量
    private int pageSize=15;//每页数据条数
    private List<Wares> datas;//分页加载每页的数据
    @ViewInject(R.id.recyclerview)
    private RecyclerView mRecyclerView;
    @ViewInject(R.id.refresh_view)//下拉刷星，上拉加载更多
    private MaterialRefreshLayout mRefreshLaout;
    private HotWaresAdapter mAdatper;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_hot,container,false);
        ViewUtils.inject(this,view);
        initRefreshLayout();
        getwebserverData(currPage,pageSize);
        return view ;
    }

    /**
     * 从服务器分页获取数据
     * @param Page 第几页数据
     * @param Size 每页数据量
     */
    private void getwebserverData(int Page,int Size) {
        String url = Contants.API.WARES_HOT+"?curPage="+Page+"&pageSize="+Size;
        OkHttpHelper.performNetworkRequest().httpGet(url, new SpotsCallBack<Page<Wares>>(getContext()) {
            @Override
            public void onSuccess(Response response, Page<Wares> waresPage) {
                datas = waresPage.getList();
                currPage = waresPage.getCurrentPage();
                totalCount =waresPage.getTotalCount();
                showData();
            }
            @Override
            public void onError(Response response, int code, Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * 初始化MaterialRefreshLayout控件(上拉加载更多，下拉刷新)
     */
    private  void initRefreshLayout(){
        mRefreshLaout.setLoadMore(true);
        mRefreshLaout.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
                refreshData();
            }

            @Override
            public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {
                loadMoreData();
            }
        });
    }

    /**
     * 下拉刷新
     */
    private void refreshData(){
        state=STATE_REFREH;
        getwebserverData(1,pageSize);
    }

    private void loadMoreData(){
        if(mAdatper.getItemCount()!=totalCount) {
            currPage++;
            state=STATE_MORE;
            getwebserverData(currPage,pageSize);
        }else{
            Toast.makeText(getContext(),"到底啦",Toast.LENGTH_SHORT).show();
            mRefreshLaout.finishRefreshLoadMore();
        }
    }
    private void showData() {
        switch (state) {
            case STATE_NORMAL://正常加载
                mAdatper = new HotWaresAdapter(datas);
                mRecyclerView.setAdapter(mAdatper);
                mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                mRecyclerView.setItemAnimator(new DefaultItemAnimator());//动画
                mRecyclerView.addItemDecoration(new DividerItemDecortion(getContext(),DividerItemDecortion.VERTICAL_LIST));
                break;
            case STATE_REFREH://下拉刷新
                mAdatper.refrehView(datas);
                mRecyclerView.scrollToPosition(0);//滚动（显示）第0个item
                mRefreshLaout.finishRefresh();//关闭下拉刷新
                break;
            case STATE_MORE://上拉加载更多
                mAdatper.addData(datas);
                mRefreshLaout.finishRefreshLoadMore();//关闭上拉加载更多
                break;
        }
    }
}
