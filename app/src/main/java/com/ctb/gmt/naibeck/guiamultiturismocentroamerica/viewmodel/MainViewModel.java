package com.ctb.gmt.naibeck.guiamultiturismocentroamerica.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.ui.activity.SearchActivity;
import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.ui.fragment.SearchFragment;

/**
 * Created by Kevin Gomez on 3/23/2017.
 */

public class MainViewModel extends BaseObservable {
    private static MainViewModel sInstance;
    
    private String mSearchContent;

    public static MainViewModel getInstance() {
        if (sInstance == null) {
            sInstance = new MainViewModel();
        }

        return sInstance;
    }

    @Bindable
    public String getSearchContent() {
        return mSearchContent;
    }

    public void setSearchContent(String mSearchContent) {
        this.mSearchContent = mSearchContent;
    }

    public void goSearchActivity(Context context) {
        Intent intent = new Intent(context, SearchActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra(SearchFragment.SEARCH_REQUEST, getSearchContent());
        context.startActivity(intent);
    }

    public void removeIsntance() {
        if (sInstance != null) {
            sInstance = null;
        }
    }
}
