package com.ctb.gmt.naibeck.guiamultiturismocentroamerica.ui.activity;


import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.R;
import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.databinding.ActivityPlaceDetailBinding;
import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.model.Places;
import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.ui.adapter.DetailGalleryAdapter;
import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.ui.fragment.CategoryFragment;
import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.viewmodel.PlaceDetailViewModel;

public class PlaceDetailActivity extends BaseActivity<ActivityPlaceDetailBinding, PlaceDetailViewModel> {
    private static final String TAG = PlaceDetailActivity.class.getName();

    private Places mPlace;
    private RecyclerView mGallery;

    @Override
    public int getLayout() {
        return R.layout.activity_place_detail;
    }

    @Override
    public PlaceDetailViewModel getViewModel() {
        return PlaceDetailViewModel.getInstance(this, getPlace());
    }

    @Override
    public void setViewModelToBinding() {
        getBinding().setViewModel(getViewModel());
    }

    @Override
    public Toolbar getToolbar() {
        return getBinding().placeDetailToolbar.toolbar;
    }

    @Override
    public boolean isHomeAsUpEnable() {
        return true;
    }

    @Override
    public void initComponents(ActivityPlaceDetailBinding binding) {
        super.initComponents(binding);
        setTitle(mPlace.getName());
        mGallery = getBinding().placeDetailContent.contentGalleryDetail.contentGalleryList;
        mGallery.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        mGallery.setAdapter(new DetailGalleryAdapter(mPlace.getGalleryImage()));
    }

    private Places getPlace() {
        if (mPlace != null) {
            return mPlace;
        }

        mPlace = getIntent().getParcelableExtra(CategoryFragment.PLACE);
        return mPlace;
    }

    @Override
    protected void onStop() {
        super.onStop();
        getViewModel().onDestroyInstance();
    }

    @Override
    public boolean onOptionsItemSelected(android.view.MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
