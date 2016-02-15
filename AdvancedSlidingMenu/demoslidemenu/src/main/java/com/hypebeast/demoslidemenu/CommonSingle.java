package com.hypebeast.demoslidemenu;

import android.support.annotation.IdRes;

import com.hkm.slidingmenulib.layoutdesigns.singleDetailPost;
import com.hypebeast.demoslidemenu.content.DemoFrag2;

/**
 * Created by hesk on 9/9/15.
 */
public class CommonSingle extends singleDetailPost<DemoFrag2>{
    @Override
    protected void loadPageWithFullURL(String url) {

    }

    @Override
    protected void loadPageWithPID(long pid) {

    }

    /**
     * setting the first initial fragment at the beginning
     *
     * @return generic type fragment
     * @throws Exception the exception for the wrongs
     */
    @Override
    protected DemoFrag2 getInitFragment() throws Exception {
        return new DemoFrag2();
    }

    @Override
    protected void onMenuItemSelected(@IdRes int Id) {

    }
}
