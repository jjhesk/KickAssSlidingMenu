package mxh.kickassmenu.menucontent.sectionPlate;

import android.content.Context;
import android.content.res.Resources;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;

import mxh.kickassmenu.R;
import mxh.kickassmenu.menucontent.ImaterialBinder;


/**
 * Created by marc on 03.03.2015.
 */
public class MaterialLabel implements ImaterialBinder {

    private String label;
    private View view;
    private TextView text;
    private boolean bottom;
    private Context ctx;

    public MaterialLabel(Context ctx, String label, boolean bottom) {
        this.label = label;
        this.bottom = bottom;
        this.ctx = ctx;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public View getView() {
        return view;
    }

    public boolean isBottom() {
        return bottom;
    }

    public void setBottom(boolean bottom) {
        this.bottom = bottom;
    }

    public TextView getText() {
        return text;
    }

    @Override
    public int getLayout() {
        return R.layout.layout_material_label;
    }

    @Override
    public View init(View view) {
        text = (TextView) view.findViewById(R.id.section_label);
        Resources.Theme theme = ctx.getTheme();
        TypedValue typedValue = new TypedValue();
        theme.resolveAttribute(R.attr.labelColor, typedValue, true);
        int textColor = typedValue.data;
        text.setTextColor(textColor);
        text.setText(label);
        this.view = view;
        return view;
    }
}
