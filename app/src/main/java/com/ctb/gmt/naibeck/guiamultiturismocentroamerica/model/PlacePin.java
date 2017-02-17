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

    @SerializedName("Tipo")
    private List<PinType> mPinType;

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

    public double getLat() {
        return mLat;
    }

    public void setLat(double mLat) {
        this.mLat = mLat;
    }

    public double getLon() {
        return mLon;
    }

    public void setLon(double mLon) {
        this.mLon = mLon;
    }

    public List<PinType> getPinType() {
        return mPinType;
    }

    public void setPinType(List<PinType> mPinType) {
        this.mPinType = mPinType;
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
        dest.writeTypedList(this.mPinType);
    }

    public PlacePin() {
    }

    protected PlacePin(Parcel in) {
        this.mId = in.readInt();
        this.mName = in.readString();
        this.mLat = in.readDouble();
        this.mLon = in.readDouble();
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

    @Override
    public String toString() {
        return "PlacePin{" +
                "mId=" + mId +
                ", mName='" + mName + '\'' +
                ", mLat=" + mLat +
                ", mLon=" + mLon +
                ", mPinType=" + mPinType +
                '}';
    }
}
