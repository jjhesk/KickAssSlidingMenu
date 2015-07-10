package com.hkm.slidingmenulib.advancedtreeview;

import java.util.List;
import java.util.UUID;

/**
 * Created by hesk on 8/7/15.
 */
public class SimpleMenuItemData extends ExpandableItemData {
    /**
     * construct the basic view type
     *
     * @param type     ExpAdapter.ExpandableViewTypes.VIEW_TYPES
     * @param text     the normal string
     * @param path     the path of the text or link
     * @param children the children list
     */
    public SimpleMenuItemData(int type, String text, String path, List<SimpleMenuItemData> children) {
        super(type, text, path, UUID.randomUUID().toString(), 0, children);
    }


}
