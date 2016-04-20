package mxh.kickassmenu.menucontent;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import mxh.kickassmenu.menucontent.containers.MaterialDrawerContainer;
import mxh.kickassmenu.menucontent.sectionPlate.touchItems.MaterialListSection;

/**
 * Created by hesk on 22/6/15.
 */
public abstract class materialMenuConstructorFragmentBase<ListSection extends MaterialListSection, ListData, RenderBinder extends MaterialListSection.RenderViewBindAdapter> extends Fragment implements MaterialSectionChangeListener, MaterialSectionOnClickListener {

    // static backpattern types
    public static final int BACKPATTERN_BACK_ANYWHERE = 0;
    public static final int BACKPATTERN_BACK_TO_START_INDEX = 1;
    public static final int BACKPATTERN_CUSTOM = 2;
    public static final int BACKPATTERN_LAST_SECTION = 3;

    // static header types
    public static final int DRAWERHEADER_HEADITEMS = 0;
    public static final int DRAWERHEADER_IMAGE = 1;
    public static final int DRAWERHEADER_CUSTOM = 2;
    public static final int DRAWERHEADER_NO_HEADER = 3;

    // default width and height
    public static final int DRAWER_DEFAULT_HEIGHT = 0;
    public static final int DRAWER_DEFAULT_WIDTH = 0;

    private MenuBuildHelper helperNav;
    public static String TAG = "UnableMaterialConstruction";
    private MaterialDrawerContainer binder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binder = new MaterialDrawerContainer(getLayoutId());
        return binder.init(inflater.inflate(binder.getLayout(), container, false));
    }

    protected LAYOUT_DRAWER getLayoutId() {
        return LAYOUT_DRAWER.STICKY_UP;
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            try {

                helperNav = MenuBuildHelper
                        .with(getActivity())
                        .andBinder(binder)
                        .setChangeClickListener(this, this);
                configurationMenu(helperNav.newMENU());
                helperNav.createNewMenu();
                configurationNavigation(helperNav);
            } catch (Exception e) {
                Log.d(TAG, e.getMessage());
            }
        }
    }

    protected abstract void configurationNavigation(MenuBuildHelper navi);

    protected abstract void configurationMenu(MaterialMenu menu);

    public MenuBuildHelper NavBuilder() {
        return helperNav;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    protected ListSection withmenu(List<ListData> dataSource) {
        final MaterialListSection bh = new MaterialListSection();
        return (ListSection) bh.andRenderWith(newBinderRenderForListSection(dataSource));
    }

    protected ListSection withmenu(RenderBinder initiatedRenderBinding) {
        final MaterialListSection bh = new MaterialListSection();
        return (ListSection) bh.andRenderWith(initiatedRenderBinding);
    }

    protected ListSection withmenu() {
        return withmenu(newBinderRenderForListSection(genMenu()));
    }

    protected abstract RenderBinder newBinderRenderForListSection(List<ListData> dataSource);

    protected abstract ListData genNewSampleDataobject(Random e, final int atPos);

    private List<ListData> genMenu() {
        List<ListData> b = new ArrayList<>();
        Random e = new Random();
        int re = e.nextInt(20);
        for (int h = 0; h < re; h++) {
            b.add(genNewSampleDataobject(e, h));
        }
        return b;
    }

    protected final String[][] mSampleData = {
            {"1.5"},
            {"1.6"},
            {"2.0", "2.0.1", "2.1"},
            {"2.2", "2.2.1", "2.2.2", "2.2.3"},
            {"2.3", "2.3.1", "2.3.2", "2.3.3", "2.3.4", "2.3.5", "2.3.6", "2.3.7"},
            {"3.0", "3.1", "3.2", "3.2.1", "3.2.2", "3.2.3", "3.2.4", "3.2.5", "3.2.6"},
            {"4.0", "4.0.1", "4.0.2", "4.0.3", "4.0.4"},
            {"4.1", "4.1.1", "4.1.2", "4.2", "4.2.1", "4.2.2", "4.3", "4.3.1"},
            {"4.4"}
    };
}
