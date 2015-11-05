package com.hkm.slidingmenulib.advancedtreeview.presnt.system;

import android.animation.ValueAnimator;
import android.content.Context;
import android.view.View;

import com.hkm.slidingmenulib.R;
import com.hkm.slidingmenulib.Util.TreeList;
import com.hkm.slidingmenulib.advancedtreeview.BaseViewHolder;
import com.hkm.slidingmenulib.advancedtreeview.ExpAdapter;
import com.hkm.slidingmenulib.advancedtreeview.ExpandableItemData;
import com.hkm.slidingmenulib.advancedtreeview.FastLib;
import com.hkm.slidingmenulib.advancedtreeview.ItemDataClickListener;
import com.hkm.slidingmenulib.advancedtreeview.customizationbase.child;
import com.hkm.slidingmenulib.advancedtreeview.customizationbase.parent;

import java.util.List;

/**
 * Created by hesk on 8/7/15.
 */
public class ExpSystem extends ExpAdapter<ExpandableItemData, ExpSystem.B, ExpSystem.A> {
    public ExpSystem(Context context) {
        super(context, FastLib.systemStyle);
    }


    public ExpSystem(Context context, FastLib preset) {
        super(context, preset);
    }

    @Override
    protected List<ExpandableItemData> getChildrenByPath(String path, int depth, final int pos) {
        return TreeList.getChildrenByPath(path, depth);
    }

    public void startListFromPath(final String path) {
        addAll(TreeList.getChildrenByPath(path, 0), 0);
    }


    /**
     * please do work on this id the custom object is true
     *
     * @param parentview the inflated view
     * @return the actual parent holder
     */
    @Override
    protected B iniCustomParentHolder(View parentview) {
        return null;
    }

    /**
     * please do work on this if the custom object is true
     *
     * @param childview the inflated view
     * @return the actual child holder
     */
    @Override
    protected A iniCustomChildHolder(View childview) {
        return null;
    }

    @Override
    protected int getLayoutResParent() {
        return R.layout.exp_sys_item_parent;
    }

    @Override
    protected int getLayoutResChild() {
        return R.layout.exp_sys_item_child;
    }



    public class B extends parent<ExpandableItemData> {

        public B(View itemView) {
            super(itemView);
        }


        @Override
        protected void setCountVisible(int visibility) {

        }

        @Override
        protected void updateCountNumber(String text) {

        }

        /**
         * <p>Notifies the occurrence of another frame of the animation.</p>
         *
         * @param animation The animation which was repeated.
         */
        @Override
        public void onAnimationUpdate(ValueAnimator animation) {

        }

        @Override
        public void bindView(ExpandableItemData itemData, int position, ItemDataClickListener imageClickListener) {

        }

        @Override
        public void onParentItemClick(String path) {

        }

        @Override
        public int openDegree() {
            return 0;
        }

        @Override
        public int closeDegree() {
            return 0;
        }
    }

    public class A extends child<ExpandableItemData> {

        public A(View itemView) {
            super(itemView);
        }


        @Override
        public void bindView(ExpandableItemData itemData, int position) {

        }

        @Override
        public void onChildItemClick(String title, String path) {

        }
    }
}
