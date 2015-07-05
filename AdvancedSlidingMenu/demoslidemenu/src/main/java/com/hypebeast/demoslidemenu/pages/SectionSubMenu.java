package com.hypebeast.demoslidemenu.pages;

import android.view.View;
import android.widget.TextView;

import com.hkm.slidingmenulib.menucontent.LAYOUT_DRAWER;
import com.hkm.slidingmenulib.menucontent.MenuBuildHelper;
import com.hkm.slidingmenulib.menucontent.sectionPlate.MaterialSection;
import com.hkm.slidingmenulib.menucontent.sectionPlate.touchItems.MaterialListSection;

import java.util.List;
import java.util.Random;

/**
 * Created by hesk on 26/6/15.
 */
public class SectionSubMenu extends MaterialListSection<SubItem, TextView, SectionSubMenu.RendeBind> {
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




}
