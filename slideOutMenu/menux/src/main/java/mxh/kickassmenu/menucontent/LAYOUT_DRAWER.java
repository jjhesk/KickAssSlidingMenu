package mxh.kickassmenu.menucontent;

import android.support.annotation.LayoutRes;
import mxh.kickassmenu.R;

/**
 * Created by hesk on 24/6/15.
 */
public enum LAYOUT_DRAWER {
    STICKY_UP(R.layout.layout_drawer_sticky_up),
    STICKY_BOTTOM(R.layout.layout_drawer_sticky_bottom),
    PROFILE_HEAD(R.layout.layout_drawer_headed),
    USER_DEFINED_LAYOUT(-1);
    private final int layout_id;

    LAYOUT_DRAWER(@LayoutRes int layout) {
        this.layout_id = layout;
    }

    public int getLayoutRes() {
        return layout_id;
    }
}
