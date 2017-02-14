package com.ctb.gmt.naibeck.guiamultiturismocentroamerica.viewmodel;

import android.databinding.BaseObservable;
import android.support.annotation.NonNull;

public class CategoryViewModel extends BaseObservable {
    private static final String TAG = CategoryViewModel.class.getName();

    private static CategoryViewModel sInstance;

    public static CategoryViewModel getInstance() {
        if (sInstance != null) {
            return sInstance;
        }

        sInstance = new CategoryViewModel();
        return sInstance;
    }
}
