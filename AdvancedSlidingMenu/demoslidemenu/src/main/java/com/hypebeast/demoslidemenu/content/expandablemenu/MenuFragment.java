package com.hypebeast.demoslidemenu.content.expandablemenu;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;

import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hypebeast.demoslidemenu.R;
import com.marshalchen.ultimaterecyclerview.UltimateRecyclerView;

/**
 * Created by hesk on 23/2/16.
 */
public class MenuFragment extends Fragment {

    private UltimateRecyclerView ultimateRecyclerView;
    private expCustomAdapter simpleRecyclerViewAdapter = null;
    private LinearLayoutManager linearLayoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.left_slid_menu, container, false);
    }


    private static String[] sampledatagroup1 = {
            "peter", "http://google",
            "billy", "http://google",
            "lisa", "http://google",
            "visa", "http://google"
    };
    private static String[] sampledatagroup2 = {
            "mother", "http://google",
            "father", "http://google",
            "son", "http://google",
            "holy spirit", "http://google",
            "god the son", "http://google"
    };
    private static String[] sampledatagroup3 = {
            "SONY", "http://google",
            "LG", "http://google",
            "SAMSUNG", "http://google",
            "XIAOMI", "http://google",
            "HTC", "http://google"
    };

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ultimateRecyclerView = (UltimateRecyclerView) view.findViewById(R.id.ultimate_recycler_view_menu);
        ultimateRecyclerView.setHasFixedSize(false);
        /**
         * this is the adapter for the expanx
         */
        simpleRecyclerViewAdapter = new expCustomAdapter(getActivity());
        simpleRecyclerViewAdapter.addAll(expCustomAdapter.getPreCodeMenu(
                        sampledatagroup1,
                        sampledatagroup2,
                        sampledatagroup3),
                0);

        linearLayoutManager = new LinearLayoutManager(getActivity());
        ultimateRecyclerView.setLayoutManager(linearLayoutManager);
        ultimateRecyclerView.setAdapter(simpleRecyclerViewAdapter);
        ultimateRecyclerView.setRecylerViewBackgroundColor(Color.parseColor("#ffffff"));
        addExpandableFeatures();

    }

    private void addExpandableFeatures() {
        ultimateRecyclerView.getItemAnimator().setAddDuration(100);
        ultimateRecyclerView.getItemAnimator().setRemoveDuration(100);
        ultimateRecyclerView.getItemAnimator().setMoveDuration(200);
        ultimateRecyclerView.getItemAnimator().setChangeDuration(100);
    }

}
