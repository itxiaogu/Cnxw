package com.itxiaogu.administrator.cnxw.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.itxiaogu.administrator.cnxw.R;
import com.itxiaogu.administrator.cnxw.utils.UIUtils;
import com.itxiaogu.administrator.cnxw.widget.FragmentTabHost;
import com.lidroid.xutils.view.annotation.ViewInject;


/**
 * 主页
 * Created by Ivan on 15/9/25.
 */
public class HomeFragment extends Fragment {
    private Toolbar mToolBar;//标题栏
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_home,container,false);
        initToolbar(view);
        return  view;
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    /**
     * 初始化菜单栏
     * @param view
     */
    private void initToolbar(View view){
        mToolBar=(Toolbar)view.findViewById(R.id.toolbar);
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
}
