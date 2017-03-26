package com.ctb.gmt.naibeck.guiamultiturismocentroamerica.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Kevin Gomez on 3/25/2017.
 */

public class GalleryImage implements Parcelable {
    @SerializedName("imageurl")
    private String mImgUrl;

    public String getImgUrl() {
        return mImgUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mImgUrl);
    }

    public GalleryImage() {
    }

    protected GalleryImage(Parcel in) {
        this.mImgUrl = in.readString();
    }

    public static final Parcelable.Creator<GalleryImage> CREATOR = new Parcelable.Creator<GalleryImage>() {
        @Override
        public GalleryImage createFromParcel(Parcel source) {
            return new GalleryImage(source);
        }

        @Override
        public GalleryImage[] newArray(int size) {
            return new GalleryImage[size];
        }
    };
}
