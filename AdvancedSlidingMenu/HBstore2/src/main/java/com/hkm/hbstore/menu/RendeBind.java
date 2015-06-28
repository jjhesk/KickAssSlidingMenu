package com.hkm.hbstore.menu;

import com.hkm.slidingmenulib.menucontent.sectionPlate.touchItems.MaterialListSection;

import java.util.List;

/**
 * Created by hesk on 26/6/15.
 */
public class RendeBind extends MaterialListSection.RenderViewBindAdapter<SubItem> {

    /**
     * dynamic object to start
     *
     * @param list the list source
     */
    public RendeBind(List list) {
        super(list);
    }

    @Override
    protected void withBindHolder(final MaterialListSection.RowBinder holder, final SubItem data, final int pos) {
        holder.text.setText(data.getItem_name());
        super.withBindHolder(holder, data, pos);
    }
}