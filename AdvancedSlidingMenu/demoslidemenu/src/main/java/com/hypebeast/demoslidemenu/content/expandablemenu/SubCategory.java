package com.hypebeast.demoslidemenu.content.expandablemenu;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.marshalchen.ultimaterecyclerview.expanx.SmartItem;
import com.marshalchen.ultimaterecyclerview.expanx.Util.easyTemplateChild;

/**
 * Created by hesk on 16/7/15.
 */
public class SubCategory extends easyTemplateChild<SmartItem, TextView, RelativeLayout> {
    public SubCategory(View itemView) {
        super(itemView);
    }
}
