package com.itxiaogu.administrator.cnxw.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.widget.TintTypedArray;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.itxiaogu.administrator.cnxw.R;
import com.itxiaogu.administrator.cnxw.utils.UIUtils;

/**
 * Created by Administrator on 2017/10/26.
 */

public class CnToolbar extends Toolbar{

    private View mView;
    private TextView mTextTitle;
    private EditText mSearchView;
    private ImageButton mRightImageButton;

    public CnToolbar(Context context) {
        this(context,null);
    }

    public CnToolbar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CnToolbar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
        if(attrs !=null) {
            final TintTypedArray a = TintTypedArray.obtainStyledAttributes(getContext(), attrs,
                    R.styleable.CnToolbar, defStyleAttr, 0);

            final Drawable rightIcon = a.getDrawable(R.styleable.CnToolbar_rightButtonIcon);
            if (rightIcon != null) {
                setRightButtonIcon(rightIcon);
            }

            boolean isShowSearchView = a.getBoolean(R.styleable.CnToolbar_isShowSearchView,false);
            if(isShowSearchView){
                showSearchView();
            }
            a.recycle();
        }
    }
    private void initView() {
        if (mView==null){
            mView = UIUtils.inflate(R.layout.toolbar);
            mTextTitle = (TextView) mView.findViewById(R.id.toolbar_title);
            mSearchView = (EditText) mView.findViewById(R.id.toolbar_searchview);
            mRightImageButton = (ImageButton) mView.findViewById(R.id.toolbar_rightButton);
            LayoutParams lp = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.CENTER_HORIZONTAL);
            addView(mView, lp);
        }
    }

    /**
     * 显示右边按钮
     */
    public void showRightImageButton(){
        if (mRightImageButton!=null)
            mRightImageButton.setVisibility(View.VISIBLE);
    }

    /**
     * 显示搜索输入框
     */
    public void showSearchView(){
        if (mSearchView!=null)
            mSearchView.setVisibility(View.VISIBLE);
        else
            mTextTitle.setVisibility(View.GONE);
    }

    /**
     * 显示标题
     */
    public void showTitleView(){
        if (mTextTitle!=null)
            mTextTitle.setVisibility(View.VISIBLE);
        else
            mSearchView.setVisibility(View.GONE);
    }

    /**
     * 设置标题内容String资源id
     * @param resId
     */
    @Override
    public void setTitle(@StringRes int resId) {
        this.setTitle(UIUtils.getString(resId));
    }

    /**
     * 设置标题内容String
     * @param title
     */
    @Override
    public void setTitle(CharSequence title) {
        initView();
        mTextTitle.setText(title);
        showTitleView();
    }

    /**
     * 给右边按钮设置背景图片
     * @param icon
     */
    public void  setRightButtonIcon(Drawable icon){
        if(mRightImageButton !=null){
            mRightImageButton.setImageDrawable(icon);
            mRightImageButton.setVisibility(VISIBLE);
        }

    }

    /**
     * 给右边按钮设置监听
     * @param li
     */
    public  void setRightButtonOnClickListener(OnClickListener li){

        mRightImageButton.setOnClickListener(li);
    }
}
