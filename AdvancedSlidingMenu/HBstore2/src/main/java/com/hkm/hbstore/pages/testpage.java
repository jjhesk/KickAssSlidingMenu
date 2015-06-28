package com.hkm.hbstore.pages;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hkm.hbstore.R;
import com.neopixl.pixlui.components.textview.TextView;


/**
 * Created by hesk on 22/6/15.
 */
public class testpage extends Fragment {
    TextView mTab;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.emptyview, container, false);
    }

    @SuppressLint("ResourceAsColor")
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public void onViewCreated(View v, Bundle b) {
        mTab = (TextView) v.findViewById(R.id.text);


    }
}
