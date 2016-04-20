package mxh.kickassmenu.menucontent.sectionPlate.touchItems;

import android.annotation.SuppressLint;
import android.support.annotation.DrawableRes;
import android.view.View;
import android.widget.TextView;

import mxh.kickassmenu.R;


/**
 * Created by hesk on 24/6/15.
 */
public class MaterialSectionNotificationBind<CustomTextView extends TextView> extends MaterialSectionBind {
    public CustomTextView notificationText;

    public MaterialSectionNotificationBind() {
        super();
    }

    public MaterialSectionNotificationBind(@DrawableRes int icon) {
        super(icon);
    }

    @SuppressLint("WrongViewCast")
    public View init(View view) {
        super.init(view);
        notificationText = (CustomTextView) view.findViewById(R.id.section_notification);
        return view;
    }

    @Override
    public int getLayout() {
        return withIcon() ? R.layout.section_icon : R.layout.section_material;
    }

    public void setNotificationStyle() {

    }

    public void setNumber(int notifications) {
        String textNotification;
        textNotification = String.valueOf(notifications);
        if (notifications < 1) {
            textNotification = "";
        }
        if (notifications > 99) {
            textNotification = "99+";
        }
        notificationText.setText(textNotification);
    }
}
