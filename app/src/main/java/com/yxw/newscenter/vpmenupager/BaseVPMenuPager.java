package com.yxw.newscenter.vpmenupager;

import android.app.Activity;
import android.view.View;

import com.yxw.newscenter.bean.NewsData;

public abstract class BaseVPMenuPager {
    public Activity mVPMenuPagerActivity;
    public View rootView;
    public NewsData.NewsTabData newsTabData;

    public BaseVPMenuPager(Activity mVPMenuPagerActivity,NewsData.NewsTabData newsTabData) {
        this.mVPMenuPagerActivity = mVPMenuPagerActivity;
        this.newsTabData = newsTabData;
        rootView = initView();
    }

    public abstract View initView();

    public void initData() {
    }
}
