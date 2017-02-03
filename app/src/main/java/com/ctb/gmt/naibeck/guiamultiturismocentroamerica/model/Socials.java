package com.ctb.gmt.naibeck.guiamultiturismocentroamerica.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Socials implements Parcelable {
    @SerializedName("socialTipo_Id")
    private int mId;
    
    @SerializedName("sociallurl")
    private String mUrl;
    
    @SerializedName("socialTipoNombre")
    private String mName;

    public int getId() {
        return mId;
    }

    public void setId(int mId) {
        this.mId = mId;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String mUrl) {
        this.mUrl = mUrl;
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
        dest.writeString(this.mUrl);
        dest.writeString(this.mName);
    }

    public Socials() {
    }

    protected Socials(Parcel in) {
        this.mId = in.readInt();
        this.mUrl = in.readString();
        this.mName = in.readString();
    }

    public static final Parcelable.Creator<Socials> CREATOR = new Parcelable.Creator<Socials>() {
        @Override
        public Socials createFromParcel(Parcel source) {
            return new Socials(source);
        }

        @Override
        public Socials[] newArray(int size) {
            return new Socials[size];
        }
    };

    @Override
    public String toString() {
        return "Socials{" +
                "mId=" + mId +
                ", mUrl='" + mUrl + '\'' +
                ", mName='" + mName + '\'' +
                '}';
    }
}
