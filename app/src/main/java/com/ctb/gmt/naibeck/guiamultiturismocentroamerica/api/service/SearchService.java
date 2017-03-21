package com.ctb.gmt.naibeck.guiamultiturismocentroamerica.api.service;

import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.model.CategoryPlace;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Kevin Gomez on 3/20/2017.
 */

public interface SearchService {
    @GET("ubicacion/search/{searchRequest}/")
    Call<CategoryPlace> getSearchResults(@Path("searchRequest") String searchRequest);
}
