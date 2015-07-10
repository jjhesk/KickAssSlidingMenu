package com.hkm.slidingmenulib.advancedtreeview.presnt.system;

import android.content.Context;

import com.hkm.slidingmenulib.Util.TreeList;
import com.hkm.slidingmenulib.advancedtreeview.ExpAdapter;
import com.hkm.slidingmenulib.advancedtreeview.ExpandableItemData;
import com.hkm.slidingmenulib.advancedtreeview.FastLib;

import java.util.List;

/**
 * Created by hesk on 8/7/15.
 */
public class ExpSystem extends ExpAdapter<ExpandableItemData> {
    public ExpSystem(Context context) {
        super(context);
    }

    public ExpSystem(Context context, FastLib preset) {
        super(context, preset);
    }

    @Override
    protected List<ExpandableItemData> getChildrenByPath(String path, int depth) {
        return TreeList.getChildrenByPath(path, depth);
    }

    public void startListFromPath(final String path) {
        addAll(TreeList.getChildrenByPath(path, 0), 0);
    }
}
