package com.hkm.slidingmenulib.advancedtreeview.presnt.slack;

import android.content.Context;
import android.view.View;

import com.hkm.slidingmenulib.R;
import com.hkm.slidingmenulib.Util.TreeList;
import com.hkm.slidingmenulib.advancedtreeview.ExpAdapter;
import com.hkm.slidingmenulib.advancedtreeview.ExpandableItemData;
import com.hkm.slidingmenulib.advancedtreeview.FastLib;
import com.hkm.slidingmenulib.advancedtreeview.customizationbase.child;
import com.hkm.slidingmenulib.advancedtreeview.customizationbase.parent;

import java.util.List;

/**
 * Created by hesk on 10/7/15.
 */
public abstract class customadapterhb<T extends ExpandableItemData, G extends parent<T>, H extends child<T>> extends ExpAdapter<T, G, H> {

    public customadapterhb(Context context) {
        super(context, FastLib.stringIconExpandableStyleA, EXPANDABLE_ITEMS, true);
    }

    @Override
    protected int getLayoutResParent() {
        return R.layout.exp_2_item_parent;
    }

    @Override
    protected int getLayoutResChild() {
        return R.layout.exp_1_item_child;
    }


}
