package com.arman.astro;

import android.app.Application;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.interceptors.HttpLoggingInterceptor.Level;
import com.arman.astro.data.DataManager;


/**
 * Created by Arman on 5/16/2017.
 */

public class AstroApplication extends Application {

    private static final String PREF_KEY = "ASTRO_PREF_KEY";

    DataManager dataManager;

    @Override
    public void onCreate() {
        super.onCreate();

        AndroidNetworking.initialize(getApplicationContext());
        if (BuildConfig.DEBUG) {
            AndroidNetworking.enableLogging(Level.BODY);
        }

        dataManager = new DataManager(this, PREF_KEY);
    }

    public DataManager getDataManager() {
        return dataManager;
    }
}
