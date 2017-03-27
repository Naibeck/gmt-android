package com.ctb.gmt.naibeck.guiamultiturismocentroamerica.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.view.View;

import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.model.GalleryImage;

/**
 * Created by Kevin Gomez on 3/25/2017.
 */

public class DetailGalleryItemViewModel extends BaseObservable {
    private String mGalleryImageUrl;

    private GalleryImage mGalleryImage;
    private OnGalleryItemClickListener<String> mOnGalleryItemClickListener;

    public DetailGalleryItemViewModel(GalleryImage mGalleryImage, OnGalleryItemClickListener<String> onGalleryItemClickListener) {
        this.mGalleryImage = mGalleryImage;
        this.mOnGalleryItemClickListener = onGalleryItemClickListener;
        this.mGalleryImageUrl = mGalleryImage.getImgUrl();
    }

    @Bindable
    public String getGalleryImageUrl() {
        return mGalleryImageUrl;
    }

    public void onGalleryClick(View view) {
        mOnGalleryItemClickListener.onItemClick(mGalleryImageUrl);
    }

    public interface OnGalleryItemClickListener<T> {
        void onItemClick(T item);
    }
}
