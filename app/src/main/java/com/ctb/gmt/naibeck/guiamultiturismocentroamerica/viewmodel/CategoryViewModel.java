package com.ctb.gmt.naibeck.guiamultiturismocentroamerica.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;

import com.android.databinding.library.baseAdapters.BR;
import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.api.domain.CategoryDomain;
import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.model.TourismCategory;
import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.ui.fragment.CategoryFragment;
import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.utility.GmtSharedPreferences;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryViewModel extends BaseObservable {
    private static final String TAG = CategoryViewModel.class.getName();

    private static CategoryViewModel sInstance;

    private CategoryFragment mCategoryFragment;
    private CategoryListListener<TourismCategory> mCategoryListListener;
    private CategoryDomain mCategoryDomain;

    private int mIsVisible;
    private int mConnectionVisibility;

    public static CategoryViewModel getInstance(@NonNull CategoryFragment fragment,
                                                @NonNull CategoryListListener<TourismCategory> listener,
                                                @NonNull CategoryDomain domain) {
        if (sInstance != null) {
            return sInstance;
        }
        sInstance = new CategoryViewModel(fragment, listener, domain);
        return sInstance;
    }

    private CategoryViewModel(CategoryFragment mCategoryFragment,
                              CategoryListListener<TourismCategory> mCategoryListListener,
                              CategoryDomain mCategoryDomain) {
        this.mCategoryFragment = mCategoryFragment;
        this.mCategoryListListener = mCategoryListListener;
        this.mCategoryDomain = mCategoryDomain;
        this.mConnectionVisibility = View.GONE;
    }

    @Bindable
    public int getIsVisible() {
        return mIsVisible;
    }

    @Bindable
    private void setIsVisible(int mIsVisible) {
        this.mIsVisible = mIsVisible;
        notifyPropertyChanged(BR.isVisible);
    }

    @Bindable
    public int getConnectionVisibility() {
        return mConnectionVisibility;
    }

    @Bindable
    public void setConnectionVisibility(int mConnectionVisibility) {
        this.mConnectionVisibility = mConnectionVisibility;
        notifyPropertyChanged(BR.connectionVisibility);
    }

    public void loadData(@NonNull String categoryId) {
        setIsVisible(View.VISIBLE);
        mCategoryDomain.getCategoryPlace(categoryId, new Callback<TourismCategory>() {
            @Override
            public void onResponse(Call<TourismCategory> call, Response<TourismCategory> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, "Data loaded");
                    mCategoryListListener.onDataLoad(response.body());
                }
                setIsVisible(View.INVISIBLE);
            }

            @Override
            public void onFailure(Call<TourismCategory> call, Throwable t) {
                Log.d(TAG, "Error", t);
                setIsVisible(View.INVISIBLE);
                setConnectionVisibility(View.VISIBLE);
            }
        });
    }

    public void retryClick(View view) {
        mCategoryListListener.onRetryClick();
        if (mConnectionVisibility == View.VISIBLE) {
            setConnectionVisibility(View.GONE);
        }
    }

    public void onDestroyInstance() {
        if (sInstance != null) {
            sInstance = null;
        }
    }

    public interface CategoryListListener<T> {
        void onDataLoad(T item);
        void onRetryClick();
    }
}
