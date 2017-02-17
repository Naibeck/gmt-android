package com.ctb.gmt.naibeck.guiamultiturismocentroamerica.viewmodel;

import android.databinding.BaseObservable;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

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
    private GmtSharedPreferences mSharedPreferences;
    private PlacePinDomain mPlacePinDomain;
    private PlacePinLoadListener mPlacePinLoadListener;

    private String mSearchContent;

    public static MapViewModel getInstance(@NonNull MapFragment fragment,
                                           @NonNull GmtSharedPreferences preferences,
                                           @NonNull PlacePinDomain placePinDomain,
                                           @NonNull PlacePinLoadListener placePinLoadListener) {
        if (sInstance != null) {
            return sInstance;
        }

        sInstance = new MapViewModel(fragment, preferences, placePinDomain, placePinLoadListener);
        return sInstance;
    }

    public MapViewModel(MapFragment mFragment,
                        GmtSharedPreferences mSharedPreferences,
                        PlacePinDomain mPlacePinDomain,
                        PlacePinLoadListener mPlacePinLoadListener) {
        this.mFragment = mFragment;
        this.mSharedPreferences = mSharedPreferences;
        this.mPlacePinDomain = mPlacePinDomain;
        this.mPlacePinLoadListener = mPlacePinLoadListener;
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

    public interface PlacePinLoadListener<T> {
        void onPinLoaded(List<T> items);
    }
}
