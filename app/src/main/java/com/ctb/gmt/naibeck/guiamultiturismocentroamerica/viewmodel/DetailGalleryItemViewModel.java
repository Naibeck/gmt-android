package com.ctb.gmt.naibeck.guiamultiturismocentroamerica.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.model.GalleryImage;

/**
 * Created by Kevin Gomez on 3/25/2017.
 */

public class DetailGalleryItemViewModel extends BaseObservable {
    private String mGalleryImageUrl;

    private GalleryImage mGalleryImage;

    public DetailGalleryItemViewModel(GalleryImage mGalleryImage) {
        this.mGalleryImage = mGalleryImage;
        this.mGalleryImageUrl = mGalleryImage.getImgUrl();
    }

    @Bindable
    public String getGalleryImageUrl() {
        return mGalleryImageUrl;
    }
}
