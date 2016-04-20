package mxh.kickassmenu.Util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import mxh.kickassmenu.layoutdesigns.singleDetailPost;


public class Utils {

    public static final int RESULTSINGLE = 692;

    /**
     * start the single page with full route URL
     *
     * @param route the full url
     * @param ctx   the resource context
     * @param clazz the class name
     * @param <T>   the type extends from singleDetailPost
     */
    public static <T extends singleDetailPost> void routeSinglePage(final String route, final Context ctx, final Class<T> clazz) {
        Intent n = new Intent(ctx, clazz);
        final Bundle b = new Bundle();
        b.putInt(singleDetailPost.Method, singleDetailPost.REQUEST_METHOD_FULL_URL);
        b.putString(singleDetailPost.requestURL, route);
        n.putExtras(b);
        ctx.startActivity(n);
    }

    /**
     * start the single page with post id
     *
     * @param pid   the post ID
     * @param ctx   the resource context
     * @param clazz the type class
     * @param <T>   the type
     */
    public static <T extends singleDetailPost> void routeSinglePage(final long pid, final Context ctx, final Class<T> clazz) {
        Intent n = new Intent(ctx, clazz);
        final Bundle b = new Bundle();
        b.putInt(singleDetailPost.Method, singleDetailPost.REQUEST_METHOD_POST_ID);
        b.putLong(singleDetailPost.PID, pid);
        n.putExtras(b);
        ctx.startActivity(n);
    }

    /**
     * start the route single in the page with result prompt as it is finished
     *
     * @param pid   Page ID
     * @param ctx   the Context
     * @param clazz the class name
     * @param <T>   the generic type
     */
    public static <T extends singleDetailPost> void routeSinglePage(final long pid, final Activity ctx, final Class<T> clazz) {
        Intent n = new Intent(ctx, clazz);
        final Bundle b = new Bundle();
        b.putInt(singleDetailPost.Method, singleDetailPost.REQUEST_METHOD_POST_ID);
        b.putLong(singleDetailPost.PID, pid);
        n.putExtras(b);
        ctx.startActivityForResult(n, RESULTSINGLE);
    }

    /**
     * start the route single in the page with result prompt as it is finished
     *
     * @param route the string in url
     * @param ctx   the Context
     * @param clazz the class name
     * @param <T>   the generic type
     */
    public static <T extends singleDetailPost> void routeSinglePage(final String route, final Activity ctx, final Class<T> clazz) {
        Intent n = new Intent(ctx, clazz);
        final Bundle b = new Bundle();
        b.putInt(singleDetailPost.Method, singleDetailPost.REQUEST_METHOD_FULL_URL);
        b.putString(singleDetailPost.requestURL, route);
        n.putExtras(b);
        ctx.startActivityForResult(n, RESULTSINGLE);
    }

    public static int getDrawerWidth(Resources res, int ownDP) {
        if (ownDP == 0) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {

                if (res.getConfiguration().smallestScreenWidthDp >= 600 || res.getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    // device is a tablet
                    return (int) (320 * res.getDisplayMetrics().density);
                } else {
                    return (int) (res.getDisplayMetrics().widthPixels - (56 * res.getDisplayMetrics().density));
                }
            } else { // for devices without smallestScreenWidthDp reference calculate if device screen is over 600 dp
                if ((res.getDisplayMetrics().widthPixels / res.getDisplayMetrics().density) >= 600 || res.getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
                    return (int) (320 * res.getDisplayMetrics().density);
                else
                    return (int) (res.getDisplayMetrics().widthPixels - (56 * res.getDisplayMetrics().density));
            }
        } else {
            return ownDP;
        }
    }

    public static int getDrawerWidth(Resources res) {
        if (res.getConfiguration().smallestScreenWidthDp >= 600 || res.getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            // device is a tablet
            return (int) (320 * res.getDisplayMetrics().density);
        } else {
            return (int) (res.getDisplayMetrics().widthPixels - (56 * res.getDisplayMetrics().density));
        }

    }

    public static Point getUserPhotoSize(Resources res) {
        int size = (int) (64 * res.getDisplayMetrics().density);

        return new Point(size, size);
    }

    public static Point getBackgroundSize(Resources res, int drawerDPWidth) {
        int width = drawerDPWidth;
        if (drawerDPWidth == 0)
            width = getDrawerWidth(res, drawerDPWidth);

        int height = (9 * width) / 16;

        return new Point(width, height);
    }

  /*  public static Bitmap resizeBitmap(Bitmap bitmap, int reqWidth,int reqHeight) {
        return Bitmap.createScaledBitmap(bitmap,reqWidth,reqHeight,true);

    }*/

    public static int calculateSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) > reqHeight
                    && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }

    public static Bitmap resizeBitmapFromResource(Resources res, int resId, int reqWidth, int reqHeight) {
        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);
        // Calculate inSampleSize
        options.inSampleSize = calculateSize(options, reqWidth, reqHeight);
        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }

    public static void recycleDrawable(Drawable drawable) {
        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            //if (bitmapDrawable.getBitmap() != null && !bitmapDrawable.getBitmap().isRecycled())
            //{
            bitmapDrawable.getBitmap().recycle();
            System.gc();
            //}
        }
    }

    public static boolean isTablet(Resources res) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            return res.getConfiguration().smallestScreenWidthDp >= 600;
        } else { // for devices without smallestScreenWidthDp reference calculate if device screen is over 600
            return (res.getDisplayMetrics().widthPixels / res.getDisplayMetrics().density) >= 600;

        }
    }

    public static LinearLayout.LayoutParams genVerticalLayoutParam(final int height_dp, float density) {
        final LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (int) (height_dp * density));
        return params;
    }

    public static LinearLayout.LayoutParams genVerticalLayoutParam(final int height_pixel) {
        final LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, height_pixel);
        return params;
    }

    public static View inflatView(Context c, final @LayoutRes int t) {
        final View u = LayoutInflater.from(c).inflate(t, null, false);
        return u;
    }

}