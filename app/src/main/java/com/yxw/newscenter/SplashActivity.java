package com.yxw.newscenter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.RelativeLayout;

import com.yxw.newscenter.utils.SharedPreferencesUtils;

public class SplashActivity extends Activity {
    private RelativeLayout splash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        splash = findViewById(R.id.splash);
        startAnim();
    }

    /**
     * 开启动画
     */
    private void startAnim() {
        //旋转动画
        RotateAnimation ra = new RotateAnimation(0, 360,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        ra.setDuration(1000);
        ra.setFillAfter(true);
        //缩放动画
        ScaleAnimation sa = new ScaleAnimation(0, 1, 0, 1,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        sa.setDuration(1000);
        sa.setFillAfter(true);
        //透明动画
        AlphaAnimation aa = new AlphaAnimation(0, 1);
        aa.setDuration(2000);
        aa.setFillAfter(true);
        AnimationSet set = new AnimationSet(false);
        set.addAnimation(ra);
        set.addAnimation(sa);
        set.addAnimation(aa);
        splash.startAnimation(set);
        //动画播放结束，跳转并结束当前页
        set.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Boolean guideShow = SharedPreferencesUtils.getBoolean(SplashActivity.this,
                        "guideshow", false);
                if (!guideShow) {
                    startActivity(new Intent(SplashActivity.this, GuideActivity.class));
                } else {
                    startActivity(new Intent(SplashActivity.this, HomeActivity.class));
                }
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
    }
}
