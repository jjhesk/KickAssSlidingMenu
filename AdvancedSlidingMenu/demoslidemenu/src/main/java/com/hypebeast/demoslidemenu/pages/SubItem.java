package com.hypebeast.demoslidemenu.pages;

import android.net.Uri;

/**
 * Created by hesk on 26/6/15.
 */
public class SubItem {
    private String item_name;
    private String url;
    private Uri route;

    public SubItem() {
    }

    public String getItem_name() {
        return item_name;
    }

    public Uri getRoute() {
        return route;
    }
}
