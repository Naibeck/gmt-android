package com.ctb.gmt.naibeck.guiamultiturismocentroamerica.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Places implements Parcelable {
    @SerializedName("placeId")
    private int mId;

    @SerializedName("placeName")
    private String mName;

    @SerializedName("placeDescription")
    private String mDescription;

    @SerializedName("placeAdress")
    private String mAddress;

    @SerializedName("placeLogo")
    private String mLogo;

    @SerializedName("placeLatitud")
    private String mLatitude;

    @SerializedName("placeLongitude")
    private String mLongitude;

    @SerializedName("socials")
    private List<Socials> mSocialList;

    @SerializedName("images")
    private List<GalleryImage> mGalleryImage;

    public int getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    public String getDescription() {
        return mDescription;
    }

    public String getAddress() {
        return mAddress;
    }

    public String getLogo() {
        return mLogo;
    }

    public String getLatitude() {
        return mLatitude;
    }

    public String getLongitude() {
        return mLongitude;
    }

    public List<Socials> getSocialList() {
        return mSocialList;
    }

    public List<GalleryImage> getGalleryImage() {
        return mGalleryImage;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.mId);
        dest.writeString(this.mName);
        dest.writeString(this.mDescription);
        dest.writeString(this.mAddress);
        dest.writeString(this.mLogo);
        dest.writeString(this.mLatitude);
        dest.writeString(this.mLongitude);
        dest.writeTypedList(this.mSocialList);
        dest.writeTypedList(this.mGalleryImage);
    }

    public Places() {
    }

    protected Places(Parcel in) {
        this.mId = in.readInt();
        this.mName = in.readString();
        this.mDescription = in.readString();
        this.mAddress = in.readString();
        this.mLogo = in.readString();
        this.mLatitude = in.readString();
        this.mLongitude = in.readString();
        this.mSocialList = in.createTypedArrayList(Socials.CREATOR);
        this.mGalleryImage = in.createTypedArrayList(GalleryImage.CREATOR);
    }

    public static final Creator<Places> CREATOR = new Creator<Places>() {
        @Override
        public Places createFromParcel(Parcel source) {
            return new Places(source);
        }

        @Override
        public Places[] newArray(int size) {
            return new Places[size];
        }
    };

    @Override
    public String toString() {
        return "Places{" +
                "mId=" + mId +
                ", mName='" + mName + '\'' +
                ", mDescription='" + mDescription + '\'' +
                ", mAddress='" + mAddress + '\'' +
                ", mLogo='" + mLogo + '\'' +
                ", mLatitude='" + mLatitude + '\'' +
                ", mLongitude='" + mLongitude + '\'' +
                ", mSocialList=" + mSocialList +
                ", mGalleryImage=" + mGalleryImage +
                '}';
    }
}
