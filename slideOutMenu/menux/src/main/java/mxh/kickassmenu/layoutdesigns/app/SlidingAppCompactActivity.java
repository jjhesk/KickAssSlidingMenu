package mxh.kickassmenu.layoutdesigns.app;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import mxh.kickassmenu.R;
import mxh.kickassmenu.Util.xmlViews.ControlableFrame;
import mxh.kickassmenu.gestured.SlidingMenu;
import mxh.kickassmenu.gestured.app.SlidingAppCompactActivityBase;
import mxh.kickassmenu.menucontent.MenuBuildHelper;
import mxh.kickassmenu.menucontent.internalChangeInFragment;
import mxh.kickassmenu.menucontent.materialMenuConstructorFragmentBase;

/**
 * Created by hesk on 22/6/15.
 */
public abstract class SlidingAppCompactActivity<Frag> extends SlidingAppCompactActivityBase implements internalChangeInFragment<Frag> {
    /**
     * the control frame to display the blocked content or not.
     */

    protected ControlableFrame content_frame;
    protected Frag currentFragmentNow, rightMenuFragment;


    /**
     * get the first menu fragment and this is opened for free developments
     *
     * @return the fragment object
     */
    protected abstract Frag getFirstMenuFragment();

    /**
     * this is the setting point for making all menu slider behavior, please also see from the previous setting library page developed by Jeremyfeinstein @link https://github.com/jfeinstein10/SlidingMenu/blob/master/example/src/com/jeremyfeinstein/slidingmenu/example/BaseActivity.java
     *
     * @param sm the initialized sliding menu object for reconfigurations
     */
    protected abstract void customizeSlideMenuEdge(final SlidingMenu sm);

    /**
     * setting the default main activity layout ID and this is normally had presented in the library and no need change unless there is a customization need for different layout ID
     *
     * @return resource id
     */
    protected int getDefaultMainActivityLayoutId() {
        return BODY_LAYOUT.actionbar.getResID();
    }

    /**
     * @return when @link{getDefaultMainActivityLayoutId} is using user specified layout and such layout contains custom action bar or custom action tool bar then this function must return TRUE to enable the configuration of the tool bar
     */
    protected boolean forceConfigureToolBar() {
        return false;
    }

    /**
     * setting the first initial fragment at the beginning
     *
     * @return generic type fragment
     */
    protected abstract Frag getInitFragment();

    /**
     *
     * the location to setup and configure the toolbar widget under AppCompat V7
     *
     * @param mxToolBarV7 Toolbar object
     */
    protected void configToolBar(final Toolbar mxToolBarV7) {
        mxToolBarV7.setNavigationIcon(R.drawable.ic_action_menu_drawer);
        mxToolBarV7.setTitle(getTitle());
    }

    /**
     * when the fragment is changed now and it will notify the function for user specific operations
     *
     * @param new_fragment_change_now the generic fragment type
     */
    protected void notifyOnBodyFragmentChange(Frag new_fragment_change_now) {

    }

    /**
     * to produce the menu by layout inflation
     *
     * @return int with resource id
     */
    protected int getRmenu() {
        return -1;
    }

    private Frag getOldFragment(Frag fragment, @IdRes int frame_location) throws Exception {
        if (fragment instanceof Fragment) {
            return (Frag) this.getFragmentManager().findFragmentById(frame_location);
        } else if (fragment instanceof android.support.v4.app.Fragment) {
            return (Frag) this.getSupportFragmentManager().findFragmentById(frame_location);
        } else {
            throw new Exception("The input fragment is not a valid Fragment. ");
        }
    }

    private void setRightSideFragment(Frag fragment, @Nullable Bundle savestate) {
        setBehindContentView(R.layout.menu_frame);
        try {
            if (savestate != null) {
                setPrimaryMenuFragment(fragment, getOldFragment(fragment, R.id.menu_frame));
            } else {
                setPrimaryMenuFragment(fragment, null);
            }
        } catch (RuntimeException e) {
            Log.d("RIGHTSIDE", e.getMessage());
        } catch (Exception e) {
            Log.d("RIGHTSIDE", e.getMessage());
        }
    }

