package com.ctb.gmt.naibeck.guiamultiturismocentroamerica.api.service;

import android.support.annotation.NonNull;

import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.model.TourismCategory;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.Path;

public interface CategoryService {
    @GET("ubicacion/categoria/{id_categoria}")
    Call<TourismCategory> getCategory(@Path("id_categoria") @NonNull String idCategory);
}
