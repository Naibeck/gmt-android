package com.ctb.gmt.naibeck.guiamultiturismocentroamerica.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class PlacePin implements Parcelable {
    @SerializedName("_id")
    private int mId;

    @SerializedName("placeName")
    private String mName;

    @SerializedName("placeLat")
    private double mLat;

    @SerializedName("placeLon")
    private double mLon;
    
    @SerializedName("MapIcon")
    private int mMapIcon;

    @SerializedName("Tipo")
    private List<PinType> mPinType;

    public int getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    public double getLat() {
        return mLat;
    }

    public double getLon() {
        return mLon;
    }

    public int getMapIcon() {
        return mMapIcon;
    }

    public List<PinType> getPinType() {
        return mPinType;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.mId);
        dest.writeString(this.mName);
        dest.writeDouble(this.mLat);
        dest.writeDouble(this.mLon);
        dest.writeInt(this.mMapIcon);
        dest.writeTypedList(this.mPinType);
    }

    public PlacePin() {
    }

    protected PlacePin(Parcel in) {
        this.mId = in.readInt();
        this.mName = in.readString();
        this.mLat = in.readDouble();
        this.mLon = in.readDouble();
        this.mMapIcon = in.readInt();
        this.mPinType = in.createTypedArrayList(PinType.CREATOR);
    }

    public static final Creator<PlacePin> CREATOR = new Creator<PlacePin>() {
        @Override
        public PlacePin createFromParcel(Parcel source) {
            return new PlacePin(source);
        }

        @Override
        public PlacePin[] newArray(int size) {
            return new PlacePin[size];
        }
    };
}