    protected void setUnblock() {
        if (content_frame != null) {
            content_frame.noBlock();
        }
    }

    protected void setBlockEnableWithColor(@ColorRes int mdrawble) {
        if (content_frame != null) {
            content_frame.setDimColor(mdrawble);
        }
    }

    protected void setBlockEnableWithDrawable(@DrawableRes int mdrawble) {
        if (content_frame != null) {
            content_frame.setDimDrawble(mdrawble);
        }
    }

    private void initMainContentFragment(Frag fragment, Bundle savestate) {
        setContentView(getDefaultMainActivityLayoutId());
        initToolBar(getDefaultMainActivityLayoutId());
        try {
            content_frame = (ControlableFrame) findViewById(R.id.aslib_main_frame_body);
        } catch (Exception e) {
        }
        if (savestate == null) {
            setFragment(fragment, getTitle().toString(), null, false);
        } else {
            final Frag oldfragment = (Frag) this.getFragmentManager().findFragmentById(R.id.aslib_main_frame_body);
            setFragment(fragment, getTitle().toString(), oldfragment);
        }
    }


    private void initToolBar(final @LayoutRes int resId) {
        final Toolbar widgetToolBar = (Toolbar) findViewById(R.id.aslib_toolbar);
        try {
            if (BODY_LAYOUT.isToolbarOn(resId) || forceConfigureToolBar()) {
                if (widgetToolBar != null) {
                    configToolBar(widgetToolBar);
                    setSupportActionBar(widgetToolBar);
                }
            }
        } catch (Exception e) {
            if (forceConfigureToolBar() && widgetToolBar != null) {
                //this will be using the custom layouts
                configToolBar(widgetToolBar);
            }
        }
    }


    public void setinternalChangeNoToggle(Frag section, String title) {
        setinternalChange(section, title, currentFragmentNow, false);
    }

    @Override
    public void setinternalChange(Frag section, String title) {
        setFragment(section, title);
    }


    @Override
    public void setinternalChange(Frag section, String title, Frag previousFragment, boolean closedrawer) {
        setFragment(section, title, previousFragment, closedrawer);
    }


    @Override
    public void setinternalChange(Frag section, String title, Frag previousFragment) {
        setFragment(section, title, previousFragment);
    }

    public void setFragment(Frag fragment, String title, Frag old_fragment) {
        setFragment(fragment, title, old_fragment, true);
    }


    public void setFragment(Frag fragment, String title) {
        setFragment(fragment, title, null, true);
    }


