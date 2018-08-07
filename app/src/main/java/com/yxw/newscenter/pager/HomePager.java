package com.yxw.newscenter.pager;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.TextView;

public class HomePager extends BasePager {

    public HomePager(Activity mPagerActivity) {
        super(mPagerActivity);
    }

    /**
     * 子类重写初始化数据
     */
    @Override
    public void initData() {
        super.initData();
        setTouchMode(false);
        tvTitle.setText("首页");
        TextView text = new TextView(mPagerActivity);
        text.setText("首页");
        text.setTextColor(Color.RED);
        text.setTextSize(25);
        text.setGravity(Gravity.CENTER);
        flPager.addView(text);
    }
}
