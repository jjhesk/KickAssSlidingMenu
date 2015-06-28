package com.hkm.slidingmenulib.menucontent.containers;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.hkm.slidingmenulib.R;
import com.hkm.slidingmenulib.menucontent.ImaterialBinder;
import com.hkm.slidingmenulib.menucontent.LAYOUT_DRAWER;
import com.hkm.slidingmenulib.menucontent.scrolli;

import java.util.zip.Inflater;

/**
 * Created by hesk on 24/6/15.
 */
public class MaterialDrawerContainer<T extends TextView> implements ImaterialBinder, scrolli {
    public LinearLayout
            itemMenuHolder,
            items,
            background_gradient;
    public ImageView background_switcher, current_back_item_background, current_head_item_photo, second_head_item_photo, third_head_item_photo, user_transition, user_switcher;
    public T current_head_item_title, current_head_item_sub_title;
    public LAYOUT_DRAWER drawerType;
    private ScrollView scrollor;

    public MaterialDrawerContainer(LAYOUT_DRAWER layout_type) {
        drawerType = layout_type;

    }

    public int getLayout() {
        return drawerType.getLayoutRes();
    }

    @SuppressLint("WrongViewCast")
    public View init(View inflat) {
        switch (drawerType) {
            case STICKY_UP:
                itemMenuHolder = (LinearLayout) inflat.findViewById(R.id.top_sections);
                items = (LinearLayout) inflat.findViewById(R.id.items);
                scrollor = (ScrollView) inflat.findViewById(R.id.scrollviewcomponent);
                return inflat;

            case STICKY_BOTTOM:
                itemMenuHolder = (LinearLayout) inflat.findViewById(R.id.bottom_sections);
                items = (LinearLayout) inflat.findViewById(R.id.items);
                scrollor = (ScrollView) inflat.findViewById(R.id.scrollviewcomponent);
                return inflat;

            case PROFILE_HEAD:
                itemMenuHolder = (LinearLayout) inflat.findViewById(R.id.bottom_sections);
                items = (LinearLayout) inflat.findViewById(R.id.items);
                scrollor = (ScrollView) inflat.findViewById(R.id.scrollviewcomponent);

                background_gradient = (LinearLayout) inflat.findViewById(R.id.background_gradient);
                background_switcher = (ImageView) inflat.findViewById(R.id.background_switcher);
                current_back_item_background = (ImageView) inflat.findViewById(R.id.current_back_item_background);
                current_head_item_photo = (ImageView) inflat.findViewById(R.id.current_head_item_photo);
                second_head_item_photo = (ImageView) inflat.findViewById(R.id.second_head_item_photo);
                third_head_item_photo = (ImageView) inflat.findViewById(R.id.third_head_item_photo);
                user_transition = (ImageView) inflat.findViewById(R.id.user_transition);
                user_switcher = (ImageView) inflat.findViewById(R.id.user_switcher);
                current_head_item_title = (T) inflat.findViewById(R.id.current_head_item_title);
                current_head_item_sub_title = (T) inflat.findViewById(R.id.current_head_item_sub_title);
                return inflat;
            case USER_DEFINED_LAYOUT:
                return inflat;
            default:

                return inflat;
        }

    }


    public ScrollView getScrollView() {
        return scrollor;
    }


}
