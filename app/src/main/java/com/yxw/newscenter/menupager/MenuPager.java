package com.yxw.newscenter.menupager;

import android.app.Activity;
import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.viewpagerindicator.TabPageIndicator;
import com.yxw.newscenter.HomeActivity;
import com.yxw.newscenter.R;
import com.yxw.newscenter.bean.NewsData;
import com.yxw.newscenter.vpmenupager.BaseVPMenuPager;
import com.yxw.newscenter.vpmenupager.VPMenuPager;

import java.util.ArrayList;

public class MenuPager extends BaseMenuPager {
    private ViewPager vpMenuPager;
    private ArrayList<BaseVPMenuPager> list;
    private TabPageIndicator indicator;
    private ImageButton ibMenuPager;

    public MenuPager(Activity mMenuPagerActivity, NewsData.NewsMenuData menuData) {
        super(mMenuPagerActivity,menuData);
    }

    @Override
    public View initView() {
        if (menuData.children.size() == 0) {
            TextView text = new TextView(mMenuPagerActivity);
            text.setText(menuData.title);
            text.setTextColor(Color.RED);
            text.setTextSize(25);
            text.setGravity(Gravity.CENTER);
            return text;
        } else {
            View view = View.inflate(mMenuPagerActivity, R.layout.menupager, null);
            vpMenuPager = view.findViewById(R.id.vp_menupager);
            indicator = view.findViewById(R.id.indicator_menupager);
            ibMenuPager = view.findViewById(R.id.ib_menupager);
            return view;
        }

    }

    @Override
    public void initData() {
        super.initData();
        if (menuData.children.size() == 0) {
            return;
        }
        list = new ArrayList();
        for (int i = 0;i<menuData.children.size();i++){
            list.add(new VPMenuPager(mMenuPagerActivity,menuData.children.get(i)));
        }
        vpMenuPager.setAdapter(new MenuPagerAdapter());
        indicator.setViewPager(vpMenuPager);
        //第一个页面的初始化数据方法手动调用一下
        list.get(0).initData();
        vpMenuPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                HomeActivity ha = (HomeActivity) mMenuPagerActivity;
                SlidingMenu slidingMenu = ha.getSlidingMenu();
                if (position==0){
                    slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
                }else {
                    slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
                }
                list.get(position).initData();
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        //按按钮然后跳到下一页
        ibMenuPager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vpMenuPager.setCurrentItem(vpMenuPager.getCurrentItem()+1);
            }
        });
    }

    class MenuPagerAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            //return super.instantiateItem(container, position);
            View view = list.get(position).rootView;
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            //super.destroyItem(container, position, object);
            container.removeView((View) object);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return menuData.children.get(position).title;
        }
    }
}
