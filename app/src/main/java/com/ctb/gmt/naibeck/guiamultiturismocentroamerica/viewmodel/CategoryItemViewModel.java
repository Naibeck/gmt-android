package com.ctb.gmt.naibeck.guiamultiturismocentroamerica.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.model.CategoryPlace;

/**
 * This will be the representation of a RecyclerView containing another RecyclerView
 */
public class CategoryItemViewModel extends BaseObservable {
    private static final String TAG = CategoryItemViewModel.class.getName();


    private CategoryPlace mCategoryPlace;

    private String mCategoryName;

    public CategoryItemViewModel(CategoryPlace mCategoryPlace) {
        this.mCategoryPlace = mCategoryPlace;
        this.mCategoryName = mCategoryPlace.getName();
    }

    @Bindable
    public String getCategoryName() {
        return mCategoryName;
    }
}
