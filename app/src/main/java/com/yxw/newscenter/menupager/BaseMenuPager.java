package com.yxw.newscenter.menupager;


import android.app.Activity;
import android.view.View;

import com.yxw.newscenter.bean.NewsData;

public abstract class BaseMenuPager {
    public Activity mMenuPagerActivity;
    public View rootView;
    public NewsData.NewsMenuData menuData;

    public BaseMenuPager(Activity mMenuPagerActivity,NewsData.NewsMenuData menuData) {
        this.mMenuPagerActivity = mMenuPagerActivity;
        this.menuData = menuData;
        rootView = initView();
    }

    public abstract View initView();

    public void initData() {
    }
}
