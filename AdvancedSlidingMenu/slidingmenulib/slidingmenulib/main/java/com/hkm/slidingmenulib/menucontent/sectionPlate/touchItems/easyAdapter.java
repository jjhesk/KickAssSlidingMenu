package com.hkm.slidingmenulib.menucontent.sectionPlate.touchItems;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.marshalchen.ultimaterecyclerview.UltimateRecyclerviewViewHolder;
import com.marshalchen.ultimaterecyclerview.UltimateViewAdapter;

import java.util.List;

/**
 * Created by hesk on 25/6/15.
 */
public abstract class easyAdapter<T, BINDHOLDER extends UltimateRecyclerviewViewHolder> extends UltimateViewAdapter {
    private List<T> currentlistsource;

    /**
     * dynamic object to start
     *
     * @param list the list source
     */
    public easyAdapter(List<T> list) {
        currentlistsource = list;
    }

    /**
     * the layout id for the normal data
     *
     * @return the ID
     */
    protected abstract int getNormalLayoutResId();

    protected abstract BINDHOLDER newViewHolder(View view);

    @Override
    public UltimateRecyclerviewViewHolder onCreateViewHolder(ViewGroup parent) {
        View v = LayoutInflater.from(parent.getContext()).inflate(getNormalLayoutResId(), parent, false);
        return newViewHolder(v);
    }

    @Override
    public int getAdapterItemCount() {
        return currentlistsource.size();
    }

    @Override
    public long generateHeaderId(int i) {
        return 0;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (position == getAdapterItemCount()) return;
        withBindHolder((BINDHOLDER) holder, currentlistsource.get(position), position);
    }

    protected abstract void withBindHolder(final BINDHOLDER holder, final T data, final int position);

    @Override
    public RecyclerView.ViewHolder onCreateHeaderViewHolder(ViewGroup viewGroup) {
        return null;
    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
    }


    public void insert(T object) {
        insert(currentlistsource, object, currentlistsource.size());
    }
}
