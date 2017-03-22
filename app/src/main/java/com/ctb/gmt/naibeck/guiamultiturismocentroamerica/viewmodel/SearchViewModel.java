package com.ctb.gmt.naibeck.guiamultiturismocentroamerica.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.util.Log;
import android.view.View;

import com.android.annotations.NonNull;
import com.android.databinding.library.baseAdapters.BR;
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
    private SearchListListener<CategoryPlace> mSearchListListener;

    private int mIsVisible;

    public static SearchViewModel getInstance(SearchFragment searchFragment, SearchDomain searchDomain, SearchListListener<CategoryPlace> categoryPlaceSearchListListener) {
        if (sInstance == null) {
            sInstance = new SearchViewModel(searchFragment, searchDomain, categoryPlaceSearchListListener);
        }
        return sInstance;
    }

    public SearchViewModel(SearchFragment mSearchFragment, SearchDomain mSearchDomain, SearchListListener<CategoryPlace> mSearchListListener) {
        this.mSearchFragment = mSearchFragment;
        this.mSearchDomain = mSearchDomain;
        this.mSearchListListener = mSearchListListener;
    }

    @Bindable
    public int getIsVisible() {
        return mIsVisible;
    }

    public void setIsVisible(int mIsVisible) {
        this.mIsVisible = mIsVisible;
        notifyPropertyChanged(BR.isVisible);
    }

    public void loadData(@NonNull String searchRequest) {
        mSearchDomain.getSearchResults(searchRequest, new Callback<CategoryPlace>() {
            @Override
            public void onResponse(Call<CategoryPlace> call, Response<CategoryPlace> response) {
                if (response.isSuccessful()) {
                    mSearchListListener.onDataLoad(response.body());
                    setIsVisible(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<CategoryPlace> call, Throwable t) {
                Log.d(TAG, "Error", t);
                setIsVisible(View.GONE);
            }
        });
    }

    public void onDestroyInstance() {
        if (sInstance != null) {
            sInstance = null;
        }
    }

    public interface SearchListListener<T> {
        void onDataLoad(T item);
    }
}
