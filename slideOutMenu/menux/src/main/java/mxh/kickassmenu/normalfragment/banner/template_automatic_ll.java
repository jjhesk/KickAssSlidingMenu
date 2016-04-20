package mxh.kickassmenu.normalfragment.banner;


import android.app.Fragment;
import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TableLayout;
import android.widget.TableRow;

import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Iterator;

import mxh.kickassmenu.R;

/**
 * Created by zJJ on 2/19/2016.
 */
public abstract class template_automatic_ll extends Fragment {
    protected TableLayout ll;
    protected ProgressBar mProgress;
    protected ArrayList<bind> list_configuration = new ArrayList<>();
    protected Point screen_size;
    protected int row_height;

    abstract protected void measureScreen();

    /**
     * this main screen contains IDs
     * 1.  lylib_ui_loading_circle  - ProgressBar
     * 2.   lylib_list_uv - TableLayout
     *
     * @return main screen id
     */
    @LayoutRes
    abstract protected int mainScreenLayoutId();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        /* Inflate the layout for this fragment */
        View view = inflater.inflate(mainScreenLayoutId(), container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mProgress = (ProgressBar) view.findViewById(R.id.lylib_ui_loading_circle);
        ll = (TableLayout) view.findViewById(R.id.lylib_list_uv);
        measureScreen();
        row_height = calculateRowHeight();
        onLoadData(savedInstanceState);
    }

    protected TableRow newTempHolderBy2() {
        TableRow linearLayout_temp = new TableRow(getActivity());
        linearLayout_temp.setLayoutParams(new TableRow.LayoutParams(screen_size.x, calculateRowHeightBy2()));
        return linearLayout_temp;
    }

    protected TableRow newTempHolder() {
        TableRow linearLayout_temp = new TableRow(getActivity());
        linearLayout_temp.setLayoutParams(new TableRow.LayoutParams(screen_size.x, row_height));
        return linearLayout_temp;
    }

    protected abstract Picasso getPicassoClient();

    protected ImageView newRelativeLayout(final bind mBind, int size) {
        ImageView imview = new ImageView(getActivity());
        imview.setLayoutParams(findBoxWidth(mBind, size));
        getPicassoClient()
                .load(mBind.image)
                .memoryPolicy(MemoryPolicy.NO_STORE, MemoryPolicy.NO_CACHE)
                .into(imview);
        imview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                trigger_link(mBind);
            }
        });
        imview.setScaleType(ImageView.ScaleType.CENTER_CROP);
        return imview;
    }

    protected int calculateRowHeight() {
        return calculateRowHeightBy2();
    }

    protected int calculateRowHeightBy2() {
        return (int) ((float) screen_size.x / (float) 2);
    }

    protected int calculateRowHeightBy3() {
        return (int) ((float) screen_size.x / (float) 3);
    }

    protected TableRow.LayoutParams findBoxWidth(final bind mbind, int size) {
        if (mbind.size == bind.HALF) {
            return new TableRow.LayoutParams(size == bind.HALF ? row_height : screen_size.x, row_height);
        } else {
            return new TableRow.LayoutParams(size == bind.HALF ? row_height : screen_size.x, calculateRowHeightBy2());
        }
    }

    protected void trigger_link(bind bindobject) {

    }

    abstract protected void onLoadData(Bundle b);

    protected void tabletTilePortrait() {
        int sizeNow, index = 0, halfSizeOnHold = 0;
        boolean cell_open = false;
        Iterator<bind> loop = list_configuration.iterator();
        TableRow temp_row = newTempHolder();
        while (loop.hasNext()) {
            bind dlp = loop.next();
            sizeNow = dlp.size;
            if (sizeNow == bind.FULL) {
                if (cell_open) {
                    //close half
                    temp_row = newTempHolderBy2();
                    ll.addView(temp_row, new TableLayout.LayoutParams(screen_size.x, calculateRowHeightBy2()));
                    cell_open = false;
                    halfSizeOnHold = 0;
                }
                ll.addView(newRelativeLayout(dlp, sizeNow));
            } else if (sizeNow == bind.HALF) {
                if (!cell_open) {
                    temp_row = newTempHolder();
                    cell_open = true;
                }
                halfSizeOnHold++;
                //adding view to layout
                temp_row.addView(newRelativeLayout(dlp, sizeNow));
            }

            if (index == list_configuration.size() - 1 && cell_open || cell_open && halfSizeOnHold >= 3) {
                ll.addView(temp_row, new TableLayout.LayoutParams(screen_size.x, row_height));
                cell_open = false;
                halfSizeOnHold = 0;
            }

            index++;
        }
    }

    protected void phoneTileRender() {
        int sizeNow, index = 0, halfSizeOnHold = 0;
        boolean cell_open = false;
        Iterator<bind> loop = list_configuration.iterator();
        TableRow temp_row = newTempHolder();
        while (loop.hasNext()) {
            bind dlp = loop.next();
            sizeNow = dlp.size;
            if (sizeNow == bind.FULL) {
                if (cell_open) {
                    //close half
                    ll.addView(temp_row, new TableLayout.LayoutParams(screen_size.x, row_height));
                    cell_open = false;
                    halfSizeOnHold = 0;
                }
                ll.addView(newRelativeLayout(dlp, sizeNow));
            } else if (sizeNow == bind.HALF) {
                if (!cell_open) {
                    temp_row = newTempHolder();
                    cell_open = true;
                }
                halfSizeOnHold++;
                //adding view to layout
                temp_row.addView(newRelativeLayout(dlp, sizeNow));
            }

            if (index == list_configuration.size() - 1 && cell_open || cell_open && halfSizeOnHold >= 2) {
                ll.addView(temp_row, new TableLayout.LayoutParams(screen_size.x, row_height));
                cell_open = false;
                halfSizeOnHold = 0;
            }

            index++;
        }
    }

    protected abstract void render();

    protected void onLoadComplete() {
        mProgress.animate().alpha(0f).withEndAction(new Runnable() {
            @Override
            public void run() {
                try {
                    mProgress.setVisibility(View.GONE);
                    render();
                } catch (Exception e) {
                    Log.d("dialog", e.getMessage());
                }
            }
        });
    }
}
