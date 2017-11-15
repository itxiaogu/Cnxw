package com.itxiaogu.administrator.cnxw.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.itxiaogu.administrator.cnxw.Contants;
import com.itxiaogu.administrator.cnxw.R;
import com.itxiaogu.administrator.cnxw.adapter.DividerItemDecortion;
import com.itxiaogu.administrator.cnxw.adapter.HomeCatgoryAdapter;
import com.itxiaogu.administrator.cnxw.bean.Banner;
import com.itxiaogu.administrator.cnxw.bean.Campaign;
import com.itxiaogu.administrator.cnxw.bean.HomeCampaign;
import com.itxiaogu.administrator.cnxw.http.OkHttpHelper;
import com.itxiaogu.administrator.cnxw.http.SpotsCallBack;
import com.itxiaogu.administrator.cnxw.utils.LogUtils;
import com.itxiaogu.administrator.cnxw.utils.UIUtils;
import com.squareup.okhttp.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 主页
 * Created by Ivan on 15/9/25.
 */
public class HomeFragment extends Fragment {
    private Toolbar mToolBar;//标题栏
    private SliderLayout mSliderLayout;//广告轮播
    private PagerIndicator mPagerIndicator;//广告轮播指示栏
    private RecyclerView mRecyclerView;
    private HomeCatgoryAdapter mAdatper;
    private Activity mActivity;
    private List<Banner> mBannerList;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_home,container,false);
        mActivity=getActivity();
        initView(view);
//        initToolbar(view);
        initDatas();
        initEvent();
        return  view;
    }
    private void initEvent(){

    }
    private void initDatas(){
        requestSliderResources();
        requstMerchandiseResources();
    }

    /**
     * get请求商品列表
     */
    private void requstMerchandiseResources(){
        OkHttpHelper.performNetworkRequest().httpGet(Contants.API.CAMPAIGN_HOME, new SpotsCallBack<List<HomeCampaign>>(mActivity) {

            @Override
            public void onSuccess(Response response, List<HomeCampaign> campaigns) {
                initRecycler(campaigns);
            }
            @Override
            public void onError(Response response, int code, Exception e) {
                LogUtils.d("code:"+code);e.printStackTrace();
            }
        });
    }
    /**
     * get请求广告轮播数据
     */
    private void requestSliderResources(){
        Map<String,String> parameter=new HashMap<>();
        parameter.put("type","1");
        OkHttpHelper.performNetworkRequest().httpPost(Contants.API.Advertising_HOME, parameter, new SpotsCallBack<List<Banner>>(mActivity) {

            @Override
            public void onSuccess(Response response, List<Banner> banners) {
                mBannerList=banners;
                initSlider();
            }

            @Override
            public void onError(Response response, int code, Exception e) {
                Toast.makeText(mActivity,"",Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 初始化商品列表
     */
    private void initRecycler(List<HomeCampaign> campaigns) {
        mAdatper = new HomeCatgoryAdapter(campaigns);
        mRecyclerView.setAdapter(mAdatper);
        mRecyclerView.addItemDecoration(new DividerItemDecortion(getContext(),DividerItemDecortion.VERTICAL_LIST));//设置分割线
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));//设置布局
        mAdatper.setOnCampaignClickListener(new HomeCatgoryAdapter.OnCampaignClickListener() {
            @Override
            public void onClick(View view, Campaign campaign) {
                Toast.makeText(getContext(),"title="+campaign.getTitle(),Toast.LENGTH_LONG).show();
            }
        });
    }

    private void initView(View view) {
        mToolBar=(Toolbar)view.findViewById(R.id.toolbar);
        mSliderLayout=(SliderLayout)view.findViewById(R.id.slider);
        mPagerIndicator=(PagerIndicator)view.findViewById(R.id.custom_indicator);
        mRecyclerView=(RecyclerView)view.findViewById(R.id.recyclerview);
    }
    private void initSlider() {
        if (mBannerList!=null){
            for (Banner data:mBannerList){
                TextSliderView textSliderView = new TextSliderView(mActivity);
                textSliderView.image(data.getImgUrl());
                textSliderView.description(data.getName());//提示信息
                textSliderView.setScaleType(BaseSliderView.ScaleType.Fit);
                textSliderView.setOnSliderClickListener(new BaseSliderView.OnSliderClickListener() {//单击事件监听
                    @Override
                    public void onSliderClick(BaseSliderView baseSliderView) {
                        Toast.makeText(HomeFragment.this.getActivity(),baseSliderView.getDescription(),Toast.LENGTH_LONG).show();
                    }
                });
                mSliderLayout.addSlider(textSliderView);
            }
        }
        mSliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);//设置默认的广告轮播指示栏
//        mSliderLayout.setCustomIndicator(mPagerIndicator);//设置指示器
        mSliderLayout.setCustomAnimation(new DescriptionAnimation());//设置动画
        mSliderLayout.setPresetTransformer(SliderLayout.Transformer.RotateUp);//设置转场效果
        mSliderLayout.setDuration(3000);//设置动画时间
        mSliderLayout.addOnPageChangeListener(new ViewPagerEx.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
//                LogUtils.d("onPageScrolled");
            }

            @Override
            public void onPageSelected(int i) {
//                LogUtils.d("onPageSelected");
            }
            @Override
            public void onPageScrollStateChanged(int i) {
//                LogUtils.d("onPageScrollStateChanged");
            }
        });
    }




    /**
     * 初始化菜单栏
     */
    private void initToolbar(){
        mToolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(UIUtils.getContext(),"返回按钮事件响应",Toast.LENGTH_LONG).show();
            }
        });
        mToolBar.inflateMenu(R.menu.menu_main);//绑定下拉菜单
        mToolBar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {//给下拉菜单设置监听
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.action_settings:
                        Toast.makeText(UIUtils.getContext(),"action_settings Clicked",Toast.LENGTH_LONG).show();
                        break;
                    case R.id.action_help:
                        Toast.makeText(UIUtils.getContext(),"action_help Clicked",Toast.LENGTH_LONG).show();
                        break;
                }
                return  true;//事件是否处理
            }
        });

    }
    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mSliderLayout.stopAutoCycle();
    }
}
