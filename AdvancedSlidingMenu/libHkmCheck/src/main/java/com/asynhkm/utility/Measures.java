package com.asynhkm.utility;

import android.content.Context;
import android.util.DisplayMetrics;

/**
 * Created by hesk on 3/10/15.
 */
public class Measures {
    public static int dpToPx(int dp, Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        int px = Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_XXXHIGH));
        return px;
    }

    public static int pxToDp(int px, Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        int dp = Math.round(px / (displayMetrics.xdpi / DisplayMetrics.DENSITY_XXXHIGH));
        return dp;
    }
}
