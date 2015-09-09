package com.hypebeast.demoslidemenu.pages;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.hkm.slidingmenulib.Util.Utils;
import com.hypebeast.demoslidemenu.CommonSingle;
import com.hypebeast.demoslidemenu.R;

/**
 * Created by hesk on 23/6/15.
 */
public class mainpageDemo extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_main, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button bIntent = (Button) view.findViewById(R.id.openNewIntent);
        bIntent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.routeSinglePage("nothing", getActivity(), CommonSingle.class);
            }
        });
    }
}
