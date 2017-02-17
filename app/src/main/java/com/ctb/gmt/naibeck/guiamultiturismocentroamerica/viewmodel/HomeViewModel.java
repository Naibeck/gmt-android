package com.ctb.gmt.naibeck.guiamultiturismocentroamerica.viewmodel;

import android.databinding.BaseObservable;
import android.support.annotation.NonNull;

import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.ui.fragment.HomeFragment;
import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.utility.GmtSharedPreferences;

public class HomeViewModel extends BaseObservable {
    private static final String TAG = HomeViewModel.class.getName();

    private static HomeViewModel sInstance;

    private HomeFragment mFragment;
    private GmtSharedPreferences mSharedPreferences;

    public static HomeViewModel getInstance(@NonNull HomeFragment fragment,
                                           @NonNull GmtSharedPreferences preferences) {
        if (sInstance != null) {
            return sInstance;
        }

        sInstance = new HomeViewModel(fragment, preferences);
        return sInstance;

    }

    public HomeViewModel(HomeFragment mFragment, GmtSharedPreferences mSharedPreferences) {
        this.mFragment = mFragment;
        this.mSharedPreferences = mSharedPreferences;
    }

    public void removeInstance() {
        if (sInstance != null) {
            sInstance = null;
        }
    }
}