    /**
     * require android-support-v4 import and the regular android fragment
     *
     * @param fragment    the unknown typed fragment
     * @param oldFragment the previous fragment
     */
    private void setPrimaryMenuFragment(Frag fragment, Frag oldFragment) throws RuntimeException, Exception {
        rightMenuFragment = fragment;

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
            // before honeycomb there is not android.app.Fragment
            android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            if (oldFragment != null && oldFragment != fragment)
                ft.remove((android.support.v4.app.Fragment) oldFragment);

            ft.replace(R.id.menu_frame, (android.support.v4.app.Fragment) fragment).commit();
        } else if (fragment instanceof Fragment) {
            if (oldFragment instanceof android.support.v4.app.Fragment)
                throw new RuntimeException("You should use only one type of Fragment");

            FragmentTransaction ft = getFragmentManager().beginTransaction();
            if (oldFragment != null && fragment != oldFragment)
                ft.remove((Fragment) oldFragment);

            ft.replace(R.id.menu_frame, (Fragment) fragment).commit();
        } else if (fragment instanceof android.support.v4.app.Fragment) {
            if (oldFragment instanceof Fragment)
                throw new RuntimeException("You should use only one type of Fragment");

            android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            if (oldFragment != null && oldFragment != fragment)
                ft.remove((android.support.v4.app.Fragment) oldFragment);
            ft.replace(R.id.menu_frame, (android.support.v4.app.Fragment) fragment).commit();
        } else
            throw new RuntimeException("Fragment must be android.app.Fragment or android.support.v4.app.Fragment");


    }


    /**
     * require android-support-v4 import and the regular android fragment
     *
     * @param fragment    the unknown typed fragment
     * @param title       the string in title
     * @param oldFragment the previous fragment
     * @param closeDrawer if it needs to close the drawer after the new fragment has been rendered
     */
    public void setFragment(Frag fragment, String title, Frag oldFragment, boolean closeDrawer) {
        currentFragmentNow = fragment;
        setTitle(title);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
            // before honeycomb there is not android.app.Fragment
            android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            if (oldFragment != null && oldFragment != fragment)
                ft.remove((android.support.v4.app.Fragment) oldFragment);

            ft.replace(R.id.aslib_main_frame_body, (android.support.v4.app.Fragment) fragment).commit();
        } else if (fragment instanceof Fragment) {
            if (oldFragment instanceof android.support.v4.app.Fragment)
                throw new RuntimeException("You should use only one type of Fragment");

            FragmentTransaction ft = getFragmentManager().beginTransaction();
            if (oldFragment != null && fragment != oldFragment)
                ft.remove((Fragment) oldFragment);

            ft.replace(R.id.aslib_main_frame_body, (Fragment) fragment).commit();
        } else if (fragment instanceof android.support.v4.app.Fragment) {
            if (oldFragment instanceof Fragment)
                throw new RuntimeException("You should use only one type of Fragment");

            android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            if (oldFragment != null && oldFragment != fragment)
                ft.remove((android.support.v4.app.Fragment) oldFragment);
            ft.replace(R.id.aslib_main_frame_body, (android.support.v4.app.Fragment) fragment).commit();
        } else
            throw new RuntimeException("Fragment must be android.app.Fragment or android.support.v4.app.Fragment");

        if (closeDrawer)
            getSlidingMenu().toggle(true);

        notifyOnBodyFragmentChange(currentFragmentNow);
    }





  /*  public void setRightSideFragment(F fragment, Bundle savedstate) {
        currentFragmentNow = fragment;
        // set the Behind View
        setBehindContentView(R.layout.menu_frame);

        if (savedstate == null) {
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
                // before honeycomb there is not android.app.Fragment
                android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.menu_frame, (android.support.v4.app.Fragment) fragment).commit();
            } else if (fragment instanceof android.app.Fragment) {
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.menu_frame, (android.app.Fragment) fragment).commit();
            } else if (fragment instanceof android.support.v4.app.Fragment) {
                android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.menu_frame, (android.support.v4.app.Fragment) fragment).commit();
            } else
                throw new RuntimeException("Fragment must be android.app.Fragment or android.support.v4.app.Fragment");
            */



