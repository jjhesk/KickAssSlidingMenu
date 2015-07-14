package com.hkm.slidingmenulib.advancedtreeview.customizationbase;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.hkm.slidingmenulib.advancedtreeview.BaseViewHolder;
import com.hkm.slidingmenulib.advancedtreeview.ChildClickListener;
import com.hkm.slidingmenulib.advancedtreeview.ExpandableItemData;
import com.hkm.slidingmenulib.advancedtreeview.ChildVH;

/**
 * Created by hesk on 10/7/15.
 */
public abstract class child<T extends ExpandableItemData> extends BaseViewHolder<T> implements ChildVH<T> {
    public child(View itemView) {
        super(itemView);
    }

    protected abstract BaseViewHolder getHolder(final View view);

    protected abstract int getLayout();


    public BaseViewHolder createViewHolder(Context mContext, ViewGroup parent) {
        return getHolder(getView(mContext, parent, getLayout()));
    }

    protected ChildClickListener listener;

    public void setChildListener(ChildClickListener mlistener) {
        this.listener = mlistener;
    }


}
