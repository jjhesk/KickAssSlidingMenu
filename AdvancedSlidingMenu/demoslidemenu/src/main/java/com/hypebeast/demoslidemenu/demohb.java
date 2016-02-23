package com.hypebeast.demoslidemenu;

import android.app.Fragment;

import com.hkm.slidingmenulib.gestured.SlidingMenu;
import com.hkm.slidingmenulib.layoutdesigns.app.SlidingAppCompactActivity;
import com.hypebeast.demoslidemenu.content.expandablemenu.MenuFragment;
import com.hypebeast.demoslidemenu.content.mainpageDemo;

/**
 * Created by hesk on 10/7/15.
 */
public class demohb extends SlidingAppCompactActivity<Fragment> {


    private static String[] sampledatagroup1 = {
            "peter", "http://google",
            "billy", "http://google",
            "lisa", "http://google",
            "visa", "http://google"
    };
    private static String[] sampledatagroup2 = {
            "mother", "http://google",
            "father", "http://google",
            "son", "http://google",
            "holy spirit", "http://google",
            "god the son", "http://google"
    };
    private static String[] sampledatagroup3 = {
            "SONY", "http://google",
            "LG", "http://google",
            "SAMSUNG", "http://google",
            "XIAOMI", "http://google",
            "HTC", "http://google"
    };



    @Override
    protected int getDefaultMainActivityLayoutId() {
        return SlidingAppCompactActivity.BODY_LAYOUT.actionbar.getResID();
    }

    @Override
    protected MenuFragment getFirstMenuFragment() {
        return new MenuFragment();
    }


    @Override
    protected int getRmenu() {
        return R.menu.menu_main;
    }

    @Override
    protected mainpageDemo getInitFragment() {
        return new mainpageDemo();
    }

    @Override
    protected void customizeSlideMenuEdge(SlidingMenu sm) {
        sm.setFadeDegree(0.35f);
        sm.setMode(SlidingMenu.LEFT);
        sm.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
        sm.setBehindScrollScale(0.5f);
        sm.setFadeDegree(0.34f);
        sm.setBehindWidth(840);
        sm.requestLayout();
        sm.invalidate();
    }

}
