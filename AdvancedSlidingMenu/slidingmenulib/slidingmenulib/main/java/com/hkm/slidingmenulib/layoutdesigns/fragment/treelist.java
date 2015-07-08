package com.hkm.slidingmenulib.layoutdesigns.fragment;

import android.os.Bundle;
import android.os.Handler;

import com.hkm.slidingmenulib.Util.TreeList;
import com.hkm.slidingmenulib.menucontent.treelist.ExpAdapter;
import com.hkm.slidingmenulib.menucontent.treelist.ItemData;

import java.util.List;

/**
 * Created by hesk on 8/7/15.
 */
public abstract class treelist<adapter extends ExpAdapter> extends simpleTreeList<adapter> {

    protected final Handler h = new Handler();
    public final static int
            UNSET = -1,
            LOADMENU = -949,
            LOADSYSTEM = -593;

    protected String adapter_url, cate_title, slugtag;
    protected int requestType;

    @Override
    protected boolean onArguments(final Bundle r) {
        requestType = r.getInt(REQUEST_TYPE, UNSET);
        adapter_url = r.getString(URL, "");
        slugtag = r.getString(SLUG, "");
        cate_title = r.getString(FRAGMENTTITLE, "");
        return !adapter_url.equalsIgnoreCase("") || requestType != UNSET;
    }

    protected abstract List<ItemData> loadCustomMenu();

    protected void loadDataInitial(final adapter ad) {
        switch (requestType) {
            case LOADMENU:
                ad.addAll(loadCustomMenu(), 0);
                break;
            case LOADSYSTEM:
                ad.addAll(TreeList.getChildrenByPath("/", 0), 0);
                break;
        }
    }
}
