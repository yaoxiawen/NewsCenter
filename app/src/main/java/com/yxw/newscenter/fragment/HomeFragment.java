package com.yxw.newscenter.fragment;

import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.yxw.newscenter.R;
import com.yxw.newscenter.pager.BasePager;
import com.yxw.newscenter.pager.GovaffairsPager;
import com.yxw.newscenter.pager.HomePager;
import com.yxw.newscenter.pager.NewsCenterPager;
import com.yxw.newscenter.pager.SettingPager;
import com.yxw.newscenter.pager.SmartServicePager;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends BaseFragment {
    private RadioGroup rgHome;
    private ViewPager vpHome;
    private List<BasePager> list;
    private HomeAdapter homeAdapter;

    /**
     * 重写初始化布局的方法
     *
     * @return
     */
    @Override
    public View initViews() {
        View v = View.inflate(mFragmentActivity, R.layout.fragment_home, null);
        rgHome = v.findViewById(R.id.rg_home);
        vpHome = v.findViewById(R.id.vp_home);
        list = new ArrayList<>();
        return v;
    }

    /**
     * 重写初始化数据的方法
     */
    @Override
    public void initData() {
        //RadioGroup选中第一个RadioButton
        rgHome.check(R.id.rb1_home);
        //ViewPager的内容初始化
        list.add(new HomePager(mFragmentActivity));
        list.add(new NewsCenterPager(mFragmentActivity));
        list.add(new SmartServicePager(mFragmentActivity));
        list.add(new GovaffairsPager(mFragmentActivity));
        list.add(new SettingPager(mFragmentActivity));
        homeAdapter = new HomeAdapter();
        vpHome.setAdapter(homeAdapter);
        //监听RadioGroup的选择事件
        rgHome.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                //页面切换，false表示去掉切换页面的动画
                switch (checkedId) {
                    case R.id.rb1_home:
                        vpHome.setCurrentItem(0, false);
                        break;
                    case R.id.rb2_home:
                        vpHome.setCurrentItem(1, false);
                        break;
                    case R.id.rb3_home:
                        vpHome.setCurrentItem(2, false);
                        break;
                    case R.id.rb4_home:
                        vpHome.setCurrentItem(3, false);
                        break;
                    case R.id.rb5_home:
                        vpHome.setCurrentItem(4, false);
                        break;
                }
            }
        });
        //ViewPager的页面改变事件
        vpHome.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                //页面被选中之后，再加载数据
                //防止因预加载机制而导致的一些页面之间逻辑的覆盖
                list.get(position).initData();
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        //只有页面发生变化时才会启动监听，因此第一个页面的初始化数据方法手动调用一下
        list.get(0).initData();
    }

    public BasePager getPager(int position) {
        return list.get(position);
    }

    private class HomeAdapter extends PagerAdapter {
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
    }
}
