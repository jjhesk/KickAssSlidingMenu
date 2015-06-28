package com.hkm.hbstore.life;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;

import com.hkm.hbstore.BuildConfig;
import com.parse.Parse;
import com.parse.ParseCrashReporting;
import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParsePush;
import com.parse.SaveCallback;
import com.squareup.picasso.Picasso;

import java.net.CookieManager;

import io.realm.Realm;

/**
 * Created by hesk on 2/2/15.
 */
public class LifeCycleApp extends Application {

    private static CookieManager cookieManager;
    public static GoogleAnalytics analytics;
    public static Tracker tracker;


    public static CookieManager getHBCookieMgr() {
        return cookieManager;
    }

    protected Picasso pic;
    protected Realm realm;

    @Override
    public void onCreate() {
        super.onCreate();
        // Enable Crash Reporting
        ParseCrashReporting.enable(this);
        // ENABLE PARSE IN HERE
        Parse.enableLocalDatastore(this);
        // Enable and initialize the parse application
        //Parse.initialize(this, BuildConfig.PARSE_APPLICATION_ID, BuildConfig.PARSE_CLIENT_KEY);
        //CookieHandler.setDefault(cookieManager);
        pic = Picasso.with(this);
        // Save the current Installation to Parse.
        ParseInstallation.getCurrentInstallation().saveInBackground();
        // When users indicate they are Giants fans, we subscribe them to that channel.
        ParsePush.subscribeInBackground("testingchannel", new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null) {
                            Log.d("com.parse.push", "successfully subscribed to the broadcast channel.");
                        } else {
                            Log.e("com.parse.push", "failed to subscribe for push", e);
                        }
                    }
                }
        );

       /* SP = getSharedPreferences(Config.save_pref_private_id, Context.MODE_PRIVATE);
        mac_id = Tool.get_mac_address(this);
        DisqusLogin = SP.getString(Config.save_login, "");
        pass = SP.getString(Config.save_password, "");*/
        //RealmGetData();
        googleAnalytics();
        initializeDisQus();
    }

    public Tracker getTracker() {
        return tracker;
    }

    private void googleAnalytics() {
        analytics = GoogleAnalytics.getInstance(this);
        analytics.setLocalDispatchPeriod(1800);
        tracker = analytics.newTracker(BuildConfig.GOOGLE_TRACKER); // Replace with actual tracker/property Id
        tracker.enableExceptionReporting(true);
        tracker.enableAdvertisingIdCollection(false);
        tracker.enableAutoActivityTracking(true);
    }

    private void initializeDisQus() {
        try {
         //   disqusConfigurations = new ApiConfig(BuildConfig.DISQUS_API_KEY, RestAdapter.LogLevel.BASIC);
         //   disqusConfigurations.setApiSecret(BuildConfig.DISQUS_SECRET);
        //    disqusConfigurations.setRedirectUri(Config.base_en);

            final SharedPreferences spf = PreferenceManager.getDefaultSharedPreferences(this);
           // retention.init(spf.getString("languagecode", ""), this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public Picasso getInstancePicasso() {
        return pic;
    }

    private void RealmGetData() {
        try {
            realm = Realm.getInstance(this);
         /*  realm.beginTransaction();
        RealmQuery<WishedItem> wishedItemRealmQuery = realm.where(WishedItem.class);
        RealmResults<WishedItem> wishedItemRealmResults = wishedItemRealmQuery.findAll();
        retent.WItems.clear();
        retent.WItems.addAll(wishedItemRealmResults);
        realm.commitTransaction();*/

            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                 /*   RealmQuery<WishedItem> wishedItemRealmQuery = realm.where(WishedItem.class);
                    RealmResults<WishedItem> wishedItemRealmResults = wishedItemRealmQuery.findAll();
                    retention.WItems.clear();
                    retention.WItems.addAll(wishedItemRealmResults);


                    RealmQuery<elementHome> elementHomeRealmQuery = realm.where(elementHome.class);
                    RealmResults<elementHome> elementHomeRealmResults = elementHomeRealmQuery.findAll();
                    retention.home_elements.clear();
                    retention.home_elements.addAll(elementHomeRealmResults);

                    APPSettings appSettingsItem = realm.where(APPSettings.class).findAll().last();
                    if (appSettingsItem != null)
                        retention.appSettings.setShopping_bag_current_item(appSettingsItem.getShopping_bag_current_item());
*/
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initParse() {
        /*ParsePush.subscribeInBackground("", new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    Log.d(Config.PARSETAG, "successfully subscribed to the broadcast channel.");
                } else {
                    Log.e(Config.PARSETAG, "failed to subscribe for push", e);
                }
            }
        });*/
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }


}
