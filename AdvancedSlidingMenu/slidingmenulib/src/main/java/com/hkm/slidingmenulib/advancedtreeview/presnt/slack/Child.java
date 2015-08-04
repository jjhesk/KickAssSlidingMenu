package com.hkm.slidingmenulib.advancedtreeview.presnt.slack;

import android.view.View;
import android.widget.TextView;

import com.hkm.slidingmenulib.R;
import com.hkm.slidingmenulib.advancedtreeview.ExpandableItemData;
import com.hkm.slidingmenulib.advancedtreeview.customizationbase.child;
import com.hkm.slidingmenulib.menucontent.containers.MaterialRippleLayout;

/**
 * Created by hesk on 10/7/15.
 */
public class Child<T extends ExpandableItemData,TE extends TextView> extends child<T> {
    public TE text;
    public MaterialRippleLayout relativeLayout;
    private int offsetMargin, itemMargin;
    private boolean capitalized = false;

    public Child(View itemView) {
        super(itemView);
        text = (TE) itemView.findViewById(R.id.exp_section_title);
        relativeLayout = (MaterialRippleLayout) itemView.findViewById(R.id.exp_section_ripple_wrapper_click);
        itemMargin = itemView.getContext().getResources()
                .getDimensionPixelSize(R.dimen.item_margin);
        offsetMargin = itemView.getContext().getResources()
                .getDimensionPixelSize(R.dimen.expand_size);
    }

    protected void forceTitleCapitalized(boolean b) {
        capitalized = b;
    }

    @Override
    public void bindView(final T itemData, int position) {


        if (capitalized) {
            text.setText(itemData.getText().toUpperCase());
        } else {
            text.setText(itemData.getText());
        }

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



}
