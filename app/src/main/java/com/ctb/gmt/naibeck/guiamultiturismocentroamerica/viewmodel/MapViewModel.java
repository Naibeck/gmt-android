package com.ctb.gmt.naibeck.guiamultiturismocentroamerica.viewmodel;

import android.databinding.BaseObservable;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.util.Log;

import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.api.domain.PlacePinDomain;
import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.model.PlaceCoordinate;
import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.ui.fragment.MapFragment;
import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.utility.GmtSharedPreferences;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MapViewModel extends BaseObservable {
    private static final String TAG = MapViewModel.class.getName();

    private static MapViewModel sInstance;

    private MapFragment mFragment;
    private PlacePinDomain mPlacePinDomain;
    private PlacePinLoadListener mPlacePinLoadListener;

    public static MapViewModel getInstance(@NonNull MapFragment fragment,
                                           @NonNull PlacePinDomain placePinDomain,
                                           @NonNull PlacePinLoadListener placePinLoadListener) {
        if (sInstance != null) {
            return sInstance;
        }

        sInstance = new MapViewModel(fragment, placePinDomain, placePinLoadListener);
        return sInstance;
    }

    public MapViewModel(MapFragment mFragment,
                        PlacePinDomain mPlacePinDomain,
                        PlacePinLoadListener mPlacePinLoadListener) {
        this.mFragment = mFragment;
        this.mPlacePinDomain = mPlacePinDomain;
        this.mPlacePinLoadListener = mPlacePinLoadListener;
    }

    public BitmapDescriptor getIcon(@DrawableRes int drawableResource) {
        return BitmapDescriptorFactory.fromResource(drawableResource);
    }

    public void pinsLoaded() {
        mPlacePinDomain.getPins(new Callback<PlaceCoordinate>() {
            @Override
            public void onResponse(Call<PlaceCoordinate> call, Response<PlaceCoordinate> response) {
                if (response.isSuccessful()) {
                    mPlacePinLoadListener.onPinLoaded(response.body().getPlacePin());
                }
            }

            @Override
            public void onFailure(Call<PlaceCoordinate> call, Throwable t) {
                Log.d(TAG, "Error", t);
            }
        });
    }

    public void removeIsntance() {
        if (sInstance != null) {
            sInstance = null;
        }
    }

    public interface PlacePinLoadListener<T> {
        void onPinLoaded(List<T> items);
    }
}
