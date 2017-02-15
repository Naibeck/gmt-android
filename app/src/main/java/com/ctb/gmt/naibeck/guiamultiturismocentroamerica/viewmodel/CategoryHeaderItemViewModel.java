package com.ctb.gmt.naibeck.guiamultiturismocentroamerica.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.annotation.NonNull;
import android.view.View;

import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.model.CategoryPlace;

public class CategoryHeaderItemViewModel extends BaseObservable {
    private static final String TAG = PlaceItemViewModel.class.getName();

    private CategoryPlace mCategoryPlace;
    private OnHeaderClickListener mOnHeaderClickListener;

    private String mCategoryName;

    public CategoryHeaderItemViewModel(CategoryPlace mCategoryPlace,
                                       OnHeaderClickListener mOnHeaderClickListener) {
        this.mCategoryPlace = mCategoryPlace;
        this.mOnHeaderClickListener = mOnHeaderClickListener;
        this.mCategoryName = mCategoryPlace.getName();
    }

    @Bindable
    public String getCategoryName() {
        return mCategoryName;
    }

    @Bindable
    public void setCategoryName(String mCategoryName) {
        this.mCategoryName = mCategoryName;
    }

    public void onHeaderClick(View view) {
        mOnHeaderClickListener.onHeaderClick(mCategoryPlace);
    }

    public interface OnHeaderClickListener<T> {
        void onHeaderClick(@NonNull T item);
    }
}
