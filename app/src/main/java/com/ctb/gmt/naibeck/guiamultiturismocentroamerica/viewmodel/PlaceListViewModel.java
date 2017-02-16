package com.ctb.gmt.naibeck.guiamultiturismocentroamerica.viewmodel;

import android.databinding.BaseObservable;
import android.support.annotation.NonNull;
import android.util.Log;

import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.api.domain.CategoryDomain;
import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.model.CategoryPlace;
import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.ui.activity.PlaceListActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlaceListViewModel extends BaseObservable {
    private static final String TAG = PlaceListViewModel.class.getName();

    private PlaceListActivity mActivity;
    private PlaceListViewModelListener<CategoryPlace> mPlaceListViewModelListener;
    private CategoryDomain mCategoryDomain;

    public PlaceListViewModel(PlaceListActivity mActivity,
                              PlaceListViewModelListener<CategoryPlace> mPlaceListViewModelListener,
                              CategoryDomain mCategoryDomain) {
        this.mActivity = mActivity;
        this.mPlaceListViewModelListener = mPlaceListViewModelListener;
        this.mCategoryDomain = mCategoryDomain;
    }

    public void loadData(@NonNull String categoryId) {
        mCategoryDomain.getPlaceListFromSelectedType(categoryId, new Callback<CategoryPlace>() {
            @Override
            public void onResponse(Call<CategoryPlace> call, Response<CategoryPlace> response) {
                if (response.isSuccessful()) {
                    mPlaceListViewModelListener.onDataLoad(response.body());
                }
            }

            @Override
            public void onFailure(Call<CategoryPlace> call, Throwable t) {
                Log.d(TAG, "Error: ", t);
            }
        });
    }

    public interface PlaceListViewModelListener<T> {
        void onDataLoad(T item);
    }
}
