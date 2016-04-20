package mxh.kickassmenu.menucontent.sectionPlate.touchItems;

import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.marshalchen.ultimaterecyclerview.UltimateRecyclerviewViewHolder;

import mxh.kickassmenu.R;

/**
 * Created by hesk on 15/2/16.
 */
public class RowBinder extends UltimateRecyclerviewViewHolder {
    public TextView text;
    public RelativeLayout section_relative_layout;

    public RowBinder(View itemView) {
        super(itemView);
        text = (TextView) itemView.findViewById(R.id.section_text);
        section_relative_layout = (RelativeLayout) itemView.findViewById(R.id.section_relative_layout);
    }

    public void setOnSelectItem(final int position) {
        section_relative_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("TOUCH", "touch on position:" + position);
            }
        });
    }
}
