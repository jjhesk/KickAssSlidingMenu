package com.hkm.slidingmenulib.advancedtreeview.presnt.system;

import java.util.List;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.annotation.TargetApi;
import android.os.Build;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hkm.slidingmenulib.R;
import com.hkm.slidingmenulib.advancedtreeview.BaseViewHolder;
import com.hkm.slidingmenulib.advancedtreeview.ExpandableItemData;
import com.hkm.slidingmenulib.advancedtreeview.ItemDataClickListener;
import com.hkm.slidingmenulib.advancedtreeview.ParentVH;

/**
 * Author Zheng Haibo, jjhesk edit
 * PersonalWebsite http://www.mobctrl.net
 * Description the basic framework for the parentviewholder
 */
public class ParentViewHolder extends BaseViewHolder implements ParentVH {

    public ImageView image;
    public TextView text;
    public ImageView expand;
    public TextView count;

    public RelativeLayout relativeLayout;

    public ParentViewHolder(View itemView) {
        super(itemView);
        image = (ImageView) itemView.findViewById(R.id.image);
        text = (TextView) itemView.findViewById(R.id.text);
        expand = (ImageView) itemView.findViewById(R.id.expand);
        count = (TextView) itemView.findViewById(R.id.count);
        relativeLayout = (RelativeLayout) itemView.findViewById(R.id.container);
        itemMargin = itemView.getContext().getResources().getDimensionPixelSize(R.dimen.item_margin);
    }

    @Override
    public void bindView(final ExpandableItemData itemData, final int position,
                         final ItemDataClickListener imageClickListener) {
        expand.setLayoutParams(getParamsLayout(expand, itemData));
        text.setText(itemData.getText());
        if (itemData.isExpand()) {
            expand.setRotation(openDegree());
            List<ExpandableItemData> children = itemData.getChildren();
            if (children != null) {
                count.setText(String.format("(%s)", itemData.getChildren()
                        .size()));
            }
            count.setVisibility(View.VISIBLE);
        } else {
            expand.setRotation(closeDegree());
            count.setVisibility(View.GONE);
        }
        relativeLayout.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (imageClickListener != null) {
                    if (itemData.isExpand()) {
                        imageClickListener.onHideChildren(itemData);
                        itemData.setExpand(false);
                        rotationExpandIcon(openDegree(), closeDegree());
                        count.setVisibility(View.GONE);
                    } else {
                        imageClickListener.onExpandChildren(itemData);
                        itemData.setExpand(true);
                        rotationExpandIcon(closeDegree(), openDegree());
                        List<ExpandableItemData> children = itemData.getChildren();
                        if (children != null) {
                            count.setText(String.format("(%s)", children.size()));
                            onParentItemClick(children.size() + "");
                        }
                        count.setVisibility(View.VISIBLE);

                    }

                }

            }
        });
        image.setOnLongClickListener(new OnLongClickListener() {

            @Override
            public boolean onLongClick(View view) {
                Toast.makeText(view.getContext(), "longclick",
                        Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }

    @Override
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public void rotationExpandIcon(float from, float to) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            ValueAnimator valueAnimator = ValueAnimator.ofFloat(from, to);
            valueAnimator.setDuration(150);
            valueAnimator.setInterpolator(new DecelerateInterpolator());
            valueAnimator.addUpdateListener(new AnimatorUpdateListener() {

                @Override
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    expand.setRotation((Float) valueAnimator.getAnimatedValue());
                }
            });
            valueAnimator.start();
        }
    }

    @Override
    public void onParentItemClick(String path) {
        Toast.makeText(text.getContext(), "just clickec",
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public int openDegree() {
        return 45;
    }

    @Override
    public int closeDegree() {
        return 0;
    }
}
