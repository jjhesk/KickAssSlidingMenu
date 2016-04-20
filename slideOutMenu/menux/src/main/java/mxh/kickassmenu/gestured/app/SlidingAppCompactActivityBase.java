package mxh.kickassmenu.gestured.app;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;

import mxh.kickassmenu.gestured.SlidingMenu;


/**
 * Created by hesk on 22/6/15.
 */
public class SlidingAppCompactActivityBase extends AppCompatActivity implements SlidingActivityBase {

    private SlidingActivityHelper mHelper;

    /* (non-Javadoc)
     * @see android.support.v4.app.FragmentActivity#onCreate(android.os.Bundle)
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mHelper = new SlidingActivityHelper(this);
        mHelper.onCreate(savedInstanceState);
    }

    /* (non-Javadoc)
     * @see android.app.Activity#onPostCreate(android.os.Bundle)
     */
    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mHelper.onPostCreate(savedInstanceState);
    }

    /* (non-Javadoc)
     * @see android.app.Activity#findViewById(int)
     */
    @Override
    public View findViewById(int id) {
        View v = super.findViewById(id);
        if (v != null)
            return v;
        return mHelper.findViewById(id);
    }

    /* (non-Javadoc)
     * @see android.support.v4.app.FragmentActivity#onSaveInstanceState(android.os.Bundle)
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mHelper.onSaveInstanceState(outState);
    }

   /*
    @Override
    public void setContentView(int id) {
        setContentView(getLayoutInflater().inflate(id, null));
    }


    @Override
    public void setContentView(View v) {
        setContentView(v, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
    }
*/


    @Deprecated
    @Override
    public void setContentView(View view) {
        mHelper.registerAboveContentView(view, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
    }

    @Deprecated
    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        mHelper.registerAboveContentView(view, params);
    }


    @Override
    public void setContentView(@LayoutRes int id) {
        super.setContentView(id);
        mHelper.registerAboveContentView(getLayoutInflater().inflate(id, null), new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
    }

    /* (non-Javadoc)
     * @see com.jeremyfeinstein.slidingmenu.lib.app.SlidingActivityBase#setBehindContentView(int)
     */
    public void setBehindContentView(int id) {
        setBehindContentView(getLayoutInflater().inflate(id, null));
    }

    /* (non-Javadoc)
     * @see com.jeremyfeinstein.slidingmenu.lib.app.SlidingActivityBase#setBehindContentView(android.view.View)
     */
    public void setBehindContentView(View v) {
        setBehindContentView(v, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
    }

    /* (non-Javadoc)
     * @see com.jeremyfeinstein.slidingmenu.lib.app.SlidingActivityBase#setBehindContentView(android.view.View, android.view.ViewGroup.LayoutParams)
     */
    public void setBehindContentView(View v, ViewGroup.LayoutParams params) {
        mHelper.setBehindContentView(v, params);
    }

    /* (non-Javadoc)
     * @see com.jeremyfeinstein.slidingmenu.lib.app.SlidingActivityBase#getSlidingMenu()
     */
    public SlidingMenu getSlidingMenu() {
        return mHelper.getSlidingMenu();
    }

    /* (non-Javadoc)
     * @see com.jeremyfeinstein.slidingmenu.lib.app.SlidingActivityBase#toggle()
     */
    public void toggle() {
        mHelper.toggle();
    }

    /* (non-Javadoc)
     * @see com.jeremyfeinstein.slidingmenu.lib.app.SlidingActivityBase#showAbove()
     */
    public void showContent() {
        mHelper.showContent();
    }

    /* (non-Javadoc)
     * @see com.jeremyfeinstein.slidingmenu.lib.app.SlidingActivityBase#showBehind()
     */
    public void showMenu() {
        mHelper.showMenu();
    }

    /* (non-Javadoc)
     * @see com.jeremyfeinstein.slidingmenu.lib.app.SlidingActivityBase#showSecondaryMenu()
     */
    public void showSecondaryMenu() {
        mHelper.showSecondaryMenu();
    }

    /* (non-Javadoc)
     * @see com.jeremyfeinstein.slidingmenu.lib.app.SlidingActivityBase#setSlidingActionBarEnabled(boolean)
     */

    /**
     * we will implement a new API to handle actionbar appearance
     *
     * @param b bool
     */
    @Deprecated
    public void setSlidingActionBarEnabled(boolean b) {
        mHelper.setSlidingActionBarEnabled(b);
    }

    /* (non-Javadoc)
     * @see android.app.Activity#onKeyUp(int, android.view.KeyEvent)
     */
    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        boolean b = mHelper.onKeyUp(keyCode, event);
        if (b) return b;
        return super.onKeyUp(keyCode, event);
    }
}