/*
        } else {
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
            } else if (fragment instanceof android.app.Fragment) {
                mFrag = getFragmentManager().findFragmentById(R.id.menu_frame);

            } else if (fragment instanceof android.support.v4.app.Fragment) {
                mFrag = getSupportFragmentManager().findFragmentById(R.id.menu_frame);
            } else
                throw new RuntimeException("Fragment must be android.app.Fragment or android.support.v4.app.Fragment");
        }
    }*/


    @Override
    public void onCreate(Bundle sved) {
        super.onCreate(sved);
        setRightSideFragment(getFirstMenuFragment(), sved);
        customizeSlideMenuEdge(getSlidingMenu());
        initMainContentFragment(getInitFragment(), sved);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                toggle();
                return true;
            // case R.id.github:
            //    Util.goToGitHub(this);
            //   return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (getRmenu() > -1) {
            getMenuInflater().inflate(getRmenu(), menu);
        }
        return true;
    }


    /**
     * there are some presets for the main layout configurations
     * - noactionbar : there is consist of layout resource IDs of +id(main_frame_body)
     * - actionbar : there is consist of layout resource IDs of +id(main_frame_body) and +id(mxtoolbar)
     * - overlayactionbar : there is consist of layout resource IDs of +id(main_frame_body) and +id(mxtoolbar)
     */
    public enum BODY_LAYOUT {
        overlayactionbar(R.layout.template_nomoframedactionba, true),
        noactionbar(R.layout.template_noactionbar, false),
        actionbar(R.layout.template_nonoactionbarframer, true),
        singlepullslide(R.layout.template_custom_slideout_single_article, true),
        singelsimple(R.layout.template_adjus_simple, true);

        private int id;
        private boolean mhastoolbar;

        BODY_LAYOUT(@LayoutRes int layoutId, boolean hasToolBar) {
            id = layoutId;
            mhastoolbar = hasToolBar;
        }

        public boolean hasToolBarInside() {
            return mhastoolbar;
        }

        public int getResID() {
            return id;
        }

        public static boolean compareSymbol(final int Ordinal, final BODY_LAYOUT symbol) {
            return symbol.ordinal() == Ordinal;
        }

        public static BODY_LAYOUT fromLayoutId(final @LayoutRes int id) throws Exception {
            final int e = BODY_LAYOUT.values().length;
            for (int i = 0; i < e; i++) {
                final BODY_LAYOUT h = BODY_LAYOUT.values()[i];
                if (h.getResID() == id) {
                    return h;
                }
            }
            throw new Exception("not found from this layout Id");
        }

        public static boolean isToolbarOn(final @LayoutRes int id) throws Exception {
            return BODY_LAYOUT.fromLayoutId(id).hasToolBarInside();
        }
    }


    @Override
    public void onBackPressed() {
        if (rightMenuFragment instanceof materialMenuConstructorFragmentBase) {
            final MenuBuildHelper helper = ((materialMenuConstructorFragmentBase) rightMenuFragment).NavBuilder();
            switch (helper.getBackPattern()) {
                default:
                case materialMenuConstructorFragmentBase.BACKPATTERN_BACK_ANYWHERE:
                    super.onBackPressed();
                    break;
                //TODO: need to use these switches below
            /*    case materialMenuConstructorFragmentBase.BACKPATTERN_BACK_TO_START_INDEX:
                    MaterialSection section = (MaterialSection) helper.getMENU().getItems().get(helper.getMENU().getStartIndex());
                    if (currentSection == section)
                        super.onBackPressed();
                    else {
                        //section.select();
                        onClick(section, section.getView());
                    }
                    break;
                case materialMenuConstructorFragmentBase.BACKPATTERN_CUSTOM:
                    MaterialSection backedSection = backToSection(getCurrentSection());
                    if (currentSection == backedSection) {
                        if (backedSection.getTarget() != MaterialSection.TARGET_FRAGMENT) {
                            super.onBackPressed();
                        } else {
                            if (currentFragmentNow == backedSection.getTargetFragment()) {
                                super.onBackPressed();
                            } else {

                            }
                        }
                    } else {
                        if (backedSection.getTarget() != MaterialSection.TARGET_FRAGMENT) {
                            throw new RuntimeException("The restored section must have a fragment as target");
                        }
                        onClick(backedSection, backedSection.getView());
                    }
                    break;
                case materialMenuConstructorFragmentBase.BACKPATTERN_LAST_SECTION:

                    if (sectionLastBackPatternList.size() == 0) {
                        super.onBackPressed();
                    }

                    while (sectionLastBackPatternList.size() > 0) {
                        // get section position
                        int posSection = currentMenu.getSectionPosition(sectionLastBackPatternList.get(sectionLastBackPatternList.size() - 1));
                        // remove last section
                        sectionLastBackPatternList.remove(sectionLastBackPatternList.size() - 1);
                        // if section found in the menu, load the section
                        if (posSection != -1) {
                            reloadMenu(posSection, false);
                            break;
                        }
                    }
                    break;*/
            }
        }

    }
}
