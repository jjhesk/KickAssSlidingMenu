package com.hypebeast.demoslidemenu;

import android.app.Fragment;

import com.hkm.slidingmenulib.gestured.SlidingMenu;
import com.hkm.slidingmenulib.layoutdesigns.app.SlidingAppCompactActivity;
import com.hypebeast.demoslidemenu.pages.mainpageDemo;
import com.hypebeast.demoslidemenu.pages.treemenu;
import com.hypebeast.demoslidemenu.pages.treesystem;

/**
 * Created by hesk on 10/7/15.
 */
public class demosys extends SlidingAppCompactActivity<Fragment> {

    @Override
    protected int getDefaultMainActivityLayoutId() {
        return SlidingAppCompactActivity.BODY_LAYOUT.actionbar.getResID();
    }

    @Override
    protected treesystem getFirstMenuFragment() {
        return new treesystem();
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
