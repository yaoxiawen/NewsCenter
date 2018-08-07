package com.yxw.newscenter;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Window;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;
import com.yxw.newscenter.fragment.HomeFragment;
import com.yxw.newscenter.fragment.MenuFragment;

public class HomeActivity extends SlidingFragmentActivity {

    private static final String MENUFRAGMENT = "menufragment";
    private static final String HOMEFRAGMENT = "homefragment";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_home);
        //侧边栏布局
        setBehindContentView(R.layout.left_menu);
        //侧边栏对象
        SlidingMenu slidingMenu = getSlidingMenu();
        //设置全屏触摸
        slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        //设置预留屏幕的宽度
        slidingMenu.setBehindOffset(600);
        //初始化Fragment
        initFragment();
    }

    private void initFragment() {
        //获取管理器
        FragmentManager fm = getFragmentManager();
        //开启事务
        FragmentTransaction ft = fm.beginTransaction();
        //填充Fragment
        ft.replace(R.id.fl_leftmenu, new MenuFragment(), MENUFRAGMENT);
        ft.replace(R.id.fl_homeactivity, new HomeFragment(), HOMEFRAGMENT);
        //提交事务
        ft.commit();
    }

    //获取侧边栏fragment对象
    public MenuFragment getMenuFragment() {
        FragmentManager fm = getFragmentManager();
        MenuFragment menufragment = (MenuFragment) fm.findFragmentByTag(MENUFRAGMENT);
        return menufragment;
    }

    //获取HomeFragment对象
    public HomeFragment getHomeFragment() {
        FragmentManager fm = getFragmentManager();
        HomeFragment homeFragment = (HomeFragment) fm.findFragmentByTag(HOMEFRAGMENT);
        return homeFragment;
    }
}
