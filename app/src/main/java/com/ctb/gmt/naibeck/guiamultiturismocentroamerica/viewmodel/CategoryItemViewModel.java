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
    private PlaceItemViewModel.PlaceViewModelListener.PlaceItemClickListener mPlaceItemClickListener;

    private String mCategoryName;

    public CategoryItemViewModel(CategoryPlace mCategoryPlace,
                                 PlaceItemViewModel.PlaceViewModelListener.PlaceItemClickListener placeItemClickListener) {
        this.mCategoryPlace = mCategoryPlace;
        this.mPlaceItemClickListener = placeItemClickListener;
        this.mCategoryName = mCategoryPlace.getName();
    }

    @Bindable
    public String getCategoryName() {
        return mCategoryName;
    }
}
