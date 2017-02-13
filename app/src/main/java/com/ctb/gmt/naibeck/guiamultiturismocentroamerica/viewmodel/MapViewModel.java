package com.ctb.gmt.naibeck.guiamultiturismocentroamerica.viewmodel;

import android.databinding.BaseObservable;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;

import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.ui.fragment.MapFragment;
import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.utility.GmtSharedPreferences;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;

public class MapViewModel extends BaseObservable {
    private static final String TAG = MapViewModel.class.getName();

    private static MapViewModel sInstance;

    private MapFragment mFragment;

    public static MapViewModel getInstance(@NonNull MapFragment fragment) {
        if (sInstance != null) {
            return sInstance;
        }

        sInstance = new MapViewModel(fragment);
        return sInstance;
    }

    public MapViewModel(MapFragment mFragment) {
        this.mFragment = mFragment;
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

    private GmtSharedPreferences getGmtPreferences() {
        return GmtSharedPreferences.getInstance(mFragment.getContext());
    }
}
