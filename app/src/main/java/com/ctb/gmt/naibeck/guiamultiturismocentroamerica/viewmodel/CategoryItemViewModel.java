package com.ctb.gmt.naibeck.guiamultiturismocentroamerica.viewmodel;

import android.databinding.BaseObservable;

/**
 * This will be the representation of a RecyclerView containing another RecyclerView
 */
public class CategoryItemViewModel extends BaseObservable {
    private static final String TAG = CategoryItemViewModel.class.getName();

    private static CategoryItemViewModel sInstance;

    public static CategoryItemViewModel getInstance() {
        if (sInstance != null) {
            return sInstance;
        }

        sInstance = new CategoryItemViewModel();
        return sInstance;
    }
}
