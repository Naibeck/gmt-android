package com.ctb.gmt.naibeck.guiamultiturismocentroamerica.ui.activity;

import android.support.v7.widget.Toolbar;

import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.R;
import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.databinding.ActivityPlaceListBinding;
import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.model.CategoryPlace;
import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.ui.fragment.CategoryFragment;
import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.viewmodel.PlaceListViewModel;

public class PlaceListActivity extends BaseActivity<ActivityPlaceListBinding, PlaceListViewModel> {
    private static final String TAG = PlaceListActivity.class.getName();

    private CategoryPlace mCategoryPlace;

    @Override
    public int getLayout() {
        return R.layout.activity_place_list;
    }

    @Override
    public PlaceListViewModel getViewModel() {
        return new PlaceListViewModel();
    }

    @Override
    public void setViewModelToBinding() {
        getBinding().setViewModel(getViewModel());
    }

    @Override
    public Toolbar getToolbar() {
        return getBinding().placeListToolbar.toolbar;
    }

    @Override
    public boolean isHomeAsUpEnable() {
        return true;
    }

    @Override
    public void initComponents(ActivityPlaceListBinding binding) {
        super.initComponents(binding);

        setTitle(getCategoryPlace().getName());
    }

    private CategoryPlace getCategoryPlace() {
        if (mCategoryPlace != null) {
            return mCategoryPlace;
        }

        mCategoryPlace = getIntent().getParcelableExtra(CategoryFragment.CATEGORY_PLACE);
        return mCategoryPlace;
    }
}