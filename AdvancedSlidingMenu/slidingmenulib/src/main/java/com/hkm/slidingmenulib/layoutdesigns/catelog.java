package com.hkm.slidingmenulib.layoutdesigns;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hkm.slidingmenulib.menucontent.sectionPlate.touchItems.midUltimateAdapter;
import com.marshalchen.ultimaterecyclerview.UltimateRecyclerView;
import com.marshalchen.ultimaterecyclerview.UltimateRecyclerviewViewHolder;
import com.squareup.picasso.Picasso;

/**
 * Created by hesk on 30/6/15.
 */
public abstract class catelog<adapter extends midUltimateAdapter, binder extends UltimateRecyclerviewViewHolder> extends Fragment {
    public static String TAG = "catelog";
    public UltimateRecyclerView listview_layout;
    protected Picasso picasso;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // listener = this;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.content_fragment_image_list, container, false);
    }

    protected abstract void onClickItem(final String route);

    protected abstract int getColumn();

    protected abstract adapter getAdatperWithdata();

    protected abstract void setUltimateRecyclerViewExtra(UltimateRecyclerView listview);

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            try {
                listview_layout = (UltimateRecyclerView) view.findViewById(R.id.ultimate_recycler_view);
                GridLayoutManager mlayout = new GridLayoutManager(view.getContext(), getColumn(), LinearLayoutManager.VERTICAL, false);
                listview_layout.setLayoutManager(mlayout);
                listview_layout.setHasFixedSize(true);
                listview_layout.setSaveEnabled(false);
                //  listview_layout.set
                picasso = Picasso.with(getActivity());
                setUltimateRecyclerViewExtra(listview_layout);
                listview_layout.setAdapter(getAdatperWithdata());
            } catch (Exception e) {
                Log.d(TAG, e.getMessage());
            }
        }
    }
}
