package com.hypebeast.demoslidemenu.pages;

import android.os.Bundle;

import com.hkm.slidingmenulib.advancedtreeview.ExpandableItemData;
import com.hkm.slidingmenulib.advancedtreeview.FastLib;
import com.hkm.slidingmenulib.advancedtreeview.presnt.system.ExpSystem;
import com.hkm.slidingmenulib.layoutdesigns.fragment.treelist;
import com.marshalchen.ultimaterecyclerview.UltimateRecyclerView;

import java.util.List;

/**
 * Created by hesk on 10/7/15.
 */
public class menusystemtree extends treelist<ExpSystem, ExpandableItemData> {
    public static Bundle from(final int e) {
        final Bundle n = new Bundle();
        n.putInt(REQUEST_TYPE, e);
        return n;
    }

    public static treemenu newInstanceFromMenu() {
        final treemenu g = new treemenu();
        g.setArguments(from(LOADMENU));
        return g;
    }

    public static treemenu newInstanceFromSystem() {
        final treemenu g = new treemenu();
        g.setArguments(from(LOADSYSTEM));
        return g;
    }

    @Override
    protected List<ExpandableItemData> loadCustomMenu() {
        return null;
    }

    /**
     * load the adapter with the data list
     *
     * @return the adapter with type
     */
    @Override
    protected ExpSystem getAdatperWithdata() {
        return new ExpSystem(getActivity(), FastLib.systemStyle);
    }

    /**
     * set up the extra configurations on the ultimate recycler view
     *
     * @param listview the list with view
     * @param madapter the adapter
     */
    @Override
    protected void setUltimateRecyclerViewExtra(UltimateRecyclerView listview, ExpSystem madapter) {

    }
}
