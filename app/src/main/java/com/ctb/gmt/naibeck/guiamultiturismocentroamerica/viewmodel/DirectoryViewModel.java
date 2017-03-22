package com.ctb.gmt.naibeck.guiamultiturismocentroamerica.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.util.Log;
import android.view.View;

import com.android.databinding.library.baseAdapters.BR;
import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.api.domain.CategoryDomain;
import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.model.CategoryPlace;
import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.ui.fragment.DirectoryFragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DirectoryViewModel extends BaseObservable{
    private static final String TAG = DirectoryViewModel.class.getName();

    private static DirectoryViewModel sInstance;

    private DirectoryFragment mDirectoryFragment;
    private CategoryDomain mCategoryDomain;
    private DirectoryListListener<CategoryPlace> mDirectoryListListener;

    private int mIsVisible;

    public static DirectoryViewModel getInstance(DirectoryFragment directoryFragment, CategoryDomain categoryDomain, DirectoryListListener<CategoryPlace> directoryListListener) {
        if (sInstance == null) {
            sInstance = new DirectoryViewModel(directoryFragment, categoryDomain, directoryListListener);
        }
        return sInstance;
    }

    public DirectoryViewModel(DirectoryFragment mDirectoryFragment, CategoryDomain mCategoryDomain, DirectoryListListener<CategoryPlace> mDirectoryListListener) {
        this.mDirectoryFragment = mDirectoryFragment;
        this.mCategoryDomain = mCategoryDomain;
        this.mDirectoryListListener = mDirectoryListListener;
    }

    @Bindable
    public int getIsVisible() {
        return mIsVisible;
    }

    public void setIsVisible(int mIsVisible) {
        this.mIsVisible = mIsVisible;
        notifyPropertyChanged(BR.isVisible);
    }

    public void onDataLoad() {
        mCategoryDomain.getDirectory(new Callback<CategoryPlace>() {
            @Override
            public void onResponse(Call<CategoryPlace> call, Response<CategoryPlace> response) {
                if (response.isSuccessful()) {
                    mDirectoryListListener.onDataLoad(response.body());
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

    public interface DirectoryListListener<T> {
        void onDataLoad(T item);
    }
}
