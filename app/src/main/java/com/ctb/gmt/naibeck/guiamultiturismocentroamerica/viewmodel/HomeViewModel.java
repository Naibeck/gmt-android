package com.ctb.gmt.naibeck.guiamultiturismocentroamerica.viewmodel;

import android.databinding.BaseObservable;
import android.support.annotation.NonNull;

import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.ui.fragment.HomeFragment;

public class HomeViewModel extends BaseObservable {
    private static final String TAG = HomeViewModel.class.getName();

    private static HomeViewModel sInstance;

    private HomeFragment mFragment;

    public static HomeViewModel getInstance(@NonNull HomeFragment fragment) {
        if (sInstance != null) {
            return sInstance;
        }

        sInstance = new HomeViewModel(fragment);
        return sInstance;

    }

    public HomeViewModel(HomeFragment mFragment) {
        this.mFragment = mFragment;
    }

    public void removeInstance() {
        if (sInstance != null) {
            sInstance = null;
        }
    }
}
