package com.hypebeast.demoslidemenu.content;

import android.view.View;

import com.hkm.slidingmenulib.menucontent.LAYOUT_DRAWER;
import com.hkm.slidingmenulib.menucontent.MenuBuildHelper;
import com.hkm.slidingmenulib.menucontent.MaterialMenu;
import com.hkm.slidingmenulib.menucontent.sectionPlate.MaterialSection;
import com.hkm.slidingmenulib.menucontent.materialMenuConstructorFragmentBase;
import com.hkm.slidingmenulib.menucontent.sectionPlate.touchItems.MaterialListSection;
import com.hypebeast.demoslidemenu.R;

import java.util.List;
import java.util.Random;

/**
 * Created by hesk on 23/6/15.
 */
public class menupageDemo extends materialMenuConstructorFragmentBase<SectionSubMenu, Sample, menupageDemo.Adbinder> {

    public static class Adbinder extends MaterialListSection.RenderViewBindAdapter<Sample> {

        /**
         * dynamic object to start
         *
         * @param list the list source
         */
        public Adbinder(List list) {
            super(list);
        }

        @Override
        protected void withBindHolder(final MaterialListSection.RowBinder holder, final Sample data, final int pos) {
         //   holder.text.setText(data.getItem_name());
            super.withBindHolder(holder, data, pos);
        }
    }

    @Override
    protected void configurationNavigation(MenuBuildHelper navi) {

    }

    @Override
    protected void configurationMenu(MaterialMenu menu) {
        // NavBuilder().newImageLabel("home", ContextCompat.getDrawable(getActivity(), R.mipmap.cross_mi), menu);
        NavBuilder().newImageLabel("home", R.mipmap.ic_launcher, menu);
        NavBuilder().newSection("toneon", new mainpageDemo(), menu).getPlateSection();
        NavBuilder().newSectionSticky("fsioenf", new mainpageDemo(), menu).getPlateSection();
        NavBuilder().newSectionSticky(R.string.hello_world, new mainpageDemo(), menu).getPlateSection();

    }

    @Override
    protected Adbinder newBinderRenderForListSection(List dataSource) {
        return new Adbinder(dataSource);
    }

    protected LAYOUT_DRAWER getLayoutId() {
        return LAYOUT_DRAWER.STICKY_UP;
    }


    @Override
    protected Sample genNewSampleDataobject(Random e, int atPos) {
        return new Sample();
    }

    @Override
    public void onBeforeChangeSection(MaterialSection newSection) {

    }

    @Override
    public void onAfterChangeSection(MaterialSection newSection) {

    }

    @Override
    public void onClick(MaterialSection section, View v) {

    }
}
