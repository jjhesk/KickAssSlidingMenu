package com.asynhkm.productchecker;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.preference.PreferenceActivity;

import java.util.Locale;

/**
 * Created by Hesk on 24/11/2014.
 */
public class PreferenceControl extends PreferenceActivity implements SharedPreferences.OnSharedPreferenceChangeListener {
    public static void updateLanguage(Context context, String idioma) {
        if (!"".equals(idioma)) {
            if ("castella".equals(idioma)) {
                idioma = "es_ES";
            } else if ("catala".equals(idioma)) {
                idioma = "ca_ES";
            }
            Locale locale = new Locale(idioma);
            Locale.setDefault(locale);
            Configuration config = new Configuration();
            config.locale = locale;
            context.getResources().updateConfiguration(config, null);
        }
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
        getPreferenceScreen().getSharedPreferences()
                .registerOnSharedPreferenceChangeListener(this);
    }
}
