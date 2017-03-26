package com.ctb.gmt.naibeck.guiamultiturismocentroamerica.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.view.View;

import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.model.Socials;

/**
 * Created by Kevin Gomez on 3/26/2017.
 */

public class DetailSocialItemViewModel extends BaseObservable {
    private int mSocialId;
    private String mSocialUrl;

    private Socials mSocial;
    private OnSocialClickListener<Socials> mOnSocialClickListener;

    public DetailSocialItemViewModel(Socials mSocial, OnSocialClickListener<Socials> mOnSocialClickListener) {
        this.mSocial = mSocial;
        this.mOnSocialClickListener = mOnSocialClickListener;

        this.mSocialId = mSocial.getId();
        this.mSocialUrl = mSocial.getUrl();
    }

    @Bindable
    public int getSocialId() {
        return mSocialId;
    }

    @Bindable
    public String getSocialUrl() {
        return mSocialUrl;
    }

    public void onSocialClick(View view) {
        mOnSocialClickListener.onClick(mSocial);
    }

    public interface OnSocialClickListener<T>{
        void onClick(T item);
    }
}
