package com.ctb.gmt.naibeck.guiamultiturismocentroamerica.api.service;

import android.support.annotation.NonNull;

import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.model.CategoryPlace;
import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.model.Places;
import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.model.TourismCategory;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.Path;

public interface CategoryService {
    int LIMIT_QUANTITY = 5;

    @GET("ubicacion/categoria/{id_categoria}/" + LIMIT_QUANTITY)
    Call<TourismCategory> getCategory(@Path("id_categoria") @NonNull String idCategory);

    @GET("ubicacion/tipo/{id_tipo}")
    Call<CategoryPlace> getPlaces(@Path("id_tipo") @NonNull String idTipo);
}
