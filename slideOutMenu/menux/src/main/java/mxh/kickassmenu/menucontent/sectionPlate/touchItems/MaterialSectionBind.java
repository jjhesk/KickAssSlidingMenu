package mxh.kickassmenu.menucontent.sectionPlate.touchItems;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.DrawableRes;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import mxh.kickassmenu.R;
import mxh.kickassmenu.menucontent.ImaterialBinder;


/**
 * Created by hesk on 24/6/15.
 */
public class MaterialSectionBind<CustomTextView extends TextView> implements ImaterialBinder {
    public CustomTextView text;
    public ImageView iconHolder;
    private final boolean hasIcon;
    private int iconRes;

    public MaterialSectionBind() {
        hasIcon = false;
    }

    public MaterialSectionBind(@DrawableRes int icon) {
        hasIcon = true;
        iconRes = icon;
    }

    public boolean withIcon() {
        return hasIcon;
    }

    @Override
    public int getLayout() {
        return hasIcon ? R.layout.section_icon : R.layout.section_material;
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @SuppressLint("WrongViewCast")
    public View init(View view) {
        text = (CustomTextView) view.findViewById(R.id.section_text);
        if (hasIcon) {
            iconHolder = (ImageView) view.findViewById(R.id.section_icon);
            Drawable icon = ContextCompat.getDrawable(view.getContext(), iconRes);
            iconHolder.setImageDrawable(icon);
        }
        return view;
    }

    public void tintColor(int iconColor) {
        if (withIcon())
            this.iconHolder.setColorFilter(iconColor);
    }

    private void checkVisibleAndVisible() {
        if (withIcon()) {
            if (iconHolder.getVisibility() == View.INVISIBLE || iconHolder.getVisibility() == View.GONE) {
                iconHolder.setVisibility(View.VISIBLE);
            }
        }
    }

    public void reConfigIcon(final Drawable image) {
        if (withIcon()) {
            iconHolder.setImageDrawable(image);
            checkVisibleAndVisible();
        }
    }

    public void reConfigIcon(final Drawable image, final int colorTint) {
        if (withIcon()) {
            iconHolder.setImageDrawable(image);
            iconHolder.setColorFilter(colorTint);
            checkVisibleAndVisible();
        }
    }


    public void reConfigIcon(final Bitmap image) {
        if (withIcon()) {
            iconHolder.setImageBitmap(image);
            checkVisibleAndVisible();
        }
    }

    public void reConfigIcon(final Bitmap image, final int colorTint) {
        if (withIcon()) {
            iconHolder.setImageBitmap(image);
            iconHolder.setColorFilter(colorTint);
            checkVisibleAndVisible();
        }
    }
}
