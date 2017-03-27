package com.ctb.gmt.naibeck.guiamultiturismocentroamerica.ui.activity;


import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;

import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.R;
import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.databinding.ActivityPlaceDetailBinding;
import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.model.Places;
import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.model.Socials;
import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.ui.adapter.DetailGalleryAdapter;
import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.ui.adapter.DetailSocialAdapter;
import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.ui.fragment.CategoryFragment;
import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.viewmodel.DetailGalleryItemViewModel;
import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.viewmodel.DetailSocialItemViewModel;
import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.viewmodel.PlaceDetailViewModel;

public class PlaceDetailActivity extends BaseActivity<ActivityPlaceDetailBinding, PlaceDetailViewModel>
        implements DetailSocialItemViewModel.OnSocialClickListener<Socials>,
        DetailGalleryItemViewModel.OnGalleryItemClickListener<String> {
    private static final String TAG = PlaceDetailActivity.class.getName();
    public static final String GALLERY_IMAGE = "galleryItem";

    private Places mPlace;
    private RecyclerView mGallery;
    private RecyclerView mSocial;

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
        mSocial = getBinding().placeDetailContent.contentSocialDetail.detailSocialList;

        mGallery.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        mGallery.setAdapter(new DetailGalleryAdapter(mPlace.getGalleryImage(), this));

        mSocial.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        mSocial.setAdapter(new DetailSocialAdapter(mPlace.getSocialList(), this));
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
        switch (item.getItemId()) {
            case R.id.call:
                openDialer(mPlace.getPhone());
                break;
            case R.id.map:
                openGoogleMaps(mPlace.getLatitude(), mPlace.getLongitude());
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(Socials item) {
        openUrl(item.getUrl());
    }

    @Override
    public void onItemClick(String item) {
        Log.d("GalleryFull", "onItemClick: " + item);
        Intent intent = new Intent(this, GalleryItemActivity.class);
        intent.putExtra(GALLERY_IMAGE, item);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.detail, menu);
        return true;
    }
}
