package mxh.kickassmenu.menucontent.sectionPlate;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.LayoutRes;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import mxh.kickassmenu.R;
import mxh.kickassmenu.menucontent.ImaterialBinder;
import mxh.kickassmenu.menucontent.MaterialSectionChangeListener;
import mxh.kickassmenu.menucontent.MaterialSectionImpleLayout;
import mxh.kickassmenu.menucontent.MaterialSectionOnClickListener;
import mxh.kickassmenu.menucontent.containers.MaterialPlain;
import mxh.kickassmenu.menucontent.containers.MaterialRippleLayout;
import mxh.kickassmenu.menucontent.containers.MaterialRippleLayoutNineOld;
import mxh.kickassmenu.menucontent.sectionPlate.touchItems.MaterialListSection;
import mxh.kickassmenu.menucontent.sectionPlate.touchItems.MaterialSectionBind;
import mxh.kickassmenu.menucontent.sectionPlate.touchItems.MaterialSectionNotificationBind;


public class MaterialSection<Fragment, SectionModel extends ImaterialBinder> implements View.OnTouchListener, View.OnClickListener, MaterialSectionImpleLayout {
    public static final String TAG = "materialsectionnow";
    public static final int TARGET_FRAGMENT = 0;
    public static final int TARGET_ACTIVITY = 1;
    public static final int TARGET_CLICK = 2;
    public static final int TARGET_LISTVIEW = 3;
    public static final int TARGET_EMBEDED_CHILDFRAGMENT = 4;

    private View view;
    private ImageView icon;
    private MaterialSectionOnClickListener listener;
    private MaterialSectionChangeListener changeListener;

    private boolean isSelected;
    private int targetType;
    private int sectionColor;
    private boolean fillIconColor;

    private boolean sticky;
    private boolean sectiondivided;
    private boolean hasSectionColor;
    private boolean hasColorDark;
    //private int colorPressed;
    private int colorUnpressed;
    private int colorSelected;
    private int colorOnPressHighlight;
    private int iconColor;
    private int colorDark;
    private int textColor;
    private int notificationColor;
    private int layout_item_section = -1;

    private int numberNotifications;

    private String title;
    private String fragmentTitle;
    private final SectionModel mSectionmodel;
    private Fragment targetFragment;
    private Intent targetIntent;

    private boolean hasIcon = false;

    private Context ctx;

    public MaterialSection(final int target, final boolean sticky, final
    MaterialSectionChangeListener changeListener, final SectionModel mSectionmodel) {
        this.targetType = target;
        this.sticky = sticky;
        this.changeListener = changeListener;
        this.mSectionmodel = mSectionmodel;
        isSelected = false;
        hasSectionColor = false;
        hasColorDark = false;
        numberNotifications = 0;
        fillIconColor = true;
    }

    private int getItemLayout(final TypedArray values, final @LayoutRes int defaultResId) {
        int resId = values.getResourceId(R.styleable.MaterialSection_section_item_layout, -1);
        if (resId == -1 || layout_item_section > -1) {
            return defaultResId;
        } else {
            return resId;
        }
    }

    private View getView(final Context c, final TypedArray arr, final @LayoutRes int rDefault) {
        if (mSectionmodel instanceof MaterialListSection) {
            return LayoutInflater.from(c).inflate(rDefault, null);
        } else {
            return LayoutInflater.from(c).inflate(getItemLayout(arr, rDefault), null);
        }
    }

    /**
     * for custom user layout swapping
     *
     * @param layout_custom the resource id from the layout
     */
    public void swapLayout(final @LayoutRes int layout_custom) {
        layout_item_section = layout_custom;
    }

    private void RippleCustomization(final TypedArray values, final View view) {

        if (view.findViewById(R.id.section_ripple) != null) {
            int rippleColor = values.getColor(R.styleable.MaterialSection_sectionRippleColor, 0x16000000);
            if (view.findViewById(R.id.section_ripple) instanceof MaterialRippleLayout) {
                MaterialRippleLayout ilayout = (MaterialRippleLayout) view.findViewById(R.id.section_ripple);
                ilayout.setRippleColor(rippleColor);
                ilayout.setOnClickListener(this);
                ilayout.setOnTouchListener(this);
            } else if (view.findViewById(R.id.section_ripple) instanceof MaterialRippleLayoutNineOld) {
                MaterialRippleLayoutNineOld ilayout = (MaterialRippleLayoutNineOld) view.findViewById(R.id.section_ripple);
                ilayout.setRippleColor(rippleColor);
                ilayout.setOnClickListener(this);
                ilayout.setOnTouchListener(this);
            } else if (view.findViewById(R.id.section_ripple) instanceof MaterialPlain) {
                MaterialPlain ilayout = (MaterialPlain) view.findViewById(R.id.section_ripple);
                ilayout.setOnClickListener(this);
                ilayout.setOnTouchListener(this);
            } else if (view.findViewById(R.id.section_relative_layout) != null && view.findViewById(R.id.section_relative_layout) instanceof RelativeLayout) {
                RelativeLayout ilayout = (RelativeLayout) view.findViewById(R.id.section_relative_layout);
                ilayout.setOnClickListener(this);
                ilayout.setOnTouchListener(this);
            }
        }
        //colorPressed = values.getColor(R.styleable.MaterialSection_sectionBackgroundColorPressed, 0x16000000);
        colorUnpressed = values.getColor(R.styleable.MaterialSection_sectionBackgroundColor, 0x00FFFFFF);
        colorSelected = values.getColor(R.styleable.MaterialSection_sectionBackgroundColorSelected, 0x0A000000);
        colorOnPressHighlight = values.getColor(R.styleable.MaterialSection_sectionOnPressHighLightColor, 0x0A000000);
        iconColor = values.getColor(R.styleable.MaterialSection_sectionColorIcon, 0x000);
        textColor = values.getColor(R.styleable.MaterialSection_sectionColorText, 0x000);
        notificationColor = values.getColor(R.styleable.MaterialSection_sectionColorNotification, 0x000);
        sectiondivided = values.getBoolean(R.styleable.MaterialSection_sectionDivider, false);
    }

