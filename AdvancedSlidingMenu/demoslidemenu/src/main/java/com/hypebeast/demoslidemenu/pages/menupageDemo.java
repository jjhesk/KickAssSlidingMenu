package com.hypebeast.demoslidemenu.pages;

import android.view.View;

import com.hkm.slidingmenulib.menucontent.MenuBuildHelper;
import com.hkm.slidingmenulib.menucontent.MaterialMenu;
import com.hkm.slidingmenulib.menucontent.sectionPlate.MaterialSection;
import com.hkm.slidingmenulib.menucontent.materialMenuConstructorFragmentBase;
import com.hypebeast.demoslidemenu.R;

/**
 * Created by hesk on 23/6/15.
 */
public class menupageDemo extends materialMenuConstructorFragmentBase {

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
    public void onBeforeChangeSection(MaterialSection newSection) {

    }

    @Override
    public void onAfterChangeSection(MaterialSection newSection) {

    }

    @Override
    public void onClick(MaterialSection section, View v) {

    }
}
