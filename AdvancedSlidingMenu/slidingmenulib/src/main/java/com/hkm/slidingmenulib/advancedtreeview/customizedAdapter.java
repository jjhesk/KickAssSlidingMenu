package com.hkm.slidingmenulib.advancedtreeview;

import android.content.Context;
import com.hkm.slidingmenulib.advancedtreeview.customizationbase.child;
import com.hkm.slidingmenulib.advancedtreeview.customizationbase.parent;

/**
 * Created by hesk on 14/7/15.
 */
public abstract class customizedAdapter<G extends parent<SmartItem>, T extends child<SmartItem>> extends ExpAdapter<SmartItem, G, T> {

    public customizedAdapter(Context context) {
        super(context, FastLib.stringIconExpandableStyleA, EXPANDABLE_ITEMS, true);
    }

}
