package com.yxw.newscenter.pager;

import android.app.Activity;

import com.yxw.newscenter.HomeActivity;
import com.yxw.newscenter.bean.DataUtils;
import com.yxw.newscenter.bean.NewsData;
import com.yxw.newscenter.fragment.MenuFragment;
import com.yxw.newscenter.menupager.BaseMenuPager;
import com.yxw.newscenter.menupager.MenuPager;

import java.util.ArrayList;

public class NewsCenterPager extends BasePager {
    private ArrayList<BaseMenuPager> list;
    private NewsData newsData;

    public NewsCenterPager(Activity mPagerActivity) {
        super(mPagerActivity);
    }

    /**
     * 子类重写初始化数据
     */
    @Override
    public void initData() {
        super.initData();
        setTouchMode(true);
        //初始化侧边栏数据
        initMenu();
        //第一页手动调用
        setMenuPager(0);
    }

    private void initMenu() {
        newsData = DataUtils.dataObject();
        HomeActivity ha = (HomeActivity) mPagerActivity;
        MenuFragment menuFragment = ha.getMenuFragment();
        menuFragment.setMenuData(newsData, 1);
        list = new ArrayList<>();
        for (int i = 0; i < newsData.data.size(); i++) {
            list.add(new MenuPager(mPagerActivity, newsData.data.get(i)));
        }
    }

    public void setMenuPager(int position) {
        //标题的改动
        tvTitle.setText(newsData.data.get(position).title);
        //初始化当前页数据
        list.get(position).initData();
        //FrameLayout每次加载前先清除之前的布局
        flPager.removeAllViews();
        flPager.addView(list.get(position).rootView);
    }
}
