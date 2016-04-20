package mxh.kickassmenu.layoutdesigns;

import android.app.FragmentTransaction;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import mxh.kickassmenu.R;
import mxh.kickassmenu.gestured.SlidingMenu;
import mxh.kickassmenu.layoutdesigns.app.SlidingAppCompactActivity;
import mxh.kickassmenu.menucontent.internalChangeInFragment;

/**
 * Created by hesk on 2/7/15.
 */
public abstract class singleDetailPost<Frag> extends AppCompatActivity implements internalChangeInFragment<Frag> {
    private Toolbar tb;
    protected String url;
    protected long pid;
    protected Frag currentFragmentNow, rightMenuFragment;
    public static final String
            PID = "POST_ID",
            Method = "METHOD_REQUEST",
            requestURL = "URL",
            TAG = "singlePost";


    public static final int REQUEST_METHOD_FULL_URL = 1;
    public static final int REQUEST_METHOD_POST_ID = 2;

    protected Toolbar getTB() {
        return tb;
    }

    private void initMainContentFragment(Frag mfragment, Bundle savestate) {
        if (savestate == null) {
            setContentView(getDefaultMainActivityLayoutId());
            initToolBar(getDefaultMainActivityLayoutId());
            setFragment(mfragment, getTitle().toString(), null, false);
            initalizeOtherUI();
        } else {
            currentFragmentNow = (Frag) this.getFragmentManager().findFragmentById(R.id.aslib_main_frame_body);
        }
    }

    protected boolean startIntentArgument() {
        Bundle b = getIntent().getExtras();
        if (b == null) return false;
        final int method = b.getInt(Method, 0);
        if (method == 0) return false;
        if (method == REQUEST_METHOD_FULL_URL) {
            loadPageWithFullURL(url = b.getString(requestURL));
        }
        if (method == REQUEST_METHOD_POST_ID) {
            loadPageWithPID(pid = b.getLong(PID));
        }

        return true;
    }

    protected boolean hasStatusBarPadding() {
        return true;
    }

    protected void initalizeOtherUI() {
        if (!hasStatusBarPadding()) {
            View hv = (View) findViewById(R.id.aslib_statusBar);
            if (hv != null) {
                hv.setVisibility(View.GONE);
            }
        }
    }

    protected abstract void loadPageWithFullURL(final String url);

    protected abstract void loadPageWithPID(final long pid);

    /**
     * the location to setup and configure the toolbar widget under AppCompat V7
     *
     * @param v7 Toolbar object
     */
    protected void configToolBar(final Toolbar v7) {
        classicToolbarConfig(v7);
    }

    protected void classicToolbarConfig(final Toolbar v7) {
        v7.setTitle(getTitle());
        setSupportActionBar(v7);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
    }


    /**
     * init the tool bar configuration
     *
     * @param resId the location of the tool bar id
     */
    private void initToolBar(final @LayoutRes int resId) {
        final Toolbar widgetToolBar = (Toolbar) findViewById(R.id.aslib_toolbar);
        if (widgetToolBar != null) {
            //toolbar is found
            configToolBar(widgetToolBar);
        }
        //  if (SlidingAppCompactActivity.BODY_LAYOUT.isToolbarOn(resId) || forceConfigureToolBar()) {
        // }

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
     * to produce the menu by layout inflation
     *
     * @return int with resource id
     */
    protected int getRmenu() {
        return -1;
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
        } else if (fragment instanceof android.app.Fragment) {
            if (oldFragment instanceof android.support.v4.app.Fragment)
                throw new RuntimeException("You should use only one type of Fragment");

            FragmentTransaction ft = getFragmentManager().beginTransaction();
            if (oldFragment != null && fragment != oldFragment)
                ft.remove((android.app.Fragment) oldFragment);

            ft.replace(R.id.aslib_main_frame_body, (android.app.Fragment) fragment).commit();
        } else if (fragment instanceof android.support.v4.app.Fragment) {
            if (oldFragment instanceof android.app.Fragment)
                throw new RuntimeException("You should use only one type of Fragment");

            android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            if (oldFragment != null && oldFragment != fragment)
                ft.remove((android.support.v4.app.Fragment) oldFragment);
            ft.replace(R.id.aslib_main_frame_body, (android.support.v4.app.Fragment) fragment).commit();
        } else
            throw new RuntimeException("Fragment must be android.app.Fragment or android.support.v4.app.Fragment");


        notifyOnBodyFragmentChange(currentFragmentNow);
    }


    /**
     * setting the default main activity layout ID and this is normally had presented in the library and no need change unless there is a customization need for different layout ID
     *
     * @return resource id
     */
    protected int getDefaultMainActivityLayoutId() {
        return SlidingAppCompactActivity.BODY_LAYOUT.singelsimple.getResID();
    }

    /**
     * @return when @link{getDefaultMainActivityLayoutId} is using user specified layout and such layout contains custom action bar or custom action tool bar then this function must return TRUE to enable the configuration of the tool bar
     */
    @Deprecated
    protected boolean forceConfigureToolBar() {
        return false;
    }

    /**
     * setting the first initial fragment at the beginning
     *
     * @return generic type fragment
     * @throws Exception the exception for the wrongs
     */
    protected abstract Frag getInitFragment() throws Exception;

    /**
     * when the fragment is changed now and it will notify the function for user specific operations
     *
     * @param new_fragment_change_now the generic fragment type
     */
    protected void notifyOnBodyFragmentChange(Frag new_fragment_change_now) {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            initMainContentFragment(getInitFragment(), savedInstanceState);
        } catch (Exception e) {
            Log.d(TAG, e.getMessage());
        }
    }

    protected abstract void onMenuItemSelected(final @IdRes int Id);

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onMenuItemSelected(item.getItemId());
        if (item.getItemId() == android.R.id.home) {
            finish();
            return (true);
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

    @Override
    protected void onPause() {
        //  killwebview(mVideo);
        super.onPause();
    }

    @Override
    public void finish() {
        //  killwebview(mVideo);
        super.finish();
    }
    
}
