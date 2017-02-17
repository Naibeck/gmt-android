package com.ctb.gmt.naibeck.guiamultiturismocentroamerica.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class PinType implements Parcelable {
    @SerializedName("tipoId")
    private int mId;

    @SerializedName("tipoNombre")
    private String mName;

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


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.mId);
        dest.writeString(this.mName);
    }

    public PinType() {
    }

    protected PinType(Parcel in) {
        this.mId = in.readInt();
        this.mName = in.readString();
    }

    public static final Parcelable.Creator<PinType> CREATOR = new Parcelable.Creator<PinType>() {
        @Override
        public PinType createFromParcel(Parcel source) {
            return new PinType(source);
        }

        @Override
        public PinType[] newArray(int size) {
            return new PinType[size];
        }
    };

    @Override
    public String toString() {
        return "PinType{" +
                "mId=" + mId +
                ", mName='" + mName + '\'' +
                '}';
    }
}
