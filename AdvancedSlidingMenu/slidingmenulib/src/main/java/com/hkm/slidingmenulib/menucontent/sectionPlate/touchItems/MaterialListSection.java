package com.hkm.slidingmenulib.menucontent.sectionPlate.touchItems;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.hkm.slidingmenulib.R;
import com.hkm.slidingmenulib.menucontent.ImaterialBinder;

import com.marshalchen.ultimaterecyclerview.CustomLinearLayoutManager;
import com.marshalchen.ultimaterecyclerview.UltimateRecyclerView;
import com.marshalchen.ultimaterecyclerview.UltimateRecyclerviewViewHolder;

import java.util.List;

/**
 * Created by hesk on 25/6/15.
 * from the HB STORE project 2015
 */
public class MaterialListSection<TD, CustomTextView extends TextView, RenderBinder extends MaterialListSection.RenderViewBindAdapter> implements ImaterialBinder {
    public CustomTextView text, notificationtext;
    public ImageView indicator, icon;
    public UltimateRecyclerView listview;
    public static String TAG = "section header on";
    private RenderBinder renderer;
    private boolean mstatusShown;
    private View mContainer;
    private int mContainerOriginalHeight;
    private ScrollView scrollcontainer;

    public MaterialListSection() {
        mstatusShown = false;
    }

    @Override
    public int getLayout() {
        return R.layout.section_list_header;
    }

    @SuppressLint("WrongViewCast")
    @Override
    public View init(View view) {
        indicator = (ImageView) view.findViewById(R.id.indicatorIcon);
        text = (CustomTextView) view.findViewById(R.id.section_text);
        notificationtext = (CustomTextView) view.findViewById(R.id.section_notification);
        listview = (UltimateRecyclerView) view.findViewById(R.id.ultimate_recycler_view);
        // listview.disableLoadmore();
        listview.setSaveEnabled(false);
        CustomLinearLayoutManager mlayout = new CustomLinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false);
        listview.setLayoutManager(mlayout);
        listview.setHasFixedSize(false);
        listview.setAdapter(renderer);
        mContainer = view;
        return view;
    }


    private float mdensity;

    public void setDensity(float density) {
        this.mdensity = density;
    }

    public void setNormalHeight(int mContainerOriginalHeight) {
        this.mContainerOriginalHeight = mContainerOriginalHeight;
    }

    public void setScrollContainer(ScrollView scroll) {
        this.scrollcontainer = scroll;
    }

    public MaterialListSection andRenderWith(RenderBinder rbinder) {
        this.renderer = rbinder;
        return this;
    }

    public MaterialListSection toggleListShown() {
        mstatusShown = !mstatusShown;
        showList(mstatusShown);
        return this;
    }

    private MaterialListSection showList(boolean t) {
        boolean expand = listview.getAdapter().getItemCount() > 0 && t;
        postAnimationListHeight(listview, expand);
        return this;
    }

    private int DURATION = 1000;

    private void postAnimationListHeight(final UltimateRecyclerView listrv, boolean expand) {
        final ValueAnimator anim;

        if (expand) {
            // anim = ValueAnimator.ofInt(listrv.getMeasuredHeight(), 300);
            anim = ValueAnimator.ofInt(mContainerOriginalHeight, 500);
        } else {
            anim = ValueAnimator.ofInt(500, mContainerOriginalHeight);
        }
        anim.setInterpolator(new AccelerateDecelerateInterpolator());
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int val = (Integer) valueAnimator.getAnimatedValue();
                ViewGroup.MarginLayoutParams layoutParams1 = (ViewGroup.MarginLayoutParams) ((View) listrv).getLayoutParams();
                ViewGroup.MarginLayoutParams layoutParams2 = (ViewGroup.MarginLayoutParams) ((View) mContainer).getLayoutParams();
                //  ViewGroup.LayoutParams layoutParams = listrv.getLayoutParams();
                layoutParams2.height = val;
                layoutParams1.height = val;
                //listrv.setLayoutParams(layoutParams);
                listrv.requestLayout();
                mContainer.requestLayout();
            }
        });
        // anim.setDuration(DURATION);
        anim.start();
    }

    public static class RenderViewBindAdapter<TD> extends midUltimateAdapter<TD, RowBinder> {

        public static RenderViewBindAdapter newInstance(List<?> list) {
            return new RenderViewBindAdapter(list);
        }

        /**
         * dynamic object to start
         *
         * @param list the list source
         */
        public RenderViewBindAdapter(List list) {
            super(list);
        }

        @Override
        protected void withBindHolder(final RowBinder holder, final TD data, final int pos) {
            if (data instanceof String) {
                holder.text.setText((String) data);
            }
            holder.setOnSelectItem(pos);
        }

        /**
         * the layout id for the normal data
         *
         * @return the ID
         */
        @Override
        protected int getNormalLayoutResId() {
            return R.layout.section_sub_list_row;
        }

        @Override
        protected RowBinder newViewHolder(View view) {
            return new RowBinder(view);
        }
    }

    public static class RowBinder extends UltimateRecyclerviewViewHolder {
        public TextView text;
        public RelativeLayout section_relative_layout;

        public RowBinder(View itemView) {
            super(itemView);
            text = (TextView) itemView.findViewById(R.id.section_text);
            section_relative_layout = (RelativeLayout) itemView.findViewById(R.id.section_relative_layout);
        }

        public void setOnSelectItem(final int position) {
            section_relative_layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(TAG, "touch on position:" + position);
                }
            });
        }

    }
}
