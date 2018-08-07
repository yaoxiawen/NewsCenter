package com.yxw.newscenter.pager;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.TextView;

public class SettingPager extends BasePager {

    public SettingPager(Activity mPagerActivity) {
        super(mPagerActivity);
    }

    /**
     * 子类重写初始化数据
     */
    @Override
    public void initData() {
        super.initData();
        setTouchMode(false);
        tvTitle.setText("设置");
        TextView text = new TextView(mPagerActivity);
        text.setText("设置");
        text.setTextColor(Color.RED);
        text.setTextSize(25);
        text.setGravity(Gravity.CENTER);
        flPager.addView(text);
    }
}
