package com.hypebeast.demoslidemenu;

import android.support.annotation.IdRes;

import com.hkm.slidingmenulib.layoutdesigns.singleDetailPost;
import com.hypebeast.demoslidemenu.pages.menusystemtree;

/**
 * Created by hesk on 9/9/15.
 */
public class CommonSingle extends singleDetailPost {
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
    protected menusystemtree getInitFragment() throws Exception {
        return new menusystemtree();
    }

    @Override
    protected void onMenuItemSelected(@IdRes int Id) {

    }
}
