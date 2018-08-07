package com.yxw.newscenter.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class VPMenuPagerViewPager extends ViewPager {
    private int startX;
    private int startY;
    public VPMenuPagerViewPager(Context context) {
        super(context);
    }

    public VPMenuPagerViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 重写事件分发方法，将情况分类：
     * 当第一个页面右划时，父控件拦截，->
     * 当最后一个页面左划时，父控件拦截，<-
     * 当上下划时，父控件拦截
     * 其余情况父控件都不拦截
     * @param ev
     * @return
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                //父控件不拦截，才能获取到触摸事件
                //接下去把父控件拦截的情况写出来就可以了
                getParent().requestDisallowInterceptTouchEvent(true);//请求父控件不拦截事件
                startX = (int) ev.getRawX();
                startY = (int) ev.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                int endX = (int) ev.getRawX();
                int endY = (int) ev.getRawY();
                if (Math.abs(endX-startX)>Math.abs(endY-startY)){//左右划
                    if (endX>startX){//右划
                        if (getCurrentItem()==0){//第一个页面
                            getParent().requestDisallowInterceptTouchEvent(false);//父控件拦截事件
                        }
                    }else{//左划
                        if (getCurrentItem()==getAdapter().getCount()-1){
                            getParent().requestDisallowInterceptTouchEvent(false);//父控件拦截事件
                        }
                    }
                }else{//上下划
                    getParent().requestDisallowInterceptTouchEvent(false);//父控件拦截事件
                }
                break;
        }
        return super.dispatchTouchEvent(ev);
    }
}
