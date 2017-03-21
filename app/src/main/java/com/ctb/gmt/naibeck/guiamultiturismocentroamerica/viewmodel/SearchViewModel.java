package com.ctb.gmt.naibeck.guiamultiturismocentroamerica.viewmodel;

import android.databinding.BaseObservable;

import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.ui.fragment.SearchFragment;

/**
 * Created by Kevin Gomez on 3/16/2017.
 */

public class SearchViewModel extends BaseObservable {
    private static String TAG = SearchViewModel.class.getName();
    private static SearchViewModel sInstance;

    private SearchFragment mSearchFragment;

    public static SearchViewModel getInstance(SearchFragment searchFragment) {
        if (sInstance == null) {
            sInstance = new SearchViewModel(searchFragment);
        }
        return sInstance;
    }

    public SearchViewModel(SearchFragment mSearchFragment) {
        this.mSearchFragment = mSearchFragment;
    }
}
