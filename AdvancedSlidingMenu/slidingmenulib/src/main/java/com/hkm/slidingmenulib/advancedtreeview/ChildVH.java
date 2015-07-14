package com.hkm.slidingmenulib.advancedtreeview;

/**
 * Created by hesk on 10/7/15.
 */
public interface ChildVH<bindData extends ExpandableItemData> {
    void bindView(final bindData itemData, int position);

    void onChildItemClick(final String title, final String path);
}
