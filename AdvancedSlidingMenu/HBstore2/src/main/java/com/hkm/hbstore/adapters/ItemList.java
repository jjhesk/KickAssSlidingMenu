package com.hkm.hbstore.adapters;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v13.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hkm.hbstore.R;
import com.hkm.hbstore.pages.testpage;


/**
 * Created by hesk on 21/4/15.
 */
public class ItemList extends FragmentPagerAdapter {
    private static int NUM_ITEMS = 2;
    private static String
            HOMEPAGELIST_1 = " ",
            HOMEPAGELIST_2 = "foiwje ";
    private static int pageNum_1 = 1, pageNum_2 = 1;

    public ItemList(FragmentManager fm) {
        super(fm);

    }

    /**
     * Return the Fragment associated with a specified position.
     *
     * @param position
     */
    @Override
    public Fragment getItem(int position) {
        /*switch (position) {
            return new GridDisplay();
            default:
                return null;
        }*/

        return new testpage();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "Page " + position;
    }

    /**
     * Return the number of views available.
     */
    @Override
    public int getCount() {
        return NUM_ITEMS;
    }


    @SuppressLint("ValidFragment")
    public class SampleF extends Fragment {
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            //return super.onCreateView(inflater, container, savedInstanceState);
            return inflater.inflate(R.layout.row, container);
        }

        @Override
        public void onViewCreated(View view, Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);
        }
    }
}
