package com.ctb.gmt.naibeck.guiamultiturismocentroamerica.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class TourismCategory implements Parcelable {
    private List<CategoryPlace> mCategoryPlaceList;

    public List<CategoryPlace> getCategoryPlaceList() {
        return mCategoryPlaceList;
    }

    public void setCategoryPlaceList(List<CategoryPlace> mCategoryPlaceList) {
        this.mCategoryPlaceList = mCategoryPlaceList;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(this.mCategoryPlaceList);
    }

    public TourismCategory() {
    }

    protected TourismCategory(Parcel in) {
        this.mCategoryPlaceList = new ArrayList<CategoryPlace>();
        in.readList(this.mCategoryPlaceList, CategoryPlace.class.getClassLoader());
    }

    public static final Parcelable.Creator<TourismCategory> CREATOR = new Parcelable.Creator<TourismCategory>() {
        @Override
        public TourismCategory createFromParcel(Parcel source) {
            return new TourismCategory(source);
        }

        @Override
        public TourismCategory[] newArray(int size) {
            return new TourismCategory[size];
        }
    };

    @Override
    public String toString() {
        return "TourismCategory{" +
                "mCategoryPlaceList=" + mCategoryPlaceList +
                '}';
    }
}
