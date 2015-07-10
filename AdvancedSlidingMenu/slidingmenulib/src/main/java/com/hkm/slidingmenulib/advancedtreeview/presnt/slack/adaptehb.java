package com.hkm.slidingmenulib.advancedtreeview.presnt.slack;

import android.content.Context;

import com.hkm.slidingmenulib.Util.TreeList;
import com.hkm.slidingmenulib.advancedtreeview.ExpAdapter;
import com.hkm.slidingmenulib.advancedtreeview.ExpandableItemData;

import java.util.List;

/**
 * Created by hesk on 10/7/15.
 */
public class adaptehb extends ExpAdapter<ExpandableItemData> {
    public adaptehb(Context context) {
        super(context);
    }

    @Override
    protected List<ExpandableItemData> getChildrenByPath(String path, int depth) {
        return TreeList.getChildrenByPath(path, depth);
    }

    public void startListFromPath(final String path) {
        addAll(TreeList.getChildrenByPath(path, 0), 0);
    }

}
