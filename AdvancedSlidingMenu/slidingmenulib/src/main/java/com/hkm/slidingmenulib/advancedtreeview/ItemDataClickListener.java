package com.hkm.slidingmenulib.advancedtreeview;


/**
 * @Author Zheng Haibo
 * @PersonalWebsite http://www.mobctrl.net
 * @Description
 */
public interface ItemDataClickListener<T extends ExpandableItemData> {

    void onExpandChildren(T itemData);

    void onHideChildren(T itemData);

}
