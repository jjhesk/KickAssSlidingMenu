package com.hypebeast.demoslidemenu;

import android.app.Fragment;
import android.view.MenuItem;

import com.hkm.slidingmenulib.gestured.SlidingMenu;
import com.hkm.slidingmenulib.layoutdesigns.app.SlidingAppCompactActivity;
import com.hypebeast.demoslidemenu.content.expandablemenu.MenuFragment;
import com.hypebeast.demoslidemenu.helpr.structurebind;
import com.hypebeast.demoslidemenu.content.mainpageDemo;


public class MainActivityDemo extends SlidingAppCompactActivity<Fragment> {


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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        final int t = item.getItemId();
        if (t != android.R.id.home) {
            structurebind.startfromSelectionMenu(t, this, null);
            return super.onOptionsItemSelected(item);
        } else {
            toggle();
            return true;
        }
    }
}
