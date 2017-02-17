package com.ctb.gmt.naibeck.guiamultiturismocentroamerica.api.domain;

import com.android.annotations.NonNull;
import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.api.GmtClient;
import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.model.CategoryPlace;
import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.model.Places;
import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.model.TourismCategory;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class CategoryDomain {
    private static final String TAG = CategoryDomain.class.getName();

    private static CategoryDomain sInstance;

    private GmtClient mGmtClient;

    public static CategoryDomain getInstance(@NonNull GmtClient client) {
        if (sInstance != null) {
            return sInstance;
        }

        sInstance = new CategoryDomain(client);
        return sInstance;
    }

    public CategoryDomain(GmtClient mGmtClient) {
        this.mGmtClient = mGmtClient;
    }

    public void getCategoryPlace(@NonNull String categoryId, @NonNull Callback<TourismCategory> callback) {
        Call<TourismCategory> call = mGmtClient.getCategoryService().getCategory(categoryId);
        call.enqueue(callback);
    }

    public void getPlaceListFromSelectedType(@NonNull String typeId, @NonNull Callback<CategoryPlace> callback) {
        Call<CategoryPlace> call = mGmtClient.getCategoryService().getPlaces(typeId);
        call.enqueue(callback);
    }

    public void removeInstance() {
        if (sInstance != null) {
            sInstance = null;
        }
    }
}
