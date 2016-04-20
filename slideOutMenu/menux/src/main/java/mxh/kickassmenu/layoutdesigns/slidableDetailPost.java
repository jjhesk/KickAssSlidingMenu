package mxh.kickassmenu.layoutdesigns;


import android.widget.RelativeLayout;

import com.sothree.slidinguppanel.SlidingUpPanelLayout;

import mxh.kickassmenu.R;
import mxh.kickassmenu.layoutdesigns.app.SlidingAppCompactActivity;

/**
 * Created by hesk on 21/7/15.
 */
public abstract class slidableDetailPost<F> extends singleDetailPost<F> implements SlidingUpPanelLayout.PanelSlideListener {

    /**
     * setting the default main activity layout ID and this is normally had presented in the library and no need change unless there is a customization need for different layout ID
     *
     * @return resource id
     */
    protected int getDefaultMainActivityLayoutId() {
        return SlidingAppCompactActivity.BODY_LAYOUT.singlepullslide.getResID();
    }

    protected SlidingUpPanelLayout supl;
    private RelativeLayout aslb_dragabe_test;

    @Override
    protected void initalizeOtherUI() {
        aslb_dragabe_test = (RelativeLayout) findViewById(R.id.aslib_dragview);
        supl = (SlidingUpPanelLayout) findViewById(R.id.aslib_sliding_layout);
        supl.setPanelSlideListener(this);
        addviewtodrageview(aslb_dragabe_test);
    }

    protected abstract void addviewtodrageview(RelativeLayout cli);
}
