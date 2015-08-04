package com.hkm.slidingmenulib.advancedtreeview.presnt.slack;

import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.os.Build;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hkm.slidingmenulib.R;
import com.hkm.slidingmenulib.advancedtreeview.BaseViewHolder;
import com.hkm.slidingmenulib.advancedtreeview.ExpandableItemData;
import com.hkm.slidingmenulib.advancedtreeview.ItemDataClickListener;
import com.hkm.slidingmenulib.advancedtreeview.SmartItem;
import com.hkm.slidingmenulib.advancedtreeview.customizationbase.parent;
import com.hkm.slidingmenulib.menucontent.containers.MaterialRippleLayout;


/**
 * Created by hesk on 10/7/15.
 * please help to improve this library
 */
public class Parent<T extends ExpandableItemData, TE extends TextView> extends parent<T> {

    public ImageView image;
    public TE text;
    public ImageView expand;
    public TE count;

    public MaterialRippleLayout relativeLayout;
    private boolean capitalized = false;
    private boolean countenabled = true;

    public Parent(View itemView) {
        super(itemView);
        text = (TE) itemView.findViewById(R.id.exp_section_title);
        expand = (ImageView) itemView.findViewById(R.id.exp_indication_arrow);
        count = (TE) itemView.findViewById(R.id.exp_section_notification_number);
        relativeLayout = (MaterialRippleLayout) itemView.findViewById(R.id.exp_section_ripple_wrapper_click);   //clickable
        itemMargin = itemView.getContext().getResources().getDimensionPixelSize(R.dimen.item_margin);
    }

    protected void forceTitleCapitalized(boolean b) {
        capitalized = b;
    }

    protected void setNotifcationFieldEnabled(boolean b) {
        countenabled = b;
        if (!countenabled) {
            count.setVisibility(View.GONE);
        } else {
            if (getItem().isExpand()) {
                count.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    protected void setCountVisible(int visibility) {
        if (countenabled)
            count.setVisibility(visibility);
    }

    @Override
    protected void updateCountNumber(String text) {
        if (countenabled)
            count.setText(text);
    }

    @Override
    public void bindView(final T itemData, final int position, final ItemDataClickListener imageClickListener) {
        expand.setLayoutParams(getParamsLayout(expand, itemData));
        if (capitalized) {
            text.setText(itemData.getText().toUpperCase());
        } else {
            text.setText(itemData.getText());
        }
        setHandleInitiatedViewStatus(itemData, expand, count);
        setRelativeLayoutClickable(relativeLayout, itemData, imageClickListener, position);
        relativeLayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Toast.makeText(view.getContext(), "longclick",
                        Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }


    @Override
    public void onParentItemClick(String path) {

    }

    @Override
    public int openDegree() {
        return 90;
    }

    @Override
    public int closeDegree() {
        return 0;
    }

    /**
     * <p>Notifies the occurrence of another frame of the animation.</p>
     *
     * @param animation The animation which was repeated.
     */
    @Override
    public void onAnimationUpdate(ValueAnimator animation) {
        expand.setRotation((Float) animation.getAnimatedValue());
    }


}
