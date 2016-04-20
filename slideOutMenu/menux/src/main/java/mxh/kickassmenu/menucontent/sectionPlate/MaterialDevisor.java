package mxh.kickassmenu.menucontent.sectionPlate;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;

import mxh.kickassmenu.R;


public class MaterialDevisor {
    private final boolean sticky;
    private final int thickness;

    public MaterialDevisor() {
        this.sticky = false;
        this.thickness = 1;
    }

    public MaterialDevisor(final boolean sticky) {
        this.sticky = sticky;
        this.thickness = 1;
    }

    public MaterialDevisor(final int thickness, final boolean sticky) {
        this.sticky = sticky;
        this.thickness = thickness;
    }

    public boolean isBottom() {
        return sticky;
    }


    public int getHeight(float density) {
        return (int) (this.thickness * density);
    }

    public View init(Context ctx, LinearLayout.LayoutParams sep) {
        View bar = new View(ctx);
        bar.setBackgroundResource(R.drawable.pressfill);
        bar.setLayoutParams(sep);
        return bar;
    }

}
