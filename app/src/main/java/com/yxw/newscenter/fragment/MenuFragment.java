package com.yxw.newscenter.fragment;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.yxw.newscenter.HomeActivity;
import com.yxw.newscenter.R;
import com.yxw.newscenter.bean.NewsData;
import com.yxw.newscenter.pager.BasePager;
import com.yxw.newscenter.pager.NewsCenterPager;

import java.util.ArrayList;

public class MenuFragment extends BaseFragment {
    private ListView lvMenu;
    private ArrayList<NewsData.NewsMenuData> list;
    private MenuAdapter adapter;
    private int mPosition = 0;

    /**
     * 重写初始化布局的方法
     *
     * @return
     */
    @Override
    public View initViews() {
        View v = View.inflate(mFragmentActivity, R.layout.fragment_menu, null);
        lvMenu = v.findViewById(R.id.lv_menu);
        return v;
    }

    @Override
    public void initData() {
        super.initData();
        //侧边栏的点击事件
        lvMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mPosition = position;
                adapter.notifyDataSetChanged();
                setCurrentMenuPager();
            }
        });
    }

    private void setCurrentMenuPager() {
        HomeActivity ha = (HomeActivity) mFragmentActivity;
        HomeFragment homeFragment = ha.getHomeFragment();
        NewsCenterPager newsCenterPager = (NewsCenterPager) homeFragment.getPager(1);
        newsCenterPager.setMenuPager(mPosition);
        //侧边栏切换状态
        SlidingMenu slidingMenu = ha.getSlidingMenu();
        slidingMenu.toggle();
    }

    public void setMenuData(NewsData newsData,int code) {
        if (code==1){
            list = newsData.data;
            mPosition = 0;
            adapter = new MenuAdapter();
            lvMenu.setAdapter(adapter);
        }else{
            lvMenu.setAdapter(null);
        }
    }

    private class MenuAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View v = View.inflate(mFragmentActivity, R.layout.item_menulist, null);
            TextView tv = v.findViewById(R.id.tv_menulist);
            tv.setText(list.get(position).title);
            if (mPosition == position) {
                tv.setEnabled(true);
            } else {
                tv.setEnabled(false);
            }
            return v;
        }
    }
}
