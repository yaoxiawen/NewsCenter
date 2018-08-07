package com.yxw.newscenter.vpmenupager;

import android.app.Activity;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.viewpagerindicator.CirclePageIndicator;
import com.yxw.newscenter.R;
import com.yxw.newscenter.bean.NewsData;

import java.util.ArrayList;

public class VPMenuPager extends BaseVPMenuPager {
    private ViewPager vpVPMenuPager;
    private ListView lvVPMenuPager;
    private TextView tvVPMenuPager;
    private View headerView;
    private CirclePageIndicator indicatorVPMenuPager;
    private ArrayList<TextView> vplist;
    private ArrayList lvlist;
    private Handler handler;


    public VPMenuPager(Activity mVPMenuPagerActivity, NewsData.NewsTabData newsTabData) {
        super(mVPMenuPagerActivity, newsTabData);
    }

    @Override
    public View initView() {
        View view = View.inflate(mVPMenuPagerActivity, R.layout.vpmenupager, null);
        headerView = View.inflate(mVPMenuPagerActivity, R.layout.headerview_vpmenupager, null);
        vpVPMenuPager = headerView.findViewById(R.id.vp_vpmenupager);
        lvVPMenuPager = view.findViewById(R.id.lv_vpmenupager);
        tvVPMenuPager = headerView.findViewById(R.id.tv_vpmenupager);
        indicatorVPMenuPager = headerView.findViewById(R.id.indicator_vpmenupager);
        lvVPMenuPager.addHeaderView(headerView);
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        vplist = new ArrayList();
        lvlist = new ArrayList();
        for (int i = 0; i < 5; i++) {
            TextView text = new TextView(mVPMenuPagerActivity);
            text.setText(newsTabData.title + i);
            text.setTextColor(Color.RED);
            text.setTextSize(25);
            text.setGravity(Gravity.CENTER);
            vplist.add(text);
        }
        for (int i = 0; i < 20; i++) {
            TextView text = new TextView(mVPMenuPagerActivity);
            text.setText(newsTabData.title + i);
            text.setTextColor(Color.BLACK);
            text.setTextSize(25);
            text.setPadding(20, 20, 20, 20);
            //text.setGravity(Gravity.CENTER);
            lvlist.add(text);
        }
        vpVPMenuPager.setAdapter(new myvpAdapter());
        lvVPMenuPager.setAdapter(new mylvAdapter());
        //第一个页面主动设置一下
        tvVPMenuPager.setText(newsTabData.title + 0);
        //页面改变监听，改变标题的内容
        vpVPMenuPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                tvVPMenuPager.setText(newsTabData.title + position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        indicatorVPMenuPager.setViewPager(vpVPMenuPager);
        //indicatorVPMenuPager.setSnap(true);//支持快照显示，移动时是否要有一跳一跳的动画
        //listview的点击事件，
        // 由于添加了headerview，点击的条目的position是加上headerview之后的position了
        lvVPMenuPager.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //局部界面刷新
                TextView textView = (TextView) view;
                textView.setTextColor(Color.GRAY);
            }
        });
        //自动轮播
        if (handler == null) {
            handler = new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    super.handleMessage(msg);
                    vpVPMenuPager.setCurrentItem((vpVPMenuPager.getCurrentItem() + 1) % vpVPMenuPager.getAdapter().getCount());
                    handler.sendEmptyMessageDelayed(0, 3000);
                }
            };
        }
        handler.sendEmptyMessageDelayed(0, 3000);
        //触摸时取消自动轮播
//        vpVPMenuPager.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                switch (event.getAction()) {
//                    case MotionEvent.ACTION_DOWN:
//                        //删除handler中的所欲消息
//                        handler.removeCallbacksAndMessages(null);
//                        break;
//                    case MotionEvent.ACTION_UP:
//                        handler.sendEmptyMessageDelayed(0, 3000);
//                        break;
//                    case MotionEvent.ACTION_CANCEL:
//                        handler.sendEmptyMessageDelayed(0, 3000);
//                        break;
//                }
//                return true;
//            }
//        });
    }

    private class myvpAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return vplist.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            //return super.instantiateItem(container, position);
            View v = vplist.get(position);
            container.addView(v);
            return v;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            //super.destroyItem(container, position, object);
            container.removeView((View) object);
        }
    }

    private class mylvAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return lvlist.size();
        }

        @Override
        public Object getItem(int position) {
            return lvlist.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            return (View) lvlist.get(position);
        }
    }
}
