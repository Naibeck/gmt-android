package com.ctb.gmt.naibeck.guiamultiturismocentroamerica.api.domain;

import com.android.annotations.NonNull;
import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.api.GmtClient;
import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.model.CategoryPlace;
import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.model.TourismCategory;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class CategoryDomain {

    private GmtClient mClient;

    public CategoryDomain(GmtClient mClient) {
        this.mClient = mClient;
    }

    public void getCategoriPlace(@NonNull String categoryId, @NonNull Callback<TourismCategory> callback) {
        Call<TourismCategory> call = mClient.getCategoryService().getCategory(categoryId);
        call.enqueue(callback);
    }

    public List<String> getCategoryNames(@NonNull TourismCategory tourismCategory) {
        List<String> categoryPlaceList = new ArrayList<>();
        for (CategoryPlace categoryPlace : tourismCategory.getCategoryPlaceList()) {
            categoryPlaceList.add(categoryPlace.getName());
        }
        return categoryPlaceList;
    }
}
