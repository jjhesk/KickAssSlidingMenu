package com.hkm.slidingmenulib.layoutdesigns;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.hkm.slidingmenulib.R;
import com.hkm.slidingmenulib.menucontent.sectionPlate.touchItems.midUltimateAdapter;
import com.marshalchen.ultimaterecyclerview.CustomLinearLayoutManager;
import com.marshalchen.ultimaterecyclerview.UltimateRecyclerView;
import com.marshalchen.ultimaterecyclerview.UltimateRecyclerviewViewHolder;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by hesk on 29/6/15.
 */
public abstract class menubigbanner<adapter extends midUltimateAdapter> extends Fragment {

    public static String TAG = "bigmenubanner";
    public UltimateRecyclerView listview_layout;
    private Picasso picasso;
    // private itemSelectListener listener;


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // listener = this;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.content_fragment_image_list, container, false);
    }

    protected abstract adapter getAdatperWithdata();

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            try {
                listview_layout = (UltimateRecyclerView) view.findViewById(R.id.ultimate_recycler_view);
                CustomLinearLayoutManager mlayout = new CustomLinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false);
                listview_layout.setLayoutManager(mlayout);
                listview_layout.setHasFixedSize(true);
                listview_layout.setSaveEnabled(false);
                //  listview_layout.set
                picasso = Picasso.with(getActivity());
                listview_layout.setAdapter(getAdatperWithdata());
            } catch (Exception e) {
                Log.d(TAG, e.getMessage());
            }
        }
    }

    protected abstract void onClickItem(final String route);


    public class datab {
        String image_url;
        String action_route;

        public datab() {

        }

        public void setImage_url(String url) {
            image_url = url;
        }

        public void setAction_route(String route) {
            action_route = route;
        }

        public String getImage_url() {
            return image_url;
        }

        public String getAction_route() {
            return action_route;
        }
    }

    public static class binder extends UltimateRecyclerviewViewHolder {
        public final ImageView im;

        public binder(View itemView) {
            super(itemView);
            im = (ImageView) itemView.findViewById(R.id.imageholder);
        }
    }

    public class udp extends midUltimateAdapter<datab, binder> {

        /**
         * dynamic object to start
         *
         * @param list the list source
         */
        public udp(List list) {
            super(list);
        }

        /**
         * the layout id for the normal data
         *
         * @return the ID
         */
        @Override
        protected int getNormalLayoutResId() {
            return R.layout.simple_row_image;
        }

        @Override
        protected menubigbanner.binder newViewHolder(View view) {
            return new menubigbanner.binder(view);
        }

        @Override
        protected void withBindHolder(final binder holder, final datab data, int position) {
            picasso.load(data.getImage_url()).into(holder.im);
            holder.im.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickItem(data.getAction_route());
                }
            });
        }
    }
}
