package com.ctb.gmt.naibeck.guiamultiturismocentroamerica.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.R;
import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.databinding.FragmentCategoryBinding;
import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.model.CategoryPlace;
import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.model.Places;
import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.model.TourismCategory;
import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.ui.activity.MainActivity;
import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.ui.activity.PlaceListActivity;
import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.ui.adapter.CategoryAdapter;
import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.ui.adapter.HeaderCategoryAdapter;
import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.viewmodel.CategoryHeaderItemViewModel;
import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.viewmodel.CategoryItemViewModel;
import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.viewmodel.CategoryViewModel;
import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.viewmodel.PlaceItemViewModel;

public class CategoryFragment extends BaseFragment<FragmentCategoryBinding, CategoryViewModel>
        implements CategoryViewModel.CategoryListListener<TourismCategory>,
        CategoryItemViewModel.CategoryItemViewModelListener<CategoryPlace>,
        PlaceItemViewModel.PlaceViewModelListener.PlaceItemClickListener<Places>,
        CategoryHeaderItemViewModel.OnHeaderClickListener<CategoryPlace> {
    private static final String TAG = CategoryFragment.class.getName();

    public static final String TYPE_ID = "typeId";
    public static final String TYPE_NAME = "typeName";

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
        String mCategoryId = getArguments().getString(MainActivity.SELECTED_CATEGORY);
        RecyclerView mCategoryRecycler = getBinding().categoryRecycler;
        mCategoryRecycler.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        getBinding().categoryName.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        if (mCategoryId != null) {
            getViewModel().loadData(mCategoryId);
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        getViewModel().onDestroyInstance();
    }

    @Override
    public void onDataLoad(TourismCategory item) {
        getBinding().categoryRecycler.setAdapter(new CategoryAdapter(getContext(), item.getCategoryPlaceList(), this, this));
        getBinding().categoryName.setAdapter(new HeaderCategoryAdapter(getContext(), item.getCategoryPlaceList(), this));
    }

    @Override
    public void onMoreClick(CategoryPlace item) {
        showPlacesList(item);
    }

    @Override
    public void onItemClick(Places item) {
        Toast.makeText(getContext(), item.toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onHeaderClick(@NonNull CategoryPlace item) {
        showPlacesList(item);
    }

    private void showPlacesList(CategoryPlace item) {
        getContext().startActivity(goNextActivity(PlaceListActivity.class)
                .putExtra(TYPE_NAME, item.getName())
                .putExtra(TYPE_ID, item.getId()));
    }
}
