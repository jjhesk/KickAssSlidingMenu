package com.hypebeast.demoslidemenu.content.expandablemenu;

import android.content.Context;
import android.view.View;

import com.hypebeast.demoslidemenu.R;
import com.marshalchen.ultimaterecyclerview.expanx.SmartItem;
import com.marshalchen.ultimaterecyclerview.expanx.Util.DataUtil;
import com.marshalchen.ultimaterecyclerview.expanx.customizedAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hesk on 23/2/16.
 */
public class expCustomAdapter extends customizedAdapter<Category, SubCategory> {
    public expCustomAdapter(Context context) {
        super(context);
    }


    public static List<SmartItem> getPreCodeMenu(String[] a, String[] b, String[] c) {
        List<SmartItem> e = new ArrayList<>();
        e.add(SmartItem.parent("cate 1 One Piece", "open", DataUtil.getSmallList(a)));
        e.add(SmartItem.parent("cate 2 DJ Cola", "open", DataUtil.getSmallList(b)));
        e.add(SmartItem.parent("cate 3 J5 Channel", "open", DataUtil.getSmallList(c)));
        return e;
    }


    /**
     * please do work on this id the custom object is true
     *
     * @param parentview the inflated view
     * @return the actual parent holder
     */
    @Override
    protected Category iniCustomParentHolder(View parentview) {
        return new Category(parentview);
    }

    /**
     * please do work on this if the custom object is true
     *
     * @param childview the inflated view
     * @return the actual child holder
     */
    @Override
    protected SubCategory iniCustomChildHolder(View childview) {
        return new SubCategory(childview);
    }

    @Override
    protected int getLayoutResParent() {
        return R.layout.exp_parent;
    }

    @Override
    protected int getLayoutResChild() {
        return R.layout.exp_child;
    }

    @Override
    protected List<SmartItem> getChildrenByPath(String path, int depth, int position) {
        return null;
    }


}
