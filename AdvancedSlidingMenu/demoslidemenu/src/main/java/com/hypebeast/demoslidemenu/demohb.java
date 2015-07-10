package com.hypebeast.demoslidemenu;

import android.app.Fragment;

import com.hkm.slidingmenulib.gestured.SlidingMenu;
import com.hkm.slidingmenulib.layoutdesigns.app.SlidingAppCompactActivity;
import com.hypebeast.demoslidemenu.pages.mainpageDemo;
import com.hypebeast.demoslidemenu.pages.menupageDemo;
import com.hypebeast.demoslidemenu.pages.treemenu;

/**
 * Created by hesk on 10/7/15.
 */
public class demohb extends SlidingAppCompactActivity<Fragment> {

    @Override
    protected int getDefaultMainActivityLayoutId() {
        return SlidingAppCompactActivity.BODY_LAYOUT.actionbar.getResID();
    }

    @Override
    protected treemenu getFirstMenuFragment() {
        return new treemenu();
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