    @SuppressLint("WrongViewCast")
    public void build(Context ctx) {
        int currentApiVersion = Build.VERSION.SDK_INT;
        /**
         * theme location
         */
        Resources.Theme theme = ctx.getTheme();
        TypedValue typedValue = new TypedValue();
        theme.resolveAttribute(R.attr.sectionStyle, typedValue, true);
        TypedArray values = theme.obtainStyledAttributes(typedValue.resourceId, R.styleable.MaterialSection);

        /**
         * the title from the fragments
         */
        fragmentTitle = null;
        if (mSectionmodel instanceof MaterialSectionNotificationBind) {
            MaterialSectionNotificationBind pl = (MaterialSectionNotificationBind) mSectionmodel;
            this.view = getView(ctx, values, pl.getLayout());
            RippleCustomization(values, view);
            mSectionmodel.init(view);
            if (textColor != 0x000) {
                pl.text.setTextColor(textColor);
            }
            if (notificationColor != 0x000) {
                pl.notificationText.setTextColor(notificationColor);
            }
        } else if (mSectionmodel instanceof MaterialSectionBind) {
            MaterialSectionBind pl = (MaterialSectionBind) mSectionmodel;
            this.view = getView(ctx, values, pl.getLayout());
            RippleCustomization(values, view);
            mSectionmodel.init(view);
            if (textColor != 0x000) {
                pl.text.setTextColor(textColor);
            }
        } else if (mSectionmodel instanceof MaterialImageButton) {
            MaterialImageButton pl = (MaterialImageButton) mSectionmodel;
            this.view = getView(ctx, values, pl.getLayout());
            RippleCustomization(values, view);
            mSectionmodel.init(view);
        } else if (mSectionmodel instanceof MaterialListSection) {
            MaterialListSection pl = (MaterialListSection) mSectionmodel;
            this.view = getView(ctx, values, pl.getLayout());
            RippleCustomization(values, view);
            pl.setNormalHeight(view.findViewById(R.id.section_ripple).getLayoutParams().height);
            mSectionmodel.init(view);
        }
    }


    private void setTitleText(String textColor) {
        if (mSectionmodel instanceof MaterialSectionBind) {
            MaterialSectionBind pl = (MaterialSectionBind) mSectionmodel;
            pl.text.setText(textColor);
        } else if (mSectionmodel instanceof MaterialListSection) {
            MaterialListSection pl = (MaterialListSection) mSectionmodel;
            pl.text.setText(textColor);
        } else {
            Log.d(TAG, "title text is not working now");
        }
    }


    private void setTextColor(int textColor) {
        if (mSectionmodel instanceof MaterialSectionBind) {
            MaterialSectionBind pl = (MaterialSectionBind) mSectionmodel;
            pl.text.setTextColor(textColor);
        }
    }

    public void select() {
        isSelected = true;
        view.setBackgroundColor(colorSelected);
        if (hasSectionColor) {
            setTextColor(iconColor);
        }
    }

    public void unSelect() {
        isSelected = false;
        view.setBackgroundColor(colorUnpressed);
        if (hasSectionColor) {
            setTextColor(textColor);
        }
    }

    /*public void setPosition(int position) {
        this.position = position;
    }

    public int getPosition() {
        return position;
    }*/

    public void setOnClickListener(final MaterialSectionOnClickListener listener) {
        this.listener = listener;
    }

    public View getView() {
        return view;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        setTitleText(title);
    }

    public void setTarget(Fragment target) {
        this.targetFragment = target;
    }

    public void setTarget(Intent intent) {
        this.targetIntent = intent;
    }

    public int getTarget() {
        return targetType;
    }

    public Fragment getTargetFragment() {
        return targetFragment;
    }

    public Intent getTargetIntent() {
        return targetIntent;
    }

