package com.ctb.gmt.naibeck.guiamultiturismocentroamerica.ui.activity;

import android.support.v7.widget.Toolbar;

import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.R;
import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.databinding.ActivityPlaceListBinding;
import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.ui.fragment.CategoryFragment;
import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.viewmodel.PlaceListViewModel;

public class PlaceListActivity extends BaseActivity<ActivityPlaceListBinding, PlaceListViewModel> {
    private static final String TAG = PlaceListActivity.class.getName();

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
        String title = getIntent().getStringExtra(CategoryFragment.TYPE_NAME);
        String categoryId = getIntent().getStringExtra(CategoryFragment.TYPE_ID);

        setTitle(title);
    }
}
