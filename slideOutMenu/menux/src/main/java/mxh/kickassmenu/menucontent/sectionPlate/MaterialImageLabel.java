package mxh.kickassmenu.menucontent.sectionPlate;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.DrawableRes;
import android.support.v4.content.ContextCompat;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;

import mxh.kickassmenu.R;
import mxh.kickassmenu.menucontent.ImaterialBinder;


/**
 * Created by hesk on 24/6/15.
 */
public class MaterialImageLabel implements ImaterialBinder {

    private int imageres;
    private View view;
    private ImageView section_icon;
    private boolean bottom;
    private Context ctx;

    public MaterialImageLabel(Context ctx, @DrawableRes int imageres, boolean bottom) {
        this.imageres = imageres;
        this.bottom = bottom;
        this.ctx = ctx;
    }

    public View getView() {
        return view;
    }

    public int getHeight(float density) {
        return (int) (50 * density);
    }

    public boolean isBottom() {
        return bottom;
    }

    public void setBottom(boolean bottom) {
        this.bottom = bottom;
    }

    @Override
    public int getLayout() {
        return R.layout.section_image;
    }

    @Override
    public View init(View view) {
        section_icon = (ImageView) view.findViewById(R.id.section_icon);
        Resources.Theme theme = ctx.getTheme();
        TypedValue typedValue = new TypedValue();
        theme.resolveAttribute(R.attr.labelColor, typedValue, true);
        int textColor = typedValue.data;
        section_icon.setImageDrawable(ContextCompat.getDrawable(view.getContext(), imageres));
        this.view = view;
        return view;
    }
}
