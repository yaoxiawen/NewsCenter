package com.yxw.newscenter;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.yxw.newscenter.utils.SharedPreferencesUtils;

import java.util.ArrayList;
import java.util.List;

public class GuideActivity extends Activity {
    private ViewPager vpGuide;
    private Button btGuide;
    private LinearLayout llGuide;
    private ImageView dotGuide;
    private List<ImageView> images;
    private int width;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_guide);
        initView();
        initImage();
        initData();
        listener();
    }

    private void initView() {
        vpGuide = findViewById(R.id.vp_guide);
        btGuide = findViewById(R.id.bt_guide);
        llGuide = findViewById(R.id.ll_guide);
        dotGuide = findViewById(R.id.dot_guide);
        images = new ArrayList<>();
    }

    private void initImage() {
        int[] imageIds = new int[]{R.drawable.guide_1, R.drawable.guide_2, R.drawable.guide_3};
        for (int i = 0; i < imageIds.length; i++) {
            ImageView image = new ImageView(this);
            image.setBackgroundResource(imageIds[i]);
            images.add(image);
        }
    }

    private void initData() {
        vpGuide.setAdapter(new GuideAdapter());
        //动态增加小圆点
        for (int i = 0; i < images.size(); i++) {
            View dot = new View(this);
            //单位是像素
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(30, 30);
            if (i > 0) {
                params.leftMargin = 15;
            }
            dot.setLayoutParams(params);
            dot.setBackgroundResource(R.drawable.dot_gray);
            llGuide.addView(dot);
        }
        //获取视图树，设置监听
        llGuide.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            /**
             * onLayout方法执行完之后会调用此方法
             */
            @Override
            public void onGlobalLayout() {
                //只要一次即可
                llGuide.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                //小圆点之间距离
                width = llGuide.getChildAt(1).getLeft() - llGuide.getChildAt(0).getLeft();
            }
        });
    }

    private void listener() {
        vpGuide.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            /**
             * 滑动事件
             * @param position
             * @param positionOffset
             * @param positionOffsetPixels
             */
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                int dis = (int) (position * width + width * positionOffset);
                //获取布局参数
                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) dotGuide.getLayoutParams();
                //设置左边距
                params.leftMargin = dis;
                dotGuide.setLayoutParams(params);
            }

            /**
             * 某个页面被选中
             * @param position 选中的页面脚标
             */
            @Override
            public void onPageSelected(int position) {
                if (position == images.size() - 1) {
                    btGuide.setVisibility(View.VISIBLE);
                } else {
                    btGuide.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        btGuide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferencesUtils.setBoolean(GuideActivity.this,
                        "guideshow",true);
                startActivity(new Intent(GuideActivity.this, HomeActivity.class));
                finish();
            }
        });
    }

    private class GuideAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return images.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            //super.destroyItem(container, position, object);
            container.removeView((View) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            //return super.instantiateItem(container, position);
            container.addView(images.get(position));
            return images.get(position);
        }
    }
}
