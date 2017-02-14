package com.ctb.gmt.naibeck.guiamultiturismocentroamerica.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;

import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.R;
import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.databinding.FragmentCategoryBinding;
import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.model.TourismCategory;
import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.ui.activity.MainActivity;
import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.viewmodel.CategoryViewModel;

public class CategoryFragment extends BaseFragment<FragmentCategoryBinding, CategoryViewModel> implements CategoryViewModel.CategoryListListener<TourismCategory> {
    private static final String TAG = CategoryFragment.class.getName();

    private String mCategoryId;

    public static CategoryFragment getInstance(@NonNull String categoryId) {
        CategoryFragment fragment = new CategoryFragment();
        Bundle args = new Bundle();
        args.putString(MainActivity.SELECTED_CATEGORY, categoryId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_category;
    }

    @Override
    public CategoryViewModel getViewModel() {
        return CategoryViewModel.getInstance(this, getGmtPreferences(), this, getCategoryDomain());
    }

    @Override
    public void setViewModelToBinding() {
        getBinding().setViewModel(getViewModel());
    }

    @Override
    public void initComponents() {
        super.initComponents();
        mCategoryId = getArguments().getString(MainActivity.SELECTED_CATEGORY);
        getViewModel().loadData(mCategoryId);
    }

    @Override
    public void onDataLoad(TourismCategory item) {
        Log.d(TAG, "onDataLoad: " + item.getCategoryPlaceList().get(0).getName());
    }
}
