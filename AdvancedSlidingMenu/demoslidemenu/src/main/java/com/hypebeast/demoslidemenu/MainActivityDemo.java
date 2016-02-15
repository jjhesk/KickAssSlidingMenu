package com.hypebeast.demoslidemenu;

import android.app.Fragment;
import android.view.MenuItem;

import com.hkm.slidingmenulib.gestured.SlidingMenu;
import com.hkm.slidingmenulib.layoutdesigns.app.SlidingAppCompactActivity;
import com.hypebeast.demoslidemenu.helpr.fastb;
import com.hypebeast.demoslidemenu.content.mainpageDemo;
import com.hypebeast.demoslidemenu.content.menupageDemo;


public class MainActivityDemo extends SlidingAppCompactActivity<Fragment> {


    @Override
    protected int getDefaultMainActivityLayoutId() {
        return SlidingAppCompactActivity.BODY_LAYOUT.actionbar.getResID();
    }

    @Override
    protected menupageDemo getFirstMenuFragment() {
        return new menupageDemo();
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        final int t = item.getItemId();
        if (t != android.R.id.home) {
            fastb.startfromSelectionMenu(t, this, null);
            return super.onOptionsItemSelected(item);
        } else {
            toggle();
            return true;
        }
    }
}
