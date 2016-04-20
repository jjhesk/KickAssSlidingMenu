package mxh.kickassmenu.menucontent.sectionPlate;

import android.view.View;
import android.widget.ImageView;

import mxh.kickassmenu.R;
import mxh.kickassmenu.menucontent.ImaterialBinder;


/**
 * Created by hesk on 24/6/15.
 */
public class MaterialImageButton implements ImaterialBinder {
    public ImageView imageBanner;

    @Override
    public int getLayout() {
        return R.layout.section_full_image;
    }

    @Override
    public View init(View view) {
        imageBanner = (ImageView) view.findViewById(R.id.section_icon);
        return null;
    }
}
