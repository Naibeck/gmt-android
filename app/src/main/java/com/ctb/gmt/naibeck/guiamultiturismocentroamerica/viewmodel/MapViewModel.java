package com.ctb.gmt.naibeck.guiamultiturismocentroamerica.viewmodel;

import android.databinding.BaseObservable;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
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
    private GmtSharedPreferences mSharedPreferences;

    private String mSearchContent;

    public static MapViewModel getInstance(@NonNull MapFragment fragment,
                                           @NonNull GmtSharedPreferences preferences) {
        if (sInstance != null) {
            return sInstance;
        }

        sInstance = new MapViewModel(fragment, preferences);
        return sInstance;
    }

    public MapViewModel(MapFragment mFragment, GmtSharedPreferences mSharedPreferences) {
        this.mFragment = mFragment;
        this.mSharedPreferences = mSharedPreferences;
    }

    public String getSearchContent() {
        return mSearchContent;
    }

    public void setSearchContent(String searchContent) {
        this.mSearchContent = searchContent;
    }

    public LatLng getLatLngFromUser() {
        if (mSharedPreferences.getLastStoredLocation() != null) {
            return new LatLng(mSharedPreferences.getLastStoredLocation().getLatitude(),
                    mSharedPreferences.getLastStoredLocation().getLongitude());
        }
        return null;
    }

    public BitmapDescriptor getIcon(@DrawableRes int drawableResource) {
        return BitmapDescriptorFactory.fromResource(drawableResource);
    }

    public void displayMessage() {
        Toast.makeText(mFragment.getContext(), getSearchContent(), Toast.LENGTH_SHORT).show();
    }
}
