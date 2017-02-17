package com.ctb.gmt.naibeck.guiamultiturismocentroamerica.api.service;

import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.model.PlaceCoordinate;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PlacePinService {

    @GET("ubicacion/geo/lat/lon")
    Call<PlaceCoordinate> getCoordinateList();
}
