package com.ctb.gmt.naibeck.guiamultiturismocentroamerica.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;

import com.android.databinding.library.baseAdapters.BR;
import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.api.domain.CategoryDomain;
import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.model.CategoryPlace;
import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.ui.activity.PlaceListActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlaceListViewModel extends BaseObservable {
    private static final String TAG = PlaceListViewModel.class.getName();

    private static PlaceListViewModel sInstance;

    private PlaceListActivity mActivity;
    private PlaceListViewModelListener<CategoryPlace> mPlaceListViewModelListener;
    private CategoryDomain mCategoryDomain;

    private int mIsVisible;

    public static PlaceListViewModel getInstance(PlaceListActivity mActivity,
                                                 PlaceListViewModelListener<CategoryPlace> mPlaceListViewModelListener,
                                                 CategoryDomain mCategoryDomain) {
        if (sInstance != null) {
            return sInstance;
        }

        sInstance = new PlaceListViewModel(mActivity, mPlaceListViewModelListener, mCategoryDomain);
        return sInstance;
    }

    public PlaceListViewModel(PlaceListActivity mActivity,
                              PlaceListViewModelListener<CategoryPlace> mPlaceListViewModelListener,
                              CategoryDomain mCategoryDomain) {
        this.mActivity = mActivity;
        this.mPlaceListViewModelListener = mPlaceListViewModelListener;
        this.mCategoryDomain = mCategoryDomain;
    }

    @Bindable
    public int getIsVisible() {
        return mIsVisible;
    }

    @Bindable
    public void setIsVisible(int mIsVisible) {
        this.mIsVisible = mIsVisible;
        notifyPropertyChanged(BR.isVisible);
    }

    public void loadData(@NonNull String categoryId) {
        setIsVisible(View.VISIBLE);
        mCategoryDomain.getPlaceListFromSelectedType(categoryId, new Callback<CategoryPlace>() {
            @Override
            public void onResponse(Call<CategoryPlace> call, Response<CategoryPlace> response) {
                if (response.isSuccessful()) {
                    mPlaceListViewModelListener.onDataLoad(response.body());
                }
                setIsVisible(View.INVISIBLE);
                notifyChange();
            }

            @Override
            public void onFailure(Call<CategoryPlace> call, Throwable t) {
                Log.d(TAG, "Error: ", t);
                setIsVisible(View.INVISIBLE);
                notifyChange();
            }
        });
    }

    public void onDestroyInstance() {
        if (sInstance != null) {
            sInstance = null;
        }
    }

    public interface PlaceListViewModelListener<T> {
        void onDataLoad(T item);
    }
}
