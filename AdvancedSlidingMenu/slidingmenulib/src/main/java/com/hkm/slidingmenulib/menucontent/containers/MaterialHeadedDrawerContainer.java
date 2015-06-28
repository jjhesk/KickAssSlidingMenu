package com.hkm.slidingmenulib.menucontent.containers;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.hkm.slidingmenulib.R;
import com.hkm.slidingmenulib.menucontent.LAYOUT_DRAWER;
import com.hkm.slidingmenulib.menucontent.scrolli;

/**
 * Created by hesk on 24/6/15.
 */
public class MaterialHeadedDrawerContainer extends MaterialDrawerContainer implements scrolli {
    public LinearLayout background_gradient;
    public ImageView background_switcher, current_back_item_background, current_head_item_photo, second_head_item_photo, third_head_item_photo, user_transition, user_switcher;
    public TextView current_head_item_title, current_head_item_sub_title;
    private ScrollView scrollor;

    public MaterialHeadedDrawerContainer(LAYOUT_DRAWER layout_type) {
        super(layout_type);
    }

    public int getLayout() {
        return R.layout.layout_drawer_headed;
    }

    public View init(View inflat) {
        super.init(inflat);
        background_gradient = (LinearLayout) inflat.findViewById(R.id.background_gradient);
        background_switcher = (ImageView) inflat.findViewById(R.id.background_switcher);
        current_back_item_background = (ImageView) inflat.findViewById(R.id.current_back_item_background);
        current_head_item_photo = (ImageView) inflat.findViewById(R.id.current_head_item_photo);
        second_head_item_photo = (ImageView) inflat.findViewById(R.id.second_head_item_photo);
        third_head_item_photo = (ImageView) inflat.findViewById(R.id.third_head_item_photo);
        user_transition = (ImageView) inflat.findViewById(R.id.user_transition);
        user_switcher = (ImageView) inflat.findViewById(R.id.user_switcher);
        current_head_item_title = (TextView) inflat.findViewById(R.id.current_head_item_title);
        current_head_item_sub_title = (TextView) inflat.findViewById(R.id.current_head_item_sub_title);
        scrollor = (ScrollView) inflat.findViewById(R.id.scrollviewcomponent);
        return inflat;
    }

    @Override
    public ScrollView getScrollView() {
        return scrollor;
    }
}
