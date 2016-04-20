package mxh.kickassmenu.Util.xmlViews;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.FrameLayout;

/**
 * Created by hesk on 14/8/15.
 */
public class ControlableFrame extends FrameLayout {
    public ControlableFrame(Context context) {
        super(context);
    }

    protected boolean enable_block = false;

    public ControlableFrame(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ControlableFrame(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return enable_block;
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void setDimDrawble(@DrawableRes int dr) {
        setForeground(getContext().getDrawable(dr));
        enable_block = true;
    }

    public void setDimColor(@ColorRes int n_color) {
        setForeground(new ColorDrawable(getContext().getResources().getColor(n_color)));
        enable_block = true;
    }

    public void noBlock() {
        setForeground(null);
        enable_block = false;
    }
}
