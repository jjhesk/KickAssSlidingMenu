package com.asynhkm.utility;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

/**
 * Created by hesk on 3/9/15.
 * also extend Router and some other development toolings
 */
public class BaseActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        String url;

        Bundle extras = intent.getExtras();
        if (extras.containsKey("url")) {
            url = extras.getString("url");
        } else {
            Uri data = intent.getData();
            String protocol = data.getScheme() + "://";
            url = data.toString().replaceFirst(protocol, "");
            if (Router.sharedRouter().getRootUrl() != null) {
                Router.sharedRouter().open(Router.sharedRouter().getRootUrl());
            }
        }

        Router.sharedRouter().open(url);

        setResult(RESULT_OK, null);
        finish();
    }

}
