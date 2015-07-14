package com.hkm.slidingmenulib.advancedtreeview.customizationbase;

import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hkm.slidingmenulib.advancedtreeview.BaseViewHolder;
import com.hkm.slidingmenulib.advancedtreeview.ExpandableItemData;
import com.hkm.slidingmenulib.advancedtreeview.ItemDataClickListener;
import com.hkm.slidingmenulib.advancedtreeview.ParentVH;

import java.util.List;

/**
 * Created by hesk on 10/7/15.
 */
public abstract class parent<T extends ExpandableItemData> extends BaseViewHolder<T> implements ParentVH<T>, ValueAnimator.AnimatorUpdateListener {
    public parent(View itemView) {
        super(itemView);
    }

    protected abstract BaseViewHolder getHolder(final View view);

    protected abstract int getLayout();

    public BaseViewHolder createViewHolder(Context mContext, ViewGroup parent) {
        return getHolder(getView(mContext, parent, getLayout()));
    }

    protected abstract void setCountVisible(int visibility);

    protected abstract void updateCountNumber(final String text);

    private void toggleExpenable(final ExpandableItemData itemData, final ItemDataClickListener imageClickListener, final int position) {
        if (imageClickListener != null) {
            if (itemData.isExpand()) {
                imageClickListener.onHideChildren(itemData);
                itemData.setExpand(false);
                rotationExpandIcon(openDegree(), closeDegree());
                setCountVisible(View.GONE);
            } else {
                imageClickListener.onExpandChildren(itemData);
                itemData.setExpand(true);
                rotationExpandIcon(closeDegree(), openDegree());
                List<ExpandableItemData> children = itemData.getChildren();
                if (children != null) {
                    updateCountNumber(String.format("(%s)", children.size()));
                }
                onParentItemClick(itemData.toString());
                setCountVisible(View.VISIBLE);
            }

        }

    }

    @Override
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public void rotationExpandIcon(float from, float to) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            ValueAnimator valueAnimator = ValueAnimator.ofFloat(from, to);
            valueAnimator.setDuration(150);
            valueAnimator.setInterpolator(new LinearOutSlowInInterpolator());
            valueAnimator.addUpdateListener(this);
            valueAnimator.start();
        }
    }

    protected void setHandleInitiatedViewStatus(final ExpandableItemData itemData, View rotationIndicator, TextView count) {
        if (itemData.isExpand()) {
            rotationIndicator.setRotation(openDegree());
            List<ExpandableItemData> children = itemData.getChildren();
            if (children != null) {
                count.setText(String.format("(%s)", itemData.getChildren()
                        .size()));
            }
            count.setVisibility(View.VISIBLE);
        } else {
            rotationIndicator.setRotation(closeDegree());
            count.setVisibility(View.GONE);
            count.setText("");
        }
    }

    protected <V extends View> void setRelativeLayoutClickable(final V clickablelayout, final ExpandableItemData itemData, final ItemDataClickListener imageClickListener, final int position) {
        clickablelayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleExpenable(itemData, imageClickListener, position);
            }
        });

    }
}
