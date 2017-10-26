package com.itxiaogu.administrator.cnxw;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;

import com.itxiaogu.administrator.cnxw.bean.Tab;
import com.itxiaogu.administrator.cnxw.fragment.CartFragment;
import com.itxiaogu.administrator.cnxw.fragment.CategoryFragment;
import com.itxiaogu.administrator.cnxw.fragment.HomeFragment;
import com.itxiaogu.administrator.cnxw.fragment.HotFragment;
import com.itxiaogu.administrator.cnxw.fragment.MineFragment;
import com.itxiaogu.administrator.cnxw.utils.UIUtils;
import com.itxiaogu.administrator.cnxw.widget.FragmentTabHost;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    @ViewInject(android.R.id.tabhost)
    private FragmentTabHost mTabhost;//底部导航栏
    private List<Tab> mTabs = new ArrayList<>(5);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewUtils.inject(this);
        initTab();//测试底部导航栏
    }
    private void initTab() {
        Tab tab_home = new Tab(HomeFragment.class,R.string.home,R.drawable.selector_icon_home);
        Tab tab_hot = new Tab(HotFragment.class,R.string.hot,R.drawable.selector_icon_hot);
        Tab tab_category = new Tab(CategoryFragment.class,R.string.catagory,R.drawable.selector_icon_category);
        Tab tab_cart = new Tab(CartFragment.class,R.string.cart,R.drawable.selector_icon_cart);
        Tab tab_mine = new Tab(MineFragment.class,R.string.mine,R.drawable.selector_icon_mine);

        mTabs.add(tab_home);
        mTabs.add(tab_hot);
        mTabs.add(tab_category);
        mTabs.add(tab_cart);
        mTabs.add(tab_mine);

        mTabhost.setup(this,getSupportFragmentManager(),R.id.realtabcontent);

        for (Tab tab : mTabs){
            TabHost.TabSpec tabSpec = mTabhost.newTabSpec(getString(tab.getTitle()));
            tabSpec.setIndicator(buildIndicator(tab));
            mTabhost.addTab(tabSpec,tab.getFragment(),null);
        }
        mTabhost.getTabWidget().setShowDividers(LinearLayout.SHOW_DIVIDER_NONE);//去掉分隔线
        mTabhost.setCurrentTab(0);//默认选择第一个
    }
    private View buildIndicator(Tab tab){
        View view = UIUtils.inflate(R.layout.tab_indicator);
        ImageView img = (ImageView) view.findViewById(R.id.icon_tab);
        TextView text = (TextView) view.findViewById(R.id.txt_indicator);
        img.setBackgroundResource(tab.getIcon());
        text.setText(tab.getTitle());
        return  view;
    }
}
