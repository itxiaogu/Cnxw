package com.itxiaogu.administrator.cnxw.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
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
import com.itxiaogu.administrator.cnxw.R;
import com.itxiaogu.administrator.cnxw.adapter.DividerItemDecortion;
import com.itxiaogu.administrator.cnxw.adapter.HomeCatgoryAdapter;
import com.itxiaogu.administrator.cnxw.bean.HomeCategory;
import com.itxiaogu.administrator.cnxw.utils.LogUtils;
import com.itxiaogu.administrator.cnxw.utils.UIUtils;
import com.itxiaogu.administrator.cnxw.widget.FragmentTabHost;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;


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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_home,container,false);
        initView(view);
//        initToolbar(view);
        initSlider();//广告轮播
        initRecycler();
        return  view;
    }

    private void initRecycler() {
        List<HomeCategory> datas = new ArrayList<>(15);
        HomeCategory category = new HomeCategory("热门活动",R.mipmap.ic_launcher_round,R.mipmap.ic_launcher_round,R.mipmap.ic_launcher_round);
        datas.add(category);
        category = new HomeCategory("有利可图",R.mipmap.ic_launcher_round,R.mipmap.ic_launcher_round,R.mipmap.ic_launcher_round);
        datas.add(category);
        category = new HomeCategory("品牌街",R.mipmap.ic_launcher_round,R.mipmap.ic_launcher_round,R.mipmap.ic_launcher_round);
        datas.add(category);
        category = new HomeCategory("金融街 包赚翻",R.mipmap.ic_launcher_round,R.mipmap.ic_launcher_round,R.mipmap.ic_launcher_round);
        datas.add(category);
        category = new HomeCategory("超值购",R.mipmap.ic_launcher_round,R.mipmap.ic_launcher_round,R.mipmap.ic_launcher_round);
        datas.add(category);
        mAdatper = new HomeCatgoryAdapter(datas);
        mRecyclerView.setAdapter(mAdatper);
        mRecyclerView.addItemDecoration(new DividerItemDecortion());//设置分割线
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));//设置布局
    }

    private void initView(View view) {
        mToolBar=(Toolbar)view.findViewById(R.id.toolbar);
        mSliderLayout=(SliderLayout)view.findViewById(R.id.slider);
        mPagerIndicator=(PagerIndicator)view.findViewById(R.id.custom_indicator);
        mRecyclerView=(RecyclerView)view.findViewById(R.id.recyclerview);
    }
    private void initSlider() {
        TextSliderView textSliderView = new TextSliderView(this.getActivity());
        textSliderView.image("http://m.360buyimg.com/mobilecms/s300x98_jfs/t2416/102/20949846/13425/a3027ebc/55e6d1b9Ne6fd6d8f.jpg");
        textSliderView.description("新品推荐");//提示信息
        textSliderView.setOnSliderClickListener(new BaseSliderView.OnSliderClickListener() {//单击事件监听
            @Override
            public void onSliderClick(BaseSliderView baseSliderView) {

                Toast.makeText(HomeFragment.this.getActivity(),"新品推荐",Toast.LENGTH_LONG).show();

            }
        });
        TextSliderView textSliderView2 = new TextSliderView(this.getActivity());
        textSliderView2.image("http://m.360buyimg.com/mobilecms/s300x98_jfs/t1507/64/486775407/55927/d72d78cb/558d2fbaNb3c2f349.jpg");
        textSliderView2.description("时尚男装");
        textSliderView2.setOnSliderClickListener(new BaseSliderView.OnSliderClickListener() {
            @Override
            public void onSliderClick(BaseSliderView baseSliderView) {

                Toast.makeText(HomeFragment.this.getActivity(),"时尚男装",Toast.LENGTH_LONG).show();

            }
        });
        TextSliderView textSliderView3 = new TextSliderView(this.getActivity());
        textSliderView3.image("http://m.360buyimg.com/mobilecms/s300x98_jfs/t1363/77/1381395719/60705/ce91ad5c/55dd271aN49efd216.jpg");
        textSliderView3.description("家电秒杀");
        textSliderView3.setOnSliderClickListener(new BaseSliderView.OnSliderClickListener() {
            @Override
            public void onSliderClick(BaseSliderView baseSliderView) {

                Toast.makeText(HomeFragment.this.getActivity(),"家电秒杀",Toast.LENGTH_LONG).show();

            }
        });
        mSliderLayout.addSlider(textSliderView);
        mSliderLayout.addSlider(textSliderView2);
        mSliderLayout.addSlider(textSliderView3);
//        mSliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);//设置默认的广告轮播指示栏
        mSliderLayout.setCustomIndicator(mPagerIndicator);//设置指示器
        mSliderLayout.setCustomAnimation(new DescriptionAnimation());//设置动画
        mSliderLayout.setPresetTransformer(SliderLayout.Transformer.RotateUp);//设置转场效果
        mSliderLayout.setDuration(3000);//设置动画时间
        mSliderLayout.addOnPageChangeListener(new ViewPagerEx.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
                LogUtils.d("onPageScrolled");
            }

            @Override
            public void onPageSelected(int i) {
                LogUtils.d("onPageSelected");
            }
            @Override
            public void onPageScrollStateChanged(int i) {
                LogUtils.d("onPageScrollStateChanged");
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
