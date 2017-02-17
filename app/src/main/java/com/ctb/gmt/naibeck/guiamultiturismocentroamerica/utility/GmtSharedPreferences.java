package com.ctb.gmt.naibeck.guiamultiturismocentroamerica.utility;

import android.content.Context;
import android.content.SharedPreferences;
import android.location.Location;
import android.support.annotation.NonNull;

import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.MultiturismoApplication;
import com.google.gson.Gson;

public class GmtSharedPreferences {
    private static final String TAG = GmtSharedPreferences.class.getName();

    private static final String NAME = "gmtSharedPreferences";
    private static final String LAST_KNOWN_LOCATION = "lastLocation";

    private static GmtSharedPreferences sInstance;
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

    private Context mContext;
    private Gson mGson;

    public static GmtSharedPreferences getInstance(@NonNull Context context) {
        sInstance = new GmtSharedPreferences(context);
        return sInstance;
    }

    public GmtSharedPreferences(@NonNull Context mContext) {
        this.mContext = mContext;
        initSharedPreferences();
    }

    private void initSharedPreferences() {
        mSharedPreferences = mContext.getSharedPreferences(NAME, Context.MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();
        mGson = MultiturismoApplication.getsInstance().getGson();
    }

    private String getStringPreference(@NonNull String name) {
        return mSharedPreferences.getString(name, "");
    }

    private void putStringPreference(@NonNull String name, @NonNull String value) {
        mEditor.putString(name, value).apply();
    }

    public Location getLastStoredLocation() {
        String json = getStringPreference(LAST_KNOWN_LOCATION);
        if (json == null) {
            return null;
        }

        return mGson.fromJson(json, Location.class);
    }

    public void putLastStoredLocation(@NonNull Location location) {
        String json;
        if (location == null) {
            json = null;
        } else {
            json = mGson.toJson(location);
        }
        putStringPreference(LAST_KNOWN_LOCATION, json);
    }

    public void removeInstance() {
        if (sInstance != null) {
            sInstance = null;
        }
    }
}
