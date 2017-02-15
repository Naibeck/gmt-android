package com.ctb.gmt.naibeck.guiamultiturismocentroamerica.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.view.View;

import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.model.CategoryPlace;

/**
 * This will be the representation of a RecyclerView containing another RecyclerView
 */
public class CategoryItemViewModel extends BaseObservable {
    private static final String TAG = CategoryItemViewModel.class.getName();

    private CategoryPlace mCategoryPlace;
    private CategoryItemViewModelListener mCategoryItemViewModelListener;

    private String mCategoryName;

    public CategoryItemViewModel(CategoryPlace mCategoryPlace,
                                 CategoryItemViewModelListener mCategoryItemViewModelListener) {
        this.mCategoryPlace = mCategoryPlace;
        this.mCategoryItemViewModelListener = mCategoryItemViewModelListener;

        this.mCategoryName = mCategoryPlace.getName();
    }

    @Bindable
    public String getCategoryName() {
        return mCategoryName;
    }

    public void onMoreClick(View view) {
        mCategoryItemViewModelListener.onMoreClick(mCategoryPlace);
    }

    public interface CategoryItemViewModelListener<T> {
        void onMoreClick(T item);
    }
}
