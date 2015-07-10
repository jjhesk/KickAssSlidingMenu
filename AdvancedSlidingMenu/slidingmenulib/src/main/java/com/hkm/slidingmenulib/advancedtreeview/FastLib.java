package com.hkm.slidingmenulib.advancedtreeview;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hkm.slidingmenulib.R;
import com.hkm.slidingmenulib.advancedtreeview.presnt.slack.Child;
import com.hkm.slidingmenulib.advancedtreeview.presnt.slack.Parent;
import com.hkm.slidingmenulib.advancedtreeview.presnt.system.ChildViewHolder;
import com.hkm.slidingmenulib.advancedtreeview.presnt.system.ParentViewHolder;

/**
 * Created by hesk on 10/7/15.
 */
public enum FastLib {
    //            stringIconExpandableStyleA()
    //   stringExpandableStyle

    stringIconExpandableStyleA(R.layout.exp_2_item_parent, R.layout.exp_1_item_child) {
        @Override
        protected Parent getHolderP(View view) {
            return new Parent(view);
        }

        @Override
        protected Child getHolderC(View view) {
            return new Child(view);
        }


    },
    systemStyle(R.layout.exp_sys_item_parent, R.layout.exp_sys_item_child) {
        @Override
        protected ParentViewHolder getHolderP(View view) {
            return new ParentViewHolder(view);
        }

        @Override
        protected ChildViewHolder getHolderC(View view) {
            return new ChildViewHolder(view);
        }
    };

    private int parentid, childid;

    FastLib(final @LayoutRes int parentId, final @LayoutRes int childId) {
        this.parentid = parentId;
        this.childid = childId;
    }


    protected abstract BaseViewHolder getHolderP(final View view);

    protected abstract BaseViewHolder getHolderC(final View view);

    private View getView(final Context m, final ViewGroup parent, final @LayoutRes int layout) {
        return LayoutInflater.from(m).inflate(layout, parent, false);
    }

    public BaseViewHolder createViewHolderParent(Context mContext, ViewGroup parent) {
        return getHolderP(getView(mContext, parent, parentid));
    }

    public BaseViewHolder createViewHolderChild(Context mContext, ViewGroup parent) {
        return getHolderC(getView(mContext, parent, childid));
    }
}
