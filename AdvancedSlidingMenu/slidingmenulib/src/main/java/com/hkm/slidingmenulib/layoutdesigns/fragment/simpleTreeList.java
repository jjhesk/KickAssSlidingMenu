package com.hkm.slidingmenulib.layoutdesigns.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.hkm.slidingmenulib.R;
import com.hkm.slidingmenulib.advancedtreeview.ExpAdapter;
import com.hkm.slidingmenulib.advancedtreeview.OnScrollToListener;
import com.marshalchen.ultimaterecyclerview.UltimateRecyclerView;
import com.marshalchen.ultimaterecyclerview.divideritemdecoration.HorizontalDividerItemDecoration;
import com.squareup.picasso.Picasso;

/**
 * Created by hesk on 8/7/15.
 */
public abstract class simpleTreeList<adapter extends ExpAdapter> extends Fragment {

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // listener = this;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.sm_list, container, false);
    }

    public static String TAG = "catelog";
    public final static String SLUG = "slug", REQUEST_TYPE = "typerequest";
    public UltimateRecyclerView listview_layout;
    protected Picasso picasso;
    public static String URL = "data_url";
    public static String FRAGMENTTITLE = "fragment_title";
    protected LinearLayoutManager mLayoutManager;
    protected adapter madapter;
    private LinearLayout menu_container;

    /**
     * this binding will be excluding the view id of
     * 1)ultimate_recycler_view
     *
     * @param before relative layout
     * @param after  relative layout
     */
    protected void expansionBinding(RelativeLayout before, RelativeLayout after) {

    }

    /**
     * this is provided for external use only do not override it
     *
     * @param ctx   the context
     * @param color the res id in color
     */
    protected void setBackground(final Context ctx, final @ColorRes int color) {
        menu_container.setBackgroundColor(ctx.getResources().getColor(color));
    }

    /**
     * the primary binding from the flate
     *
     * @param view mview
     * @throws Exception anything that is not working
     */
    private void renderviewlayout(View view) throws Exception {
        listview_layout = (UltimateRecyclerView) view.findViewById(R.id.ultimate_recycler_view);
        mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        listview_layout.setLayoutManager(mLayoutManager);
        listview_layout.setHasFixedSize(true);
        listview_layout.setSaveEnabled(true);
        picasso = Picasso.with(getActivity());
        listview_layout.setAdapter(madapter = getAdatperWithdata());

        listview_layout.getItemAnimator().setAddDuration(100);
        listview_layout.getItemAnimator().setRemoveDuration(100);
        listview_layout.getItemAnimator().setMoveDuration(200);
        listview_layout.getItemAnimator().setChangeDuration(100);
        madapter.addOnScrollToListener(new OnScrollToListener() {
            @Override
            public void scrollTo(int position) {
                try {
                    listview_layout.scrollVerticallyTo(position);
                } catch (Exception e) {
                    Log.d(TAG, "error scroll:" + e.getMessage());
                }
            }
        });
        setUltimateRecyclerViewExtra(listview_layout, madapter);
        final HorizontalDividerItemDecoration decor = new HorizontalDividerItemDecoration.Builder(getActivity()).paint(getlinestyle()).showLastDivider().build();
        listview_layout.addItemDecoration(decor);

        final RelativeLayout rl_before = (RelativeLayout) view.findViewById(R.id.before_list);
        final RelativeLayout rl_after = (RelativeLayout) view.findViewById(R.id.after_list);
        menu_container = (LinearLayout) view.findViewById(R.id.menu_container);
        expansionBinding(rl_before, rl_after);
    }

    protected Paint getdash() {
        Paint paint = new Paint();
        float fl = getActivity().getResources().getDimensionPixelSize(R.dimen.divider_stroke_width_treelist_view);
        paint.setStrokeWidth(fl);
        paint.setColor(Color.BLUE);
        paint.setAntiAlias(true);
        paint.setPathEffect(new DashPathEffect(new float[]{25.0f, 25.0f}, 0));
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.HONEYCOMB) {
            listview_layout.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        }
        return paint;
    }

    protected Paint getsolid(final @ColorRes int intcolor) {
        Paint paint = new Paint();
        int color = getResources().getColor(intcolor);
        float fl = getResources().getDimension(R.dimen.divider_stroke_width_treelist_view);
        paint.setColor(color);
        paint.setStrokeWidth(fl);
        return paint;
    }

    /**
     * this is better to override it
     *
     * @return the Paint
     */
    protected Paint getlinestyle() {
        return getsolid(R.color.defaultactionbarColorDark);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            try {
                renderviewlayout(view);
                if (getArguments() != null) {
                    if (onArguments(getArguments())) {
                        loadDataInitial(madapter);
                    }
                }
            } catch (Exception e) {
                Log.d(TAG, e.getMessage());
            }
        } else {
            Log.d(TAG, "back from pause");
        }
    }


    /**
     * step 1:
     * takes the arguement form the intent bundle and determine if there is a need to queue a loading process. If that is a yes then we need to load up the data before displaying the list out.
     *
     * @param r and the data bundle
     * @return tells if  there is a loading process to be done before hand
     */
    protected abstract boolean onArguments(final Bundle r);

    /**
     * step 2:
     * this is the call for the loading the data stream externally
     *
     * @param confirmAdapter adapter
     */
    protected abstract void loadDataInitial(final adapter confirmAdapter);

    /**
     * load the adapter with the data list
     *
     * @return the adapter with type
     */
    protected abstract adapter getAdatperWithdata();

    /**
     * set up the extra configurations on the ultimate recycler view
     *
     * @param listview the list with view
     * @param madapter the adapter
     */
    protected abstract void setUltimateRecyclerViewExtra(final UltimateRecyclerView listview, final adapter madapter);

}
