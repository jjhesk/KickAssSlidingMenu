package com.hkm.hbstore.pages;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.hkm.hbstore.R;
import com.hkm.hbstore.adapters.ItemList;

import java.lang.reflect.Field;

import it.neokree.materialtabs.MaterialTab;
import it.neokree.materialtabs.MaterialTabHost;
import it.neokree.materialtabs.MaterialTabListener;

/**
 * Created by hesk on 21/4/15.
 */
public class DsiPage extends Fragment implements MaterialTabListener {

    private RecyclerView mRecyclerView;
    private MaterialTabHost mTab;
    private ViewPager pager;
    private ItemList pagerAdapter;
    private FragmentManager mChildFragmentManager;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.homepage, container, false);
    }

    @SuppressLint("ResourceAsColor")
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public void onViewCreated(View v, Bundle b) {
        mTab = (MaterialTabHost) v.findViewById(R.id.materialTabHost);
        pager = (ViewPager) v.findViewById(R.id.viewpager);


        mTab.setBorderReferenceColor(1, R.color.divider_press);
        mTab.setCustomBackground(R.drawable.tab_host_bottom_line);
        mTab.addTab(mTab.createCustomTextTab(R.layout.item_tab, "RECENT", false).setTabListener(this));
        mTab.addTab(mTab.createCustomTextTab(R.layout.item_tab, "POPULAR", false).setTabListener(this));
        mTab.addTab(mTab.createCustomTextTab(R.layout.item_tab, "POPU3LAR", false).setTabListener(this));
        mTab.addTab(mTab.createCustomTextTab(R.layout.item_tab, "PO2ULAR", false).setTabListener(this));
        mTab.addTab(mTab.createCustomTextTab(R.layout.item_tab, "POP4ULAR", false).setTabListener(this));
        mTab.addTab(mTab.createCustomTextTab(R.layout.item_tab, "P1OP4ULAR", false).setTabListener(this));
        mTab.addTab(mTab.createCustomTextTab(R.layout.item_tab, "POP4ULA4R", false).setTabListener(this));
        mTab.addTab(mTab.createCustomTextTab(R.layout.item_tab, "PO2P4ULAR", false).setTabListener(this));
   /*
        mRecyclerView = (RecyclerView) v.findViewById(R.id.recycler);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

   */
        // createAdapter(mRecyclerView);

        // init view pager
        pagerAdapter = new ItemList(mChildFragmentManager = getChildFragmentManager());
        pager.setAdapter(pagerAdapter);
     /*   pager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                // when user do a swipe the selected tab change
                mTab.setSelectedNavigationItem(position);
            }
        });*/

    }

    /**
     * material tab host implementations
     */

    @Override
    public void onTabSelected(MaterialTab materialTab) {
        int pos = materialTab.getPosition();
        pager.setCurrentItem(pos, true);
    }


    @Override
    public void onTabReselected(MaterialTab materialTab) {

    }

    @Override
    public void onTabUnselected(MaterialTab materialTab) {
        //  tab_selected_final(materialTab);

    }

    @Override
    public void onDetach() {
        super.onDetach();
        try {
            Field childFragmentManager = Fragment.class.getDeclaredField("mChildFragmentManager");
            childFragmentManager.setAccessible(true);
            childFragmentManager.set(this, null);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
