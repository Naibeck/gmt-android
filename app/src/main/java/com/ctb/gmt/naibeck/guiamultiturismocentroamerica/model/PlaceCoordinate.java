package com.ctb.gmt.naibeck.guiamultiturismocentroamerica.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class PlaceCoordinate implements Parcelable {
    @SerializedName("lugares")
    private List<PlacePin> mPlacePin;

    public List<PlacePin> getPlacePin() {
        return mPlacePin;
    }

    public void setPlacePin(List<PlacePin> mPlacePin) {
        this.mPlacePin = mPlacePin;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(this.mPlacePin);
    }

    public PlaceCoordinate() {
    }

    protected PlaceCoordinate(Parcel in) {
        this.mPlacePin = new ArrayList<PlacePin>();
        in.readList(this.mPlacePin, PlacePin.class.getClassLoader());
    }

    public static final Parcelable.Creator<PlaceCoordinate> CREATOR = new Parcelable.Creator<PlaceCoordinate>() {
        @Override
        public PlaceCoordinate createFromParcel(Parcel source) {
            return new PlaceCoordinate(source);
        }

        @Override
        public PlaceCoordinate[] newArray(int size) {
            return new PlaceCoordinate[size];
        }
    };

    @Override
    public String toString() {
        return "PlaceCoordinate{" +
                "mPlacePin=" + mPlacePin +
                '}';
    }
}
