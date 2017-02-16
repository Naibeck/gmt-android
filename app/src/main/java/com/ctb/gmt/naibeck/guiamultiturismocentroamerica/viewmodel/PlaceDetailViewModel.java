package com.ctb.gmt.naibeck.guiamultiturismocentroamerica.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.model.Places;
import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.ui.activity.PlaceDetailActivity;

public class PlaceDetailViewModel extends BaseObservable {
    private static final String TAG = PlaceDetailViewModel.class.getName();

    private static PlaceDetailViewModel sInstance;

    private PlaceDetailActivity mActivity;
    private Places mPlace;

    private String mPlaceName;
    private String mPlaceDescription;
    private String mPlaceAddress;
    private String mPlaceLogo;

    public PlaceDetailViewModel(PlaceDetailActivity mActivity, Places mPlace) {
        this.mActivity = mActivity;
        this.mPlace = mPlace;

        this.mPlaceName = mPlace.getName();
        this.mPlaceDescription = mPlace.getDescription();
        this.mPlaceAddress = mPlace.getAddress();
        this.mPlaceLogo = mPlace.getLogo();
    }

    public static PlaceDetailViewModel getInstance(PlaceDetailActivity activity,
                                                   Places place) {
        if (sInstance != null) {
            return sInstance;
        }

        sInstance = new PlaceDetailViewModel(activity, place);
        return sInstance;
    }

    @Bindable
    public String getPlaceName() {
        return mPlaceName;
    }

    @Bindable
    public void setPlaceName(String mPlaceName) {
        this.mPlaceName = mPlaceName;
    }

    @Bindable
    public String getPlaceDescription() {
        return mPlaceDescription;
    }

    @Bindable
    public void setPlaceDescription(String mPlaceDescription) {
        this.mPlaceDescription = mPlaceDescription;
    }

    @Bindable
    public String getPlaceAddress() {
        return mPlaceAddress;
    }

    @Bindable
    public void setPlaceAddress(String mPlaceAddress) {
        this.mPlaceAddress = mPlaceAddress;
    }

    @Bindable
    public String getPlaceLogo() {
        return mPlaceLogo;
    }

    @Bindable
    public void setPlaceLogo(String mPlaceLogo) {
        this.mPlaceLogo = mPlaceLogo;
    }

    public void onDestroyInstance() {
        if (sInstance != null) {
            sInstance = null;
        }
    }
}
