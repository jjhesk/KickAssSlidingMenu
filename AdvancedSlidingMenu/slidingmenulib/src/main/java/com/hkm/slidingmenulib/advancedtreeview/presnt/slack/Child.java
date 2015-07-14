package com.hkm.slidingmenulib.advancedtreeview.presnt.slack;

import android.content.Context;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hkm.slidingmenulib.R;
import com.hkm.slidingmenulib.Util.TreeList;
import com.hkm.slidingmenulib.advancedtreeview.BaseViewHolder;
import com.hkm.slidingmenulib.advancedtreeview.ChildVH;
import com.hkm.slidingmenulib.advancedtreeview.ExpandableItemData;
import com.hkm.slidingmenulib.advancedtreeview.customizationbase.child;
import com.hkm.slidingmenulib.menucontent.containers.MaterialRippleLayout;

/**
 * Created by hesk on 10/7/15.
 */
public class Child<T extends ExpandableItemData> extends child<T> {
    public TextView text;
    public MaterialRippleLayout relativeLayout;
    private int offsetMargin, itemMargin;

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
    public void bindView(final T itemData, int position) {
        text.setText(itemData.getText());
        text.setLayoutParams(getParamsLayoutOffset(text, itemData));
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // item = itemData;
                onChildItemClick(itemData.getText(), itemData.getPath());
            }
        });
    }

    @Override
    public void onChildItemClick(String title, String path) {
        String[] v = path.split("/");
        if (v.length > 1) {
            request_api(v, title);
        }
    }

    protected void request_api(final String[] n, final String title) {

    }


    @Override
    protected Child getHolder(View view) {
        return new Child(view);
    }

    @Override
    protected int getLayout() {
        return R.layout.exp_1_item_child;
    }

}
