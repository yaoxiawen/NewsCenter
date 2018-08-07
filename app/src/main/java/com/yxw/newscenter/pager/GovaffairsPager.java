package com.yxw.newscenter.pager;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.TextView;

import com.yxw.newscenter.HomeActivity;
import com.yxw.newscenter.fragment.MenuFragment;

public class GovaffairsPager extends BasePager {

    public GovaffairsPager(Activity mPagerActivity) {
        super(mPagerActivity);
    }

    /**
     * 子类重写初始化数据
     */
    @Override
    public void initData() {
        super.initData();
        setTouchMode(true);
        tvTitle.setText("政务");
        TextView text = new TextView(mPagerActivity);
        text.setText("政务");
        text.setTextColor(Color.RED);
        text.setTextSize(25);
        text.setGravity(Gravity.CENTER);
        flPager.addView(text);
        //初始化侧边栏数据
        initMenu();
    }
    private void initMenu() {
        HomeActivity ha = (HomeActivity) mPagerActivity;
        MenuFragment menuFragment = ha.getMenuFragment();
        menuFragment.setMenuData(null,0);
    }
}
