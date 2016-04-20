package mxh.kickassmenu.gestured.app;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup.LayoutParams;

import mxh.kickassmenu.gestured.SlidingMenu;


@SuppressWarnings("JavadocReference")
public class SlidingActivity extends Activity implements SlidingActivityBase {

    private SlidingActivityHelper mHelper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mHelper = new SlidingActivityHelper(this);
        mHelper.onCreate(savedInstanceState);
    }

    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mHelper.onPostCreate(savedInstanceState);
    }

    @Override
    public View findViewById(int id) {
        View v = super.findViewById(id);
        if (v != null)
            return v;
        return mHelper.findViewById(id);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mHelper.onSaveInstanceState(outState);
    }

    @Override
    public void setContentView(int id) {
        setContentView(getLayoutInflater().inflate(id, null));
    }

    @Override
    public void setContentView(View v) {
        setContentView(v, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
    }

    @Override
    public void setContentView(View v, LayoutParams params) {
        super.setContentView(v, params);
        mHelper.registerAboveContentView(v, params);
    }


    public void setBehindContentView(int id) {
        setBehindContentView(getLayoutInflater().inflate(id, null));
    }


    public void setBehindContentView(View v) {
        setBehindContentView(v, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
    }

    /* (non-Javadoc)
     * @see com.jeremyfeinstein.slidingmenu.lib.app.SlidingActivityBase#setBehindContentView(android.view.View, android.view.ViewGroup.LayoutParams)
     */
    public void setBehindContentView(View v, LayoutParams params) {
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
