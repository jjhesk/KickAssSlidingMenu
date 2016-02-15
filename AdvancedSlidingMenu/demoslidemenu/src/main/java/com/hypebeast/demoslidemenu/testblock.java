package com.hypebeast.demoslidemenu;

import android.app.Fragment;
import android.view.MenuItem;

import com.hkm.slidingmenulib.gestured.SlidingMenu;
import com.hkm.slidingmenulib.layoutdesigns.app.SlidingAppCompactActivity;
import com.hypebeast.demoslidemenu.helpr.fastb;
import com.hypebeast.demoslidemenu.content.mainpageDemo;


public class testblock extends SlidingAppCompactActivity<Fragment> {


    @Override
    protected int getDefaultMainActivityLayoutId() {
        return BODY_LAYOUT.actionbar.getResID();
    }

    @Override
    protected mainpageDemo getFirstMenuFragment() {
        return new mainpageDemo();
    }

    @Override
    protected int getRmenu() {
        return R.menu.function_test_blocking;
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

            if (t == R.id.unblock) {
                setUnblock();
            }

            if (t == R.id.block) {
                setBlockEnableWithColor(R.color.block_color);
            }

            fastb.startfromSelectionMenu(t, this, null);
            return super.onOptionsItemSelected(item);
        } else {
            toggle();
            return true;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }


}
