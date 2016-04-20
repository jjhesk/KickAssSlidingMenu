package mxh.kickassmenu.menucontent;


/**
 * Created by hesk on 23/6/15.
 */
public interface internalChangeInFragment<Fragment> {
    void setinternalChange(Fragment section, String title);

    void setinternalChange(Fragment section, String title, Fragment previousFragment, boolean closedrawer);

    void setinternalChange(Fragment section, String title, Fragment previousFragment);
}
