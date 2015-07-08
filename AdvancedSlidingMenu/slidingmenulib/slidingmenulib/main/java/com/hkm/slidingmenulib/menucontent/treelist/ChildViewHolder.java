package com.hkm.slidingmenulib.menucontent.treelist;


import android.view.View;
import android.view.View.OnClickListener;

import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.hkm.slidingmenulib.R;
import com.hkm.slidingmenulib.Util.TreeList;
import com.hkm.slidingmenulib.Util.Utils;

/**
 * @Author Zheng Haibo, jjhesk MIT
 * @PersonalWebsite
 * @Description
 */
public class ChildViewHolder extends BaseViewHolder {

    public TextView text;
    public ImageView image;
    public RelativeLayout relativeLayout;
    private int itemMargin;
    private int offsetMargin;

    public ChildViewHolder(View itemView) {
        super(itemView);
        text = (TextView) itemView.findViewById(R.id.text);
        image = (ImageView) itemView.findViewById(R.id.image);
        relativeLayout = (RelativeLayout) itemView.findViewById(R.id.container);
        itemMargin = itemView.getContext().getResources()
                .getDimensionPixelSize(R.dimen.item_margin);
        offsetMargin = itemView.getContext().getResources()
                .getDimensionPixelSize(R.dimen.expand_size);
    }

    public void bindView(final ItemData itemData, int position) {
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) image.getLayoutParams();
        params.leftMargin = itemMargin * itemData.getTreeDepth() + offsetMargin;
        image.setLayoutParams(params);
        text.setText(itemData.getText());
        relativeLayout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                TreeList.openFileInSystem(itemData.getPath(), view.getContext());
            }
        });
    }


}
