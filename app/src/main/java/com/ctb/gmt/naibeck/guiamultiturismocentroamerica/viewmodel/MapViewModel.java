package com.ctb.gmt.naibeck.guiamultiturismocentroamerica.viewmodel;

import android.databinding.BaseObservable;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.widget.TextView;
import android.widget.Toast;

import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.ui.fragment.MapFragment;
import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.utility.GmtSharedPreferences;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;

public class MapViewModel extends BaseObservable {
    private static final String TAG = MapViewModel.class.getName();

    private static MapViewModel sInstance;

    private MapFragment mFragment;
    private TextView.OnEditorActionListener mActionListener;

    private String mSearchContent;

    public static MapViewModel getInstance(@NonNull MapFragment fragment,
                                           @NonNull TextView.OnEditorActionListener actionListener) {
        if (sInstance != null) {
            return sInstance;
        }

        sInstance = new MapViewModel(fragment, actionListener);
        return sInstance;
    }

    public MapViewModel(MapFragment mFragment, TextView.OnEditorActionListener mActionListener) {
        this.mFragment = mFragment;
        this.mActionListener = mActionListener;
    }

    public String getSearchContent() {
        return mSearchContent;
    }

    public void setSearchContent(String searchContent) {
        this.mSearchContent = searchContent;
    }

    public LatLng getLatLngFromUser() {
        if (getGmtPreferences().getLastStoredLocation() != null) {
            return new LatLng(getGmtPreferences().getLastStoredLocation().getLatitude(),
                    getGmtPreferences().getLastStoredLocation().getLongitude());
        }
        return null;
    }

    public BitmapDescriptor getIcon(@DrawableRes int drawableResource) {
        return BitmapDescriptorFactory.fromResource(drawableResource);
    }

    public void displayMessage() {
        Toast.makeText(mFragment.getContext(), getSearchContent(), Toast.LENGTH_SHORT).show();
    }

    private GmtSharedPreferences getGmtPreferences() {
        return GmtSharedPreferences.getInstance(mFragment.getContext());
    }
}
