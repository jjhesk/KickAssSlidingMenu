package com.hkm.slidingmenulib.advancedtreeview.presnt.slack;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hkm.slidingmenulib.R;
import com.hkm.slidingmenulib.Util.TreeList;
import com.hkm.slidingmenulib.advancedtreeview.BaseViewHolder;
import com.hkm.slidingmenulib.advancedtreeview.ChildVH;
import com.hkm.slidingmenulib.advancedtreeview.ExpandableItemData;
import com.hkm.slidingmenulib.menucontent.containers.MaterialRippleLayout;

/**
 * Created by hesk on 10/7/15.
 */
public class Child extends BaseViewHolder implements ChildVH<ExpandableItemData> {
    public TextView text;
    public MaterialRippleLayout relativeLayout;
    private int itemMargin;
    private int offsetMargin;

    public Child(View itemView) {
        super(itemView);
        text = (TextView) itemView.findViewById(R.id.section_text);
        relativeLayout = (MaterialRippleLayout) itemView.findViewById(R.id.container);
        itemMargin = itemView.getContext().getResources()
                .getDimensionPixelSize(R.dimen.item_margin);
        offsetMargin = itemView.getContext().getResources()
                .getDimensionPixelSize(R.dimen.expand_size);
    }

    @Override
    public void bindView(final ExpandableItemData itemData, int position) {
        text.setText(itemData.getText());
        text.setLayoutParams(getParamsLayoutOffset(text, itemData));
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TreeList.openFileInSystem(itemData.getPath(), view.getContext());
                onChildItemClick(itemData.getPath());
            }
        });
    }

    @Override
    public void onChildItemClick(String path) {

    }
}
