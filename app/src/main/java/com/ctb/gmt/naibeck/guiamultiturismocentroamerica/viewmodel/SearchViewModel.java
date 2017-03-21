package com.ctb.gmt.naibeck.guiamultiturismocentroamerica.viewmodel;

import android.databinding.BaseObservable;
import android.util.Log;

import com.android.annotations.NonNull;
import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.api.domain.SearchDomain;
import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.model.CategoryPlace;
import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.ui.fragment.SearchFragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Kevin Gomez on 3/16/2017.
 */

public class SearchViewModel extends BaseObservable {
    private static String TAG = SearchViewModel.class.getName();
    private static SearchViewModel sInstance;

    private SearchFragment mSearchFragment;
    private SearchDomain mSearchDomain;

    public static SearchViewModel getInstance(SearchFragment searchFragment, SearchDomain searchDomain) {
        if (sInstance == null) {
            sInstance = new SearchViewModel(searchFragment, searchDomain);
        }
        return sInstance;
    }

    public SearchViewModel(SearchFragment mSearchFragment, SearchDomain mSearchDomain) {
        this.mSearchFragment = mSearchFragment;
        this.mSearchDomain = mSearchDomain;
    }

    public void loadData(@NonNull String searchRequest) {
        mSearchDomain.getSearchResults(searchRequest, new Callback<CategoryPlace>() {
            @Override
            public void onResponse(Call<CategoryPlace> call, Response<CategoryPlace> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, response.body().toString());
                }
            }

            @Override
            public void onFailure(Call<CategoryPlace> call, Throwable t) {

            }
        });
    }
}
