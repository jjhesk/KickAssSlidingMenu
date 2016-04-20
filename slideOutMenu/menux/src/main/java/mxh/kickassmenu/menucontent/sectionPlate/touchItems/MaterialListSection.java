package mxh.kickassmenu.menucontent.sectionPlate.touchItems;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.marshalchen.ultimaterecyclerview.UltimateRecyclerView;
import com.marshalchen.ultimaterecyclerview.layoutmanagers.CustomLinearLayoutManager;
import com.marshalchen.ultimaterecyclerview.quickAdapter.easyRegularAdapter;

import java.util.List;

import mxh.kickassmenu.R;
import mxh.kickassmenu.Util.LockableScrollView;
import mxh.kickassmenu.menucontent.ImaterialBinder;

/**
 * Created by hesk on 25/6/15.
 * from the HB STORE project 2015
 */
public class MaterialListSection<TD, CustomTextView extends TextView, RenderBinder extends MaterialListSection.RenderViewBindAdapter> implements ImaterialBinder {
    public CustomTextView text, notificationtext;
    public ImageView indicator, icon;
    public UltimateRecyclerView listview;
    public static String TAG = "txtNavigation";
    private RenderBinder renderer;
    private boolean mstatusShown, animate_indicator;
    private View mContainer;
    private int mContainerOriginalHeight, itemcounts, bottomNavigationHeight;
    private LockableScrollView scrollcontainer;
    private int DURATION = 900;
    private ValueAnimator anim;


    public MaterialListSection() {
        mstatusShown = false;
    }

    @Override
    public int getLayout() {
        return R.layout.section_list_header;
    }

    private int submenu_item_height = 0, containerTHeight = 0;


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

        submenu_item_height = view.getContext().getResources().getDimensionPixelSize(R.dimen.sectionNormalHeight);

        animate_indicator = true;

        final Resources resources = view.getContext().getResources();
        int resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android");
        if (resourceId > 0) {
            bottomNavigationHeight = resources.getDimensionPixelSize(resourceId);
        }
        return view;
    }


    private float mdensity;

    public void setDensity(final float density) {
        this.mdensity = density;
    }

    public void setAnimateIndicator(final boolean b) {
        this.animate_indicator = b;
    }

    public void setNormalHeight(int mContainerOriginalHeight) {
        this.mContainerOriginalHeight = mContainerOriginalHeight;
    }

    public void setScrollContainer(LockableScrollView scroll) {
        this.scrollcontainer = scroll;
        this.containerTHeight = scroll.getChildAt(0).getHeight();
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
        itemcounts = listview.getAdapter().getItemCount();
        this.containerTHeight = this.scrollcontainer.getChildAt(0).getHeight();
        if (itemcounts > 0) {
            postAnimationListHeight(listview, t);
        }
        return this;
    }


    private void animateIndicator(final boolean open) {
        if (animate_indicator) {
            //indicator.clearAnimation();
            final Animation r = new RotateAnimation(indicator.getRotation(),
                    !open ? 0 : 90f, open ? Animation.RELATIVE_TO_SELF : Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            r.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    ViewCompat.setRotation(indicator, !open ? 0f : 90f);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
            r.setDuration(500);
            indicator.startAnimation(r);
        }
    }

    private void postAnimationListHeight(final UltimateRecyclerView listrv, final boolean expand) {
        final int listviewOriginalHeight = itemcounts * submenu_item_height;
        final int heightListView = Math.min(containerTHeight - mContainerOriginalHeight, listviewOriginalHeight);
        if (expand) {
            anim = ValueAnimator.ofInt(mContainerOriginalHeight, heightListView);
        } else {
            anim = ValueAnimator.ofInt(heightListView, mContainerOriginalHeight);
        }
        animateIndicator(expand);
        anim.setInterpolator(new AccelerateDecelerateInterpolator());
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int val = (Integer) valueAnimator.getAnimatedValue();
                ViewGroup.MarginLayoutParams layoutParams1 = (ViewGroup.MarginLayoutParams) ((View) listrv).getLayoutParams();
                ViewGroup.MarginLayoutParams layoutParams2 = (ViewGroup.MarginLayoutParams) ((View) mContainer).getLayoutParams();
                //  ViewGroup.LayoutParams layoutParams = listrv.getLayoutParams();
                layoutParams2.height = val;
                layoutParams1.height = val - mContainerOriginalHeight;
                //listrv.setLayoutParams(layoutParams);
                listrv.requestLayout();
                mContainer.requestLayout();
                scrollcontainer.setScrollY(-val + mContainerOriginalHeight);
            }
        });
        anim.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                scrollcontainer.setScrollingEnabled(!expand);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        anim.setDuration(DURATION);
        anim.start();
    }

    public static class RenderViewBindAdapter<TD> extends easyRegularAdapter<TD, RowBinder> {

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

}
