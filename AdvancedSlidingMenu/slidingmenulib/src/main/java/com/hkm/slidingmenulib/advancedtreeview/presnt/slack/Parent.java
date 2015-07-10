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
import com.hkm.slidingmenulib.advancedtreeview.customizationbase.parent;
import com.hkm.slidingmenulib.menucontent.containers.MaterialRippleLayout;


/**
 * Created by hesk on 10/7/15.
 * please help to improve this library
 */
public class Parent extends parent {

    public ImageView image;
    public TextView text;
    public ImageView expand;
    public TextView count;

    public MaterialRippleLayout relativeLayout;

    public Parent(View itemView) {
        super(itemView);
        text = (TextView) itemView.findViewById(R.id.section_text);
        expand = (ImageView) itemView.findViewById(R.id.indicatorIcon);
        count = (TextView) itemView.findViewById(R.id.section_notification);
        relativeLayout = (MaterialRippleLayout) itemView.findViewById(R.id.section_ripple_wrapper_click);   //clickable
        itemMargin = itemView.getContext().getResources().getDimensionPixelSize(R.dimen.item_margin);
    }


    @Override
    protected void setCountVisible(int visibility) {
        count.setVisibility(visibility);
    }

    @Override
    protected void updateCountNumber(String text) {
        count.setText(text);
    }

    @Override
    public void bindView(final ExpandableItemData itemData, final int position, final ItemDataClickListener imageClickListener) {
        expand.setLayoutParams(getParamsLayout(expand, itemData));
        text.setText(itemData.getText());
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


    /**
     * will not be used
     *
     * @param view nothing
     * @return nothing
     */
    @Override
    protected BaseViewHolder getHolder(View view) {
        return null;
    }

    /**
     * will not be used
     *
     * @return nothing
     */
    @Override
    protected int getLayout() {
        return 0;
    }
}
