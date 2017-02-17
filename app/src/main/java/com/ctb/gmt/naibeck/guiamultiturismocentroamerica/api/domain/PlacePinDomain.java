package com.ctb.gmt.naibeck.guiamultiturismocentroamerica.api.domain;

import android.support.annotation.NonNull;

import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.api.GmtClient;
import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.model.PlaceCoordinate;

import retrofit2.Call;
import retrofit2.Callback;

public class PlacePinDomain {
    private static final String TAG = PlacePinDomain.class.getName();

    private static PlacePinDomain sInstance;

    private GmtClient mGmtClient;

    public static PlacePinDomain getInstance(@NonNull GmtClient gmtClient) {
        if (sInstance != null) {
            return sInstance;
        }

        sInstance = new PlacePinDomain(gmtClient);
        return sInstance;
    }

    public PlacePinDomain(GmtClient mGmtClient) {
        this.mGmtClient = mGmtClient;
    }

    public void getPins(@NonNull Callback<PlaceCoordinate> callback) {
        Call<PlaceCoordinate> call = mGmtClient.getPlacePinService().getCoordinateList();
        call.enqueue(callback);
    }
}
