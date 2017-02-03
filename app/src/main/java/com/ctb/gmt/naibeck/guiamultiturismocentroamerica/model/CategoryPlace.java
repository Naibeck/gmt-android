package com.ctb.gmt.naibeck.guiamultiturismocentroamerica.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class CategoryPlace implements Parcelable {
    @SerializedName("_id")
    private int mId;

    @SerializedName("tipoNombre")
    private String mName;

    @SerializedName("lugares")
    private List<Places> mPlaceList;

    public int getId() {
        return mId;
    }

    public void setId(int mId) {
        this.mId = mId;
    }

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public List<Places> getPlaceList() {
        return mPlaceList;
    }

    public void setPlaceList(List<Places> mPlaceList) {
        this.mPlaceList = mPlaceList;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.mId);
        dest.writeString(this.mName);
        dest.writeList(this.mPlaceList);
    }

    public CategoryPlace() {
    }

    protected CategoryPlace(Parcel in) {
        this.mId = in.readInt();
        this.mName = in.readString();
        this.mPlaceList = new ArrayList<Places>();
        in.readList(this.mPlaceList, Places.class.getClassLoader());
    }

    public static final Creator<CategoryPlace> CREATOR = new Creator<CategoryPlace>() {
        @Override
        public CategoryPlace createFromParcel(Parcel source) {
            return new CategoryPlace(source);
        }

        @Override
        public CategoryPlace[] newArray(int size) {
            return new CategoryPlace[size];
        }
    };

    @Override
    public String toString() {
        return "CategoryPlace{" +
                "mId=" + mId +
                ", mName='" + mName + '\'' +
                ", mPlaceList=" + mPlaceList +
                '}';
    }
}
