package com.hkm.slidingmenulib.advancedtreeview;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.hkm.slidingmenulib.R;
import com.marshalchen.ultimaterecyclerview.UltimateRecyclerviewViewHolder;

import java.util.List;

/**
 * Author Zheng Haibo
 * PersonalWebsite http://www.mobctrl.net
 * Description
 */
public class BaseViewHolder extends UltimateRecyclerviewViewHolder {

    public BaseViewHolder(View itemView) {
        super(itemView);
        getMarginBy();
    }

    protected View getView(final Context m, final ViewGroup parent, final @LayoutRes int layout) {
        return LayoutInflater.from(m).inflate(layout, parent, false);
    }

    protected int itemMargin, itemMarginRes;
    protected int offsetMargin, offsetMarginRes;


    protected void getMarginBy() {
        //  itemView.getContext().obtainStyledAttributes(Theme.)
        itemMargin = itemView.getContext().
                getResources().getDimensionPixelSize(R.dimen.item_margin);
        offsetMargin = itemView.getContext().
                getResources().getDimensionPixelSize(R.dimen.expand_size);
    }

    protected RelativeLayout.LayoutParams getParamsLayoutOffset(ImageView image, ExpandableItemData itemData) {
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) image.getLayoutParams();
        params.leftMargin = itemMargin * itemData.getTreeDepth() + offsetMargin;
        return params;
    }

    protected RelativeLayout.LayoutParams getParamsLayoutOffset(RelativeLayout layout, ExpandableItemData itemData) {
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) layout.getLayoutParams();
        params.leftMargin = itemMargin * itemData.getTreeDepth() + offsetMargin;
        return params;
    }

    protected RelativeLayout.LayoutParams getParamsLayoutOffset(TextView layout, ExpandableItemData itemData) {
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) layout.getLayoutParams();
        params.leftMargin = itemMargin * itemData.getTreeDepth() + offsetMargin;
        return params;
    }


    protected RelativeLayout.LayoutParams getParamsLayout(TextView layout, ExpandableItemData itemData) {
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) layout.getLayoutParams();
        params.leftMargin = itemMargin * itemData.getTreeDepth();
        //    layout.setLayoutParams();
        return params;
        //   layout.setMar
    }

    protected RelativeLayout.LayoutParams getParamsLayout(ImageView image, ExpandableItemData itemData) {
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) image.getLayoutParams();
        params.leftMargin = itemMargin * itemData.getTreeDepth();
        return params;
    }

    protected RelativeLayout.LayoutParams getParamsLayout(RelativeLayout layout, ExpandableItemData itemData) {
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) layout.getLayoutParams();
        params.leftMargin = itemMargin * itemData.getTreeDepth();
        return params;
    }


}
