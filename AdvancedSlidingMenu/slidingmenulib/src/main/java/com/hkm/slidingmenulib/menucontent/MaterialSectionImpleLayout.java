package com.hkm.slidingmenulib.menucontent;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.View;

/**
 * Created by hesk on 24/6/15.
 */
public interface MaterialSectionImpleLayout {


    public void select();

    public void unSelect();

    public View getView();

    public void setIcon(Drawable drawbleicon);

    public void setIcon(Bitmap drawbleicon);

    public void checkrefresh();

}
