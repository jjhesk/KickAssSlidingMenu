package com.hypebeast.demoslidemenu.pages;

import android.widget.TextView;

import com.hkm.slidingmenulib.menucontent.sectionPlate.touchItems.MaterialListSection;

import java.util.List;

/**
 * Created by hesk on 26/6/15.
 */
public class SectionSubMenu extends MaterialListSection<SubItem, TextView> {
    public static class render extends RenderViewBindAdapter<SubItem> {

        /**
         * dynamic object to start
         *
         * @param list the list source
         */
        public render(List list) {
            super(list);
        }

        @Override
        protected void withBindHolder(final RowBinder holder, final SubItem data, final int pos) {
            holder.text.setText(data.getItem_name());
            super.withBindHolder(holder, data, pos);
        }
    }
}
