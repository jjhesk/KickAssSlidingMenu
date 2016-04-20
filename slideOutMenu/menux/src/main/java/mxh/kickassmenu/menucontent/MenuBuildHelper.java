package mxh.kickassmenu.menucontent;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.DrawableRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.StringRes;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;


import mxh.kickassmenu.R;
import mxh.kickassmenu.Util.Utils;
import mxh.kickassmenu.menucontent.containers.MaterialDrawerContainer;
import mxh.kickassmenu.menucontent.sectionPlate.MaterialDevisor;
import mxh.kickassmenu.menucontent.sectionPlate.MaterialImageLabel;
import mxh.kickassmenu.menucontent.sectionPlate.MaterialLabel;
import mxh.kickassmenu.menucontent.sectionPlate.MaterialSection;
import mxh.kickassmenu.menucontent.sectionPlate.touchItems.MaterialListSection;
import mxh.kickassmenu.menucontent.sectionPlate.touchItems.MaterialSectionBind;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by hesk on 23/6/15.
 * inspired from material menu drawer
 */
public class MenuBuildHelper<Fragment, customTextView extends TextView, ListSection extends MaterialListSection> {
    public static String TAG = "txtNavigation";
    private Context activity;
    private MaterialSectionChangeListener changeListener;
    private MaterialSectionOnClickListener clickListener;
    private internalChangeInFragment fragmentChangeListener;
    private MaterialMenu menu;
    private LinearLayout itemMenuHolder, items;
    private MaterialDrawerContainer binder;
    private int
            backPattern,
            drawerHeaderType,
            headerDPHeight,
            drawerDPWidth,
            animationTransition,
            dividerStrokeThickness;
    private float displayDensity;
    private boolean
            kitkatTraslucentStatusbar,
            slidingDrawerEffect,
            drawerTouchLocked,
            loadFragmentOnStart;
    private List<MaterialSection> sectionLastBackPatternList;

    // global vars menu
    private MaterialSection currentSection;

    // global vars view menu

    public MenuBuildHelper(Context cont) {
        this.activity = cont;
    }

    public static MenuBuildHelper with(Context cc) {
        return new MenuBuildHelper(cc);
    }

    public void setNaviBackPattern(final int n) {
        backPattern = n;
    }

    public MenuBuildHelper andBinder(MaterialDrawerContainer viewBinder) {
        this.binder = viewBinder;
        return this;
    }

    public void createNewMenu() {
        initLayouts(this.binder);
    }

    /**
     * @param fragment_menu the activity should have classes implemented
     * @param inflat        the view
     * @throws Exception the exception
     */
    public void createNewMenu(Fragment fragment_menu, View inflat) throws Exception {
        if (fragment_menu instanceof android.app.Fragment) {
            //  this.activity = ((android.app.Fragment) fragment_menu).getActivity();
        } else if (fragment_menu instanceof android.support.v4.app.Fragment) {
            // this.activity = ((android.support.v4.app.Fragment) fragment_menu).getActivity();
        } else {
            throw new Exception("cannot found the fragment to apply, please double check with the input type");
        }

    }

    public MaterialMenu getMENU() {
        return menu;
    }

    public MaterialMenu newMENU() {
        return menu = new MaterialMenu();
    }

    public MenuBuildHelper setChangeClickListener(MaterialSectionChangeListener l1, MaterialSectionOnClickListener l2) {
        try {
            this.changeListener = l1;
            this.clickListener = l2;
        } catch (Exception e) {
            Log.d(TAG, "failed to cast type into listeners");
        }
        return this;
    }

    private MaterialDrawerContainer mcontainerdrawer;

