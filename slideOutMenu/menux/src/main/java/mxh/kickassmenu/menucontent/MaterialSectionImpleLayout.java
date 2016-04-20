package mxh.kickassmenu.menucontent;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.View;

/**
 * Created by hesk on 24/6/15.
 */
public interface MaterialSectionImpleLayout {


    void select();

    void unSelect();

    View getView();

    void setIcon(Drawable drawbleicon);

    void setIcon(Bitmap drawbleicon);

    void checkrefresh();

}
