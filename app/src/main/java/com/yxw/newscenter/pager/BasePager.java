package com.yxw.newscenter.pager;

import android.app.Activity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.yxw.newscenter.HomeActivity;
import com.yxw.newscenter.R;

/**
 * ViewPager内容的基类
 */
public class BasePager {
    public Activity mPagerActivity;
    public View rootView;
    public ImageView ivMenu;
    public TextView tvTitle;
    public FrameLayout flPager;

    public BasePager(Activity mPagerActivity) {
        this.mPagerActivity = mPagerActivity;
        initView();
        //防止因预加载机制而导致的一些页面之间逻辑的覆盖，
        //将加载数据的方法放到页面被选中之后再调用
        //initData();
    }

    /**
     * 初始化布局
     */
    public void initView() {
        rootView = View.inflate(mPagerActivity, R.layout.pager_base, null);
        ivMenu = rootView.findViewById(R.id.iv_menu);
        tvTitle = rootView.findViewById(R.id.tv_title);
        flPager = rootView.findViewById(R.id.fl_pager);
        ivMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HomeActivity ha = (HomeActivity) mPagerActivity;
                SlidingMenu slidingMenu = ha.getSlidingMenu();
                //侧边栏切换状态
                slidingMenu.toggle();
            }
        });
    }

    /**
     * 初始化数据，供子类重写
     */
    public void initData() {
    }

    /**
     * 是否显示侧边栏
     * @param mode
     */
    public void setTouchMode(boolean mode) {
        HomeActivity ha = (HomeActivity) mPagerActivity;
        SlidingMenu slidingMenu = ha.getSlidingMenu();
        if (mode) {
            ivMenu.setVisibility(View.VISIBLE);
            slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        } else {
            ivMenu.setVisibility(View.INVISIBLE);
            slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
        }
    }
}