    private void initLayouts(MaterialDrawerContainer binder) {
        animationTransition = 500;
        loadFragmentOnStart = true;
        drawerTouchLocked = false;
        slidingDrawerEffect = true;
        kitkatTraslucentStatusbar = false;
        drawerDPWidth = 0; // 0 not set, will get calculated
        headerDPHeight = 0;
        drawerHeaderType = 0; // default type is headItem, but will get overridden
        backPattern = materialMenuConstructorFragmentBase.BACKPATTERN_BACK_ANYWHERE; // default back button option
        sectionLastBackPatternList = new ArrayList<>();
        //get resources and displayDensity
        displayDensity = activity.getResources().getDisplayMetrics().density;

        final int statusBarHeight;
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT || (Build.VERSION.SDK_INT == Build.VERSION_CODES.KITKAT && !kitkatTraslucentStatusbar)) {
            statusBarHeight = (int) (25 * displayDensity);
        } else {
            statusBarHeight = 0;
        }

        this.itemMenuHolder = binder.itemMenuHolder;
        this.items = binder.items;
        renderMenu();
    }

    public void reloadMenu() {
        loadFragmentAutomatic(true);
        if (null != currentSection)
            currentSection.select();
    }

    public class setRemoteFragment {

    }

    private void loadFragmentAutomatic(boolean fromStart) {
        int startIndex = menu.getStartIndex();
        List<Object> sectionList = menu.getItems();
        if (startIndex == -1)
            fromStart = true;


        if (!fromStart && ((drawerHeaderType == materialMenuConstructorFragmentBase.DRAWERHEADER_HEADITEMS &&
                sectionList.get(startIndex) instanceof MaterialSection) || (drawerHeaderType != materialMenuConstructorFragmentBase.DRAWERHEADER_HEADITEMS))) {
            final MaterialSection newSection = (MaterialSection) sectionList.get(startIndex);
            if ((newSection.getTarget() == MaterialSection.TARGET_FRAGMENT)) {
                currentSection = newSection;
                currentSection.select();
                if (fragmentChangeListener != null) {
                    fragmentChangeListener.setinternalChange(currentSection.getTargetFragment(), currentSection.getFragmentTitle());
                }

                //  changeToolbarColor(currentSection);
            } else {
                throw new RuntimeException("StartIndex should selected a section with a fragment");
            }

        } else if (!fromStart && drawerHeaderType != materialMenuConstructorFragmentBase.DRAWERHEADER_HEADITEMS) {

            final MaterialSection newSection = (MaterialSection) sectionList.get(startIndex);

            if ((newSection.getTarget() == MaterialSection.TARGET_FRAGMENT)) {
                currentSection = newSection;
                currentSection.select();
                if (fragmentChangeListener != null) {
                    fragmentChangeListener.setinternalChange(currentSection.getTargetFragment(), currentSection.getTitle(), null, false);
                }
                //  changeToolbarColor(currentSection);
            } else {
                throw new RuntimeException("StartIndex should selected a section with a fragment");
            }

        } else if (fromStart) {

            for (int i = 0; i < sectionList.size(); i++) {
                if (sectionList.get(i) instanceof MaterialSection) {
                    currentSection = (MaterialSection) sectionList.get(i);
                    if ((currentSection.getTarget() == MaterialSection.TARGET_FRAGMENT)) {
                        currentSection.select();
                        if (fragmentChangeListener != null) {
                            fragmentChangeListener.setinternalChange(currentSection.getTargetFragment(), currentSection.getFragmentTitle(), null, false);
                        }
                        break;
                    }
                }
            }
        }
    }

    private LinearLayout getContainer(final boolean isBottom) {
        return isBottom ? itemMenuHolder : items;
    }

    private void renderMenu() {
        // remove all section views
        this.items.removeAllViews();
        this.itemMenuHolder.removeAllViews();
        // show current menu
        List<Object> sectionList = menu.getItems();
        for (int i = 0; i < sectionList.size(); i++) {
            if (sectionList.get(i) instanceof MaterialSection) {
                final MaterialSection section = (MaterialSection) sectionList.get(i);
                addSection(section, getContainer(section.isBottom()));
            } else if (sectionList.get(i) instanceof MaterialDevisor) {
                final MaterialDevisor devisor = (MaterialDevisor) sectionList.get(i);
                addDevisor(devisor, getContainer(devisor.isBottom()));
            } else if (sectionList.get(i) instanceof MaterialLabel) {
                final MaterialLabel label = (MaterialLabel) sectionList.get(i);
                addLabel(label, getContainer(label.isBottom()));
            } else if (sectionList.get(i) instanceof MaterialImageLabel) {
                final MaterialImageLabel imagelabel = (MaterialImageLabel) sectionList.get(i);
                addLabelImage(imagelabel, getContainer(
                        imagelabel.isBottom()
                ));
            }
        }


        // unselect all items
        for (int i = 0; i < sectionList.size(); i++) {
            if (sectionList.get(i) instanceof MaterialSection) {
                final MaterialSection section = (MaterialSection) sectionList.get(i);
                section.unSelect();
            }
        }

        itemMenuHolder.requestLayout();
    }

    private int customLayoutR = -1;

    public void swapCustomLayout(final @LayoutRes int layoutID) {
        customLayoutR = layoutID;
    }

    /**
     * adding the label
     *
     * @param label the material label
     */
    private void addLabel(MaterialLabel label, LinearLayout location) {
        location.addView(label.getView(), Utils.genVerticalLayoutParam(48, displayDensity));
    }

    private void addLabelImage(MaterialImageLabel section, LinearLayout location) {
        location.addView(section.getView(), Utils.genVerticalLayoutParam(section.getHeight(displayDensity)));
    }

    private void addSection(MaterialSection section, LinearLayout location) {
        location.addView(section.getView(), Utils.genVerticalLayoutParam(48, displayDensity));
    }

    private void addDevisor(MaterialDevisor md, LinearLayout location) {
        location.addView(md.init(activity, Utils.genVerticalLayoutParam(md.getHeight(displayDensity))));
    }

    /**
     * adding and finalize the section object
     *
     * @param section the pointer from section material
     */
    private void additionalSection(final MaterialSection section) {
        if (customLayoutR > -1) {
            section.swapLayout(customLayoutR);
        }
        section.build(activity);
    }


    /**
     * create section for the headItem changer menu
     *
     * @param title       AKA
     * @param icon        AKA
     * @param menu        AKA
     * @param position    AKA
     * @param refreshMenu AKA
     * @return AKA
     */
    private MaterialSection newSectionInternal(String title, Drawable icon, MaterialMenu menu, int position, boolean refreshMenu) {
        MaterialSection section = new MaterialSection(MaterialSection.TARGET_CLICK, false, changeListener, new MaterialSectionBind<customTextView>());
        additionalSection(section);
        section.setFillIconColor(false);
        section.setIcon(icon);
        section.setTitle(title);
        //section.setPosition(position);
        menu.addItem(section, position);
        if (refreshMenu)
            reloadMenu();

        return section;
    }


    public MaterialLabel newLabel(String label, boolean bottom, MaterialMenu menu, int position, boolean refreshMenu) {
        MaterialLabel labelM = new MaterialLabel(activity, label, bottom);
        labelM.init(getinflatedView(labelM.getLayout()));
        menu.addItem(labelM, position);
        if (refreshMenu)
            reloadMenu();

        return labelM;
    }

    public MaterialLabel newLabel(String label, boolean bottom, MaterialMenu menu, int position) {
        return newLabel(label, bottom, menu, position, false);
    }


    public MaterialDevisor newDevisor(MaterialMenu menu, int position, boolean refreshMenu) {
        MaterialDevisor devisor = new MaterialDevisor();
        menu.addItem(devisor, position);
        if (refreshMenu)
            reloadMenu();
        return devisor;
    }

    public MaterialDevisor newDevisor(MaterialMenu menu, int position) {
        return newDevisor(menu, position, false);
    }

    public MaterialDevisor newDevisor(MaterialMenu menu, boolean refreshMenu) {
        return newDevisor(menu, menu.getItems().size(), refreshMenu);
    }

    public MaterialDevisor DividerSmall(MaterialMenu meu) {
        MaterialDevisor d = new MaterialDevisor();
        meu.addItem(d, meu.getItems().size());
        return d;
    }


    public MaterialDevisor DividerSmallSticky(MaterialMenu meu) {
        MaterialDevisor d = new MaterialDevisor(true);
        meu.addItem(d, meu.getItems().size());
        return d;
    }

    public MaterialDevisor DividerBig(MaterialMenu meu) {
        MaterialDevisor d = new MaterialDevisor(2, false);
        meu.addItem(d, meu.getItems().size());
        return d;
    }


    public MaterialDevisor DividerBigSticky(MaterialMenu meu) {
        MaterialDevisor d = new MaterialDevisor(2, true);
        meu.addItem(d, meu.getItems().size());
        return d;
    }

    public MaterialDevisor newDevisor(MaterialMenu menu) {
        return newDevisor(menu, menu.getItems().size());
    }

    private View getinflatedView(@LayoutRes int layout) {
        return LayoutInflater.from(activity).inflate(layout, null);
    }

    // create items for a headItem
    public MaterialSection newSection(String title, Drawable icon, boolean bottom, MaterialMenu menu, int position, boolean refreshMenu) {
        MaterialSection section = new MaterialSection(MaterialSection.TARGET_CLICK, true, changeListener, new MaterialSectionBind<customTextView>());
        additionalSection(section);
        section.setIcon(icon);
        section.setTitle(title);
        section.setOnClickListener(clickListener);
        //section.setPosition(position);
        menu.addItem(section, position);
        if (refreshMenu)
            reloadMenu();

        return section;
    }

    public MaterialSection newSection(final String title, final Drawable icon, final boolean bottom, MaterialMenu menu, final int position) {
        return newSection(title, icon, bottom, menu, position, false);
    }

    public MaterialSection newSection(final String title, final Drawable icon, final boolean bottom, MaterialMenu menu, boolean refreshMenu) {
        return newSection(title, icon, bottom, menu, menu.getItems().size(), refreshMenu);
    }

    public MaterialSection newSection(final String title, final Drawable icon, final boolean bottom, final MaterialMenu menu) {
        return newSection(title, icon, bottom, menu, menu.getItems().size());
    }

    public MaterialImageLabel newImageLabel(final String title, final @DrawableRes int image, final MaterialMenu menu) {
        return niLInternal(title, image, false, menu, menu.getItems().size());
    }

    public MaterialImageLabel newImageLabelSticky(final String title, final @DrawableRes int image, final MaterialMenu menu) {
        return niLInternal(title, image, true, menu, menu.getItems().size());
    }

    private MaterialImageLabel niLInternal(final String title, final @DrawableRes int image,
                                           final boolean bottom, final MaterialMenu menu, final int position) {
        MaterialImageLabel lb = new MaterialImageLabel(activity, image, bottom);
        lb.init(getinflatedView(lb.getLayout()));
        menu.addItem(lb, position);
        return lb;
    }

    public MaterialSection newListSection(final @StringRes int title, ListSection list, MaterialMenu menu) {
        return newListSectionInternal(activity.getResources().getString(title), list, menu.getItems().size(), menu);
    }

    public MaterialSection newListSection(String title, ListSection list, MaterialMenu menu) {
        return newListSectionInternal(title, list, menu.getItems().size(), menu);
    }

    private MaterialSection newListSectionInternal(final String title, final ListSection sectionList, int position, final MaterialMenu menu) {
        final MaterialSection sec = new MaterialSection(
                MaterialSection.TARGET_LISTVIEW,
                false, changeListener,
                sectionList);
        sectionList.setDensity(displayDensity);
        sectionList.setScrollContainer(binder.getScrollView());
        additionalSection(sec);
        sec.setFillIconColor(false);
        sec.setTitle(title);
        sec.setOnClickListener(clickListener);
        menu.addItem(sec, position);
        return sec;
    }

    public MaterialSection newSectionSticky(final String res, Fragment target, final MaterialMenu menu) {
        MaterialSection sec = new MaterialSection<Fragment, MaterialSectionBind>(MaterialSection.TARGET_FRAGMENT,
                true, changeListener, new MaterialSectionBind<customTextView>());
        additionalSection(sec);
        sec.setOnClickListener(clickListener);
        sec.setTitle(res);
        sec.setTarget(target);
        menu.addItem(sec, menu.getItems().size());
        return sec;
    }

    public MaterialSection newSectionSticky(final @StringRes int res, Fragment target, final MaterialMenu menu) {
        MaterialSection sec = new MaterialSection<Fragment, MaterialSectionBind>(MaterialSection.TARGET_FRAGMENT,
                true, changeListener, new MaterialSectionBind<customTextView>());
        additionalSection(sec);
        sec.setOnClickListener(clickListener);
        sec.setTitle(activity.getResources().getString(res));
        sec.setTarget(target);
        menu.addItem(sec, menu.getItems().size());
        return sec;
    }

    public MaterialSection newSectionSticky(final @StringRes int res, Intent target, final MaterialMenu menu) {
        MaterialSection sec = new MaterialSection<Fragment, MaterialSectionBind>(MaterialSection.TARGET_ACTIVITY,
                true, changeListener, new MaterialSectionBind<customTextView>());
        additionalSection(sec);
        sec.setOnClickListener(clickListener);
        sec.setTitle(activity.getResources().getString(res));
        sec.setTarget(target);
        menu.addItem(sec, menu.getItems().size());
        return sec;
    }

    /*
        public MaterialSection ImagebuttonSection(String title, Drawable icon, Fragment target, boolean bottom, MaterialMenu menu, int position, boolean refreshMenu, boolean withbanner) {
            MaterialSection section = new MaterialSection(MaterialSection.TARGET_FRAGMENT, bottom, changeListener, new MaterialImageButton());
            section.setOnClickListener(clickListener);
            section.setIcon(icon);
            section.setTitle(title);
            section.setTarget(target);
            //section.setPosition(position);
            menu.addItem(section, position);
            additionalSection(section);
            if (refreshMenu)
                reloadMenu();

            return section;
        }

        public MaterialSection newSection(String title, Drawable icon, Fragment target, boolean bottom, MaterialMenu menu, int position) {
            return ImagebuttonSection(title, icon, target, bottom, menu, position, false, false);
        }

        public MaterialSection newSection(String title, Drawable icon, Fragment target, boolean bottom, MaterialMenu menu, boolean refreshMenu) {
            return ImagebuttonSection(title, icon, target, bottom, menu, menu.getItems().size(), refreshMenu, false);
        }

        public MaterialSection newSection(String title, Drawable icon, Fragment target, boolean bottom, MaterialMenu menu) {
            return newSection(title, icon, target, bottom, menu, menu.getItems().size());
        }
           public MaterialSection newSectionBanner(String title, Drawable icon, Fragment fragment, MaterialMenu menu) {
            return ImagebuttonSection(title, icon, fragment, false, menu, menu.getItems().size(), false, true);
        }

        public MaterialSection newImageLabel(String title, Drawable icon, Fragment fragment, MaterialMenu menu) {
            return ImagebuttonSection(title, icon, fragment, false, menu, menu.getItems().size(), false, true);
        }

        */
    private MaterialSection newSectionInternal(final String title, final Drawable icon, final Intent target, final boolean bottom, final MaterialMenu menu, final int position, final boolean refreshMenu) {
        MaterialSection section = new MaterialSection<Fragment, MaterialSectionBind>(MaterialSection.TARGET_ACTIVITY, bottom, changeListener, new MaterialSectionBind<customTextView>());
        additionalSection(section);
        section.setOnClickListener(clickListener);
        section.setIcon(icon);
        section.setTitle(title);
        section.setTarget(target);
        //section.setPosition(position);
        menu.addItem(section, position);
        if (refreshMenu)
            reloadMenu();
        return section;
    }


    public MaterialSection newSection(final String title, final Drawable icon, final Intent target, final boolean bottom, final MaterialMenu menu, final int position) {
        return newSectionInternal(title, icon, target, bottom, menu, position, false);
    }

    public MaterialSection newSection(final String title, final Drawable icon, final Intent target, final boolean bottom, final MaterialMenu menu, final boolean refreshMenu) {
        return newSectionInternal(title, icon, target, bottom, menu, menu.getItems().size(), refreshMenu);
    }

    public MaterialSection newSection(final String title, final Drawable icon, final Intent target, final boolean bottom, final MaterialMenu menu) {
        return newSection(title, icon, target, bottom, menu, menu.getItems().size());
    }


    /**
     * the section with ICON
     *
     * @param title       AKA
     * @param icon        AKA
     * @param bottom      AKA
     * @param menu        AKA
     * @param position    AKA
     * @param refreshMenu AKA
     * @return AKA
     */
    private MaterialSection newSectionInternal(final String title, final Bitmap icon, final boolean bottom, final MaterialMenu menu, final int position, final boolean refreshMenu) {
        MaterialSection section = new MaterialSection(MaterialSection.TARGET_CLICK, bottom, changeListener, new MaterialSectionBind<customTextView>());
        additionalSection(section);
        section.setIcon(icon);
        section.setTitle(title);
        menu.addItem(section, position);
        if (refreshMenu)
            reloadMenu();
        return section;
    }

    public MaterialSection newSection(String title, Bitmap icon, boolean bottom, MaterialMenu menu, int position) {
        return newSectionInternal(title, icon, bottom, menu, position, false);
    }

    public MaterialSection newSection(String title, Bitmap icon, boolean bottom, MaterialMenu menu, boolean refreshMenu) {
        return newSectionInternal(title, icon, bottom, menu, menu.getItems().size(), refreshMenu);
    }

    public MaterialSection newSection(String title, Bitmap icon, boolean bottom, MaterialMenu menu) {
        return newSection(title, icon, bottom, menu, menu.getItems().size());
    }


    /**
     * @param title       AKA
     * @param icon        AKA
     * @param target      AKA
     * @param bottom      AKA
     * @param menu        AKA
     * @param position    AKA
     * @param refreshMenu AKA
     * @return AKA
     */
    public MaterialSection newSection(final String title, final Bitmap icon, final Fragment target,
                                      final boolean bottom, final MaterialMenu menu, final int position,
                                      final boolean refreshMenu) {

        MaterialSection section = new MaterialSection(MaterialSection.TARGET_FRAGMENT, bottom, changeListener, new MaterialSectionBind<customTextView>());

        section.setOnClickListener(clickListener);
        section.setIcon(icon);
        section.setTitle(title);
        section.setTarget(target);
        //section.setPosition(menu.getItems().size());
        menu.addItem(section, position);

        if (refreshMenu)
            reloadMenu();

        return section;
    }


    public MaterialSection newSection(String title, Bitmap icon, Fragment target, boolean bottom, MaterialMenu menu, int position) {
        return newSection(title, icon, target, bottom, menu, position, false);
    }

    public MaterialSection newSection(String title, Bitmap icon, Fragment target, boolean bottom, MaterialMenu menu, boolean refreshMenu) {
        return newSection(title, icon, target, bottom, menu, menu.getItems().size(), refreshMenu);
    }

    public MaterialSection newSection(String title, Bitmap icon, Fragment target, boolean bottom, MaterialMenu menu) {
        return newSection(title, icon, target, bottom, menu, menu.getItems().size());
    }


    /**
     * @param title       AKA
     * @param icon        AKA
     * @param target      AKA
     * @param bottom      AKA
     * @param menu        AKA
     * @param position    AKA
     * @param refreshMenu AKA
     * @return AKA
     */
    public MaterialSection newSection(final String title, final Bitmap icon, final Intent target, final boolean bottom, final MaterialMenu menu, final int position, final boolean refreshMenu) {
        MaterialSection section = new MaterialSection(MaterialSection.TARGET_ACTIVITY, bottom, changeListener, new MaterialSectionBind<customTextView>());
        section.setOnClickListener(clickListener);
        section.setIcon(icon);
        section.setTitle(title);
        section.setTarget(target);
        //section.setPosition(menu.getItems().size());
        menu.addItem(section, position);
        if (refreshMenu)
            reloadMenu();

        return section;
    }

    public MaterialSection newSection(String title, Bitmap icon, Intent target, boolean bottom, MaterialMenu menu, int position) {
        return newSection(title, icon, target, bottom, menu, position, false);
    }

    public MaterialSection newSection(String title, Bitmap icon, Intent target, boolean bottom, MaterialMenu menu, boolean refreshMenu) {
        return newSection(title, icon, target, bottom, menu, menu.getItems().size(), refreshMenu);
    }

    public MaterialSection newSection(String title, Bitmap icon, Intent target, boolean bottom, MaterialMenu menu) {
        return newSection(title, icon, target, bottom, menu, menu.getItems().size(), false);
    }


    /**
     * @param title       AKA
     * @param bottom      AKA
     * @param menu        AKA
     * @param position    AKA
     * @param refreshMenu AKA
     * @return AKA
     */
    public MaterialSection newSection(String title, boolean bottom, MaterialMenu menu, int position, boolean refreshMenu) {

        MaterialSection section = new MaterialSection(MaterialSection.TARGET_CLICK, bottom, changeListener, new MaterialSectionBind<customTextView>());
        section.setTitle(title);
        //section.setPosition(menu.getItems().size());
        menu.addItem(section, position);
        if (refreshMenu)
            reloadMenu();

        return section;
    }


    /**
     * this is the root call
     *
     * @param title       fragment title
     * @param target      the fragment
     * @param bottom      is on the bottom
     * @param menu        the main menu
     * @param position    the position on the menu
     * @param refreshMenu will refresh the menu on display
     * @return the object of the MaterialSection
     */
    protected MaterialSection newSection(final String title, final Fragment target, final boolean bottom, MaterialMenu menu, final int position, final boolean refreshMenu) {
        MaterialSection section = new MaterialSection(MaterialSection.TARGET_FRAGMENT, false, changeListener, new MaterialSectionBind<customTextView>());
        additionalSection(section);
        section.setTitle(title);
        section.setTarget(target);
        section.setOnClickListener(clickListener);
        //section.setPosition(menu.getItems().size());
        menu.addItem(section, position);
        if (refreshMenu)
            reloadMenu();
        return section;
    }


    public MaterialSection newSection(final @StringRes int title, final Fragment target, final MaterialMenu menu) {
        final String mtitle = activity.getResources().getString(title);
        return newSection(mtitle, target, false, menu);
    }

    public MaterialSection newSection(String title, Fragment target, MaterialMenu menu) {
        return newSection(title, target, false, menu);
    }

    public MaterialSection newSection(String title, Fragment target, boolean bottom, MaterialMenu menu, int position) {
        return newSection(title, target, bottom, menu, position, false);
    }

    public MaterialSection newSection(String title, Fragment target, boolean bottom, MaterialMenu menu, boolean refreshMenu) {
        return newSection(title, target, bottom, menu, menu.getItems().size(), refreshMenu);
    }

    public MaterialSection newSection(String title, Fragment target, boolean bottom, MaterialMenu menu) {
        return newSection(title, target, bottom, menu, menu.getItems().size());
    }


    /**
     * @param title        AKA
     * @param fragment     AKA
     * @param DrawableIcon AKA
     * @param menu         AKA
     * @param bottom       the bottom part
     * @return materialsection
     */

    @SuppressLint("ResourceAsDrawable")
    protected MaterialSection newIconSection(final String title, final Fragment fragment, final @DrawableRes int DrawableIcon, final MaterialMenu menu, final boolean bottom) {
        //Drawable icon = activity.getResources().getDrawable(DrawableIcon);
        MaterialSection section = new MaterialSection(MaterialSection.TARGET_CLICK, bottom, changeListener, new MaterialSectionBind<customTextView>(DrawableIcon));
        section.setOnClickListener(clickListener);
        section.setFillIconColor(false);
        // section.setIcon(icon);
        section.setTitle(title);
        section.setTarget(fragment);
        //section.setPosition(position);
        menu.addItem(section, menu.getItems().size());
        additionalSection(section);
        return section;
    }


    public int getBackPattern() {
        return backPattern;
    }
}
