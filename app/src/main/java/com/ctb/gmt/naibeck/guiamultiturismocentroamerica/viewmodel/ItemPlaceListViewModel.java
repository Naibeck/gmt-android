package com.ctb.gmt.naibeck.guiamultiturismocentroamerica.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.view.View;

import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.model.Places;

public class ItemPlaceListViewModel extends BaseObservable {
    private static final String TAG = ItemPlaceListViewModel.class.getName();

    private Places mPlace;
    private PlaceListItemClickListener mPlaceListItemClickListener;

    public ItemPlaceListViewModel(Places mPlace,
                                  PlaceListItemClickListener mPlaceListItemClickListener) {
        this.mPlace = mPlace;
        this.mPlaceListItemClickListener = mPlaceListItemClickListener;

        this.mLogoUrl = mPlace.getLogo();
        this.mPlaceName = mPlace.getName();
        this.mPlaceAddress = mPlace.getAddress();
    }

    private String mLogoUrl;
    private String mPlaceName;
    private String mPlaceAddress;

    @Bindable
    public String getLogoUrl() {
        return mLogoUrl;
    }

    @Bindable
    public void setLogoUrl(String mLogoUrl) {
        this.mLogoUrl = mLogoUrl;
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
    public String getPlaceAddress() {
        return mPlaceAddress;
    }

    @Bindable
    public void setPlaceAddress(String mPlaceAddress) {
        this.mPlaceAddress = mPlaceAddress;
    }

    public void onPlaceClick(View view) {
        mPlaceListItemClickListener.onItemClick(mPlace);
    }

    public interface PlaceListItemClickListener<T> {
        void onItemClick(T item);
    }
}
