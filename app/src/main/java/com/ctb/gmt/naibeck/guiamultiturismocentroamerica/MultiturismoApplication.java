package com.ctb.gmt.naibeck.guiamultiturismocentroamerica;

import android.app.Application;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class MultiturismoApplication extends Application {
    private static final String TAG = MultiturismoApplication.class.getName();

    private static MultiturismoApplication sInstance;

    private Gson mGson;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
    }

    public Gson getGson() {
        if (mGson == null) {
            GsonBuilder builder = new GsonBuilder();
            mGson = builder.create();
        }
        return mGson;
    }

    public static MultiturismoApplication getsInstance() {
        return sInstance;
    }
}
