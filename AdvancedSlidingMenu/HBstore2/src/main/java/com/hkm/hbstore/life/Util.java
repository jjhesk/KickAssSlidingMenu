package com.hkm.hbstore.life;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

import com.hkm.hbstore.StoreStartNew;


/**
 * Created by hesk on 11/6/15.
 */
public class Util {
    private static String APP_INTENT_TITLE = "title";
    private static String APP_INTENT_URI = "uri";

    /**
     * start the new activities
     *
     * @param packageName the package application id
     * @param url         the url to start from
     * @param title       the title to send as extra information
     */
    public static void startNewActivity(final String packageName, final String url, final String title, final Context activity) {
        Intent intent = activity.getPackageManager().getLaunchIntentForPackage(packageName);
        if (intent != null) {
        /* We found the activity now start the activity */
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            Bundle b = new Bundle();
            b.putString(APP_INTENT_URI, url);
            b.putString(APP_INTENT_TITLE, title);
            intent.putExtras(b);
            activity.startActivity(intent);
        } else {
        /* Bring user to the market or let them choose an app? */
            intent = new Intent(Intent.ACTION_VIEW);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setData(Uri.parse("market://details?id=" + packageName));
            activity.startActivity(intent);
        }
    }

    /**
     * start the intent from the list view
     *
     * @param url      the url in full path
     * @param activity the activity
     */
    public static void startFeedList(final String url, final Activity activity) {
        Bundle conData = new Bundle();
        conData.putString(Config.KEYURL, url);
        Intent intent = new Intent();
        intent.putExtras(conData);
        activity.setResult(Config.RETURN_WITH_NEW_FEED_URL, intent);
        activity.finish();
    }

    /**
     * start the new article by the single click
     *
     * @param url      in full path for url
     * @param activity the activity
     */

    public static void startNewArticle(final String url, final Activity activity) {
        final Intent intent = new Intent(activity, StoreStartNew.class);
        Bundle b = new Bundle();
        b.putString(Config.SURL, url);
        intent.putExtras(b);
        activity.startActivity(intent);
    }

    /**
     * start the application in browser to see the url or choose by other application to view this uri
     *
     * @param url      in full path for url
     * @param activity the activity
     */
    public static void openOtherUri(final String url, final Activity activity) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        activity.startActivity(intent);
    }


    public static int getMargin(final Context c) {
        final int pageMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4, c.getResources()
                .getDisplayMetrics());

        return pageMargin;
    }

    public static void startToReveal(final ViewGroup view, final int timeinit) {
        final Handler h = new Handler();
        view.setVisibility(View.VISIBLE);
        view.setAlpha(0f);
        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                view.animate().alpha(1f);
            }
        }, timeinit);
    }


    /**
     * Return a float value within the range.
     * This is just a wrapper for Math.min() and Math.max().
     * This may be useful if you feel it confusing ("Which is min and which is max?").
     *
     * @param value    the target value
     * @param minValue minimum value. If value is less than this, minValue will be returned
     * @param maxValue maximum value. If value is greater than this, maxValue will be returned
     * @return float value limited to the range
     */
    public static float getFloat(final float value, final float minValue, final float maxValue) {
        return Math.min(maxValue, Math.max(minValue, value));
    }

    /**
     * Create a color integer value with specified alpha.
     * This may be useful to change alpha value of background color.
     *
     * @param alpha     alpha value from 0.0f to 1.0f.
     * @param baseColor base color. alpha value will be ignored.
     * @return a color with alpha made from base color
     */
    public static int getColorWithAlpha(float alpha, int baseColor) {
        int a = Math.min(255, Math.max(0, (int) (alpha * 255))) << 24;
        int rgb = 0x00ffffff & baseColor;
        return a + rgb;
    }

    /**
     * Add an OnGlobalLayoutListener for the view.
     * This is just a convenience method for using {@code ViewTreeObserver.OnGlobalLayoutListener()}.
     * This also handles removing listener when onGlobalLayout is called.
     *
     * @param view     the target view to add global layout listener
     * @param runnable runnable to be executed after the view is laid out
     */
    public static void addOnGlobalLayoutListener(final View view, final Runnable runnable) {
        ViewTreeObserver vto = view.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
                    view.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                } else {
                    view.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }
                runnable.run();
            }
        });
    }

    /**
     * Mix two colors.
     * {@code toColor} will be {@code toAlpha/1} percent,
     * and {@code fromColor} will be {@code (1-toAlpha)/1} percent.
     *
     * @param fromColor first color to be mixed
     * @param toColor   second color to be mixed
     * @param toAlpha   alpha value of toColor, 0.0f to 1.0f.
     * @return mixed color value in ARGB. Alpha is fixed value (255).
     */
    public static int mixColors(int fromColor, int toColor, float toAlpha) {
        float[] fromCmyk = ScrollUtils.cmykFromRgb(fromColor);
        float[] toCmyk = ScrollUtils.cmykFromRgb(toColor);
        float[] result = new float[4];
        for (int i = 0; i < 4; i++) {
            result[i] = Math.min(1, fromCmyk[i] * (1 - toAlpha) + toCmyk[i] * toAlpha);
        }
        return 0xff000000 + (0x00ffffff & ScrollUtils.rgbFromCmyk(result));
    }

    /**
     * Convert RGB color to CMYK color.
     *
     * @param rgbColor target color
     * @return CMYK array
     */
    public static float[] cmykFromRgb(int rgbColor) {
        int red = (0xff0000 & rgbColor) >> 16;
        int green = (0xff00 & rgbColor) >> 8;
        int blue = (0xff & rgbColor);
        float black = Math.min(1.0f - red / 255.0f, Math.min(1.0f - green / 255.0f, 1.0f - blue / 255.0f));
        float cyan = 1.0f;
        float magenta = 1.0f;
        float yellow = 1.0f;
        if (black != 1.0f) {
            // black 1.0 causes zero divide
            cyan = (1.0f - (red / 255.0f) - black) / (1.0f - black);
            magenta = (1.0f - (green / 255.0f) - black) / (1.0f - black);
            yellow = (1.0f - (blue / 255.0f) - black) / (1.0f - black);
        }
        return new float[]{cyan, magenta, yellow, black};
    }

    /**
     * Convert CYMK color to RGB color.
     * This method doesn't check f cmyk is not null or have 4 elements in array.
     *
     * @param cmyk target CYMK color. Each value should be between 0.0f to 1.0f,
     *             and should be set in this order: cyan, magenta, yellow, black.
     * @return ARGB color. Alpha is fixed value (255).
     */
    public static int rgbFromCmyk(float[] cmyk) {
        float cyan = cmyk[0];
        float magenta = cmyk[1];
        float yellow = cmyk[2];
        float black = cmyk[3];
        int red = (int) ((1.0f - Math.min(1.0f, cyan * (1.0f - black) + black)) * 255);
        int green = (int) ((1.0f - Math.min(1.0f, magenta * (1.0f - black) + black)) * 255);
        int blue = (int) ((1.0f - Math.min(1.0f, yellow * (1.0f - black) + black)) * 255);
        return ((0xff & red) << 16) + ((0xff & green) << 8) + (0xff & blue);
    }



}
