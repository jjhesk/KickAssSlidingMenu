package com.hypebeast.demoslidemenu.helpr;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.hypebeast.demoslidemenu.MainActivityDemo;
import com.hypebeast.demoslidemenu.R;
import com.hypebeast.demoslidemenu.demohb;

/**
 * Created by hesk on 10/7/15.
 */
public enum fastb {
    main(R.id.main, MainActivityDemo.class),
    hb(R.id.hbstyle, demohb.class),
    sys(R.id.system, MainActivityDemo.class),
    profile(R.id.profiletype, MainActivityDemo.class);
    private final Class<? extends AppCompatActivity> internalClazz;
    private final int menu_id;

    fastb(final @IdRes int menuid, final Class<? extends AppCompatActivity> triggerclassname) {
        this.internalClazz = triggerclassname;
        this.menu_id = menuid;
    }

    public int getId() {
        return menu_id;
    }

    public Class<? extends AppCompatActivity> getClassName() {
        return this.internalClazz;
    }

    public static void startfromSelectionMenu(final @IdRes int menuID, final Context mcontext, @Nullable final Bundle mbundle) {
        final int g = fastb.values().length;
        for (int i = 0; i < g; i++) {
            fastb bind = fastb.values()[i];
            if (bind.getId() == menuID) {
                if (mbundle != null) {
                    bind.newapp(mcontext, mbundle);
                } else {
                    bind.newapp(mcontext);
                }
                return;
            }
        }
    }

    public void newapp(final Context ctx) {
        final Intent in = new Intent(ctx, internalClazz);
        ctx.startActivity(in);
    }


    public void newapp(final Context ctx, final Bundle bun) {
        final Intent in = new Intent(ctx, internalClazz);
        in.putExtras(bun);
        ctx.startActivity(in);
    }

}