    public boolean hasSectionColorDark() {
        return hasColorDark;
    }

    public int getSectionColorDark() {
        return colorDark;
    }

    public MaterialSection setSectionColor(int color, int colorDark) {
        setSectionColor(color);
        hasColorDark = true;
        this.colorDark = colorDark;
        return this;
    }

    public boolean hasSectionColor() {
        return hasSectionColor;
    }

    public int getSectionColor() {
        return iconColor;
    }

    /**
     * Set the number of notification for this section
     *
     * @param notifications the number of notification active for this section
     * @return this section
     */
    @Deprecated
    public MaterialSection setNotifications(int notifications) {
        if (mSectionmodel instanceof MaterialSectionNotificationBind) {
            numberNotifications = notifications;
            MaterialSectionNotificationBind pl = (MaterialSectionNotificationBind) mSectionmodel;
            pl.setNumber(notifications);
        }
        return this;
    }

    /**
     * planning to remove this and locate this additional child object
     *
     * @return integer
     */
    @Deprecated
    public int getNotifications() {
        return numberNotifications;
    }

    public boolean isBottom() {
        return sticky;
    }

    public void setBottom(boolean sticky) {
        this.sticky = sticky;
    }

    public boolean isFillIconColor() {
        return fillIconColor;
    }

    public void setFillIconColor(boolean enabled_fillIconColor) {
        this.fillIconColor = enabled_fillIconColor;
    }


    public boolean isHasIcon() {
        if (mSectionmodel instanceof MaterialSectionBind) {
            return ((MaterialSectionBind) mSectionmodel).withIcon();
        }
        return false;
    }

    @Override
    public void onClick(View v) {
        if (listener != null) {
            final MaterialSection section = this;
            //view.playSoundEffect(android.view.SoundEffectConstants.CLICK);
            if (mSectionmodel instanceof MaterialListSection) {
                MaterialListSection g = (MaterialListSection) mSectionmodel;
                g.toggleListShown();
                listener.onClick(section, v);
            } else {
                changeListener.onBeforeChangeSection(this);
                listener.onClick(section, v);
                changeListener.onAfterChangeSection(this);
            }
        }
    }

    public String getFragmentTitle() {
        if (fragmentTitle == null || fragmentTitle.length() == 0)
            return title;
        else
            return fragmentTitle;
    }

    public void setFragmentTitle(final String fragmentTitle) {
        this.fragmentTitle = fragmentTitle;
    }

    /**
     * Called when a touch event is dispatched to a view. This allows listeners to
     * get a chance to respond before the target view.
     *
     * @param v     The view the touch event has been dispatched to.
     * @param event The MotionEvent object containing full information about
     *              the event.
     * @return True if the listener has consumed the event, false otherwise.
     */
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int a = event.getActionMasked();
        switch (a) {
            case MotionEvent.ACTION_DOWN:
                view.setBackgroundColor(colorOnPressHighlight);
                return true;
            case MotionEvent.ACTION_CANCEL:
                if (isSelected)
                    view.setBackgroundColor(colorSelected);
                else
                    view.setBackgroundColor(colorUnpressed);
                return true;
            case MotionEvent.ACTION_UP:
                checkrefresh();
                return false;
            default:
                return false;
        }
    }


    public void checkrefresh() {
        if (!isSelected) view.setBackgroundColor(colorUnpressed);
    }


    public TextView getText() {
        if (mSectionmodel instanceof MaterialSectionBind) {
            return ((MaterialSectionBind) mSectionmodel).text;
        }
        return null;
    }

    public ImageView getIcon() {
        if (mSectionmodel instanceof MaterialSectionBind) {
            return ((MaterialSectionBind) mSectionmodel).iconHolder;
        }
        return null;
    }

    public MaterialSection setSectionColor(int color) {
        if (mSectionmodel instanceof MaterialSectionBind) {
            MaterialSectionBind pl = (MaterialSectionBind) mSectionmodel;
            sectionColor = color;
            iconColor = color;
            pl.tintColor(sectionColor);
            hasSectionColor = true;
        }
        return this;
    }

    public void setIcon(final Drawable drawbleicon) {
        if (mSectionmodel instanceof MaterialSectionBind) {
            MaterialSectionBind pl = (MaterialSectionBind) mSectionmodel;
            if (fillIconColor) {
                pl.reConfigIcon(drawbleicon, iconColor);
            } else {
                pl.reConfigIcon(drawbleicon);
            }
        }
    }


    public void setIcon(final Bitmap drawbleicon) {
        if (mSectionmodel instanceof MaterialSectionBind) {
            MaterialSectionBind pl = (MaterialSectionBind) mSectionmodel;
            if (fillIconColor) {
                pl.reConfigIcon(drawbleicon, iconColor);
            } else {
                pl.reConfigIcon(drawbleicon);
            }
        }
    }

    public SectionModel getPlateSection() {
        return mSectionmodel;
    }

}
