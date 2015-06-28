package com.hkm.slidingmenulib.menucontent;


/**
 * Created by hesk on 23/6/15.
 */
public interface internalChangeInFragment<Fragment> {
    public void setinternalChange(Fragment section, String title);

    public void setinternalChange(Fragment section, String title, Fragment previousFragment, boolean closedrawer);

    public void setinternalChange(Fragment section, String title, Fragment previousFragment);

}
