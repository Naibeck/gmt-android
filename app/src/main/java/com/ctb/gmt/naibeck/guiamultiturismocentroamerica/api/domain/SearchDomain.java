package com.ctb.gmt.naibeck.guiamultiturismocentroamerica.api.domain;

import com.android.annotations.NonNull;
import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.api.GmtClient;
import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.model.CategoryPlace;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by Kevin Gomez on 3/20/2017.
 */

public class SearchDomain {
    private static final String TAG = SearchDomain.class.getName();

    private static SearchDomain sInstance;

    private GmtClient mGmtClient;

    public static SearchDomain getInstance(@NonNull GmtClient client) {
        if (sInstance == null) {
            sInstance = new SearchDomain(client);
        }
        return sInstance;
    }

    public SearchDomain(GmtClient mGmtClient) {
        this.mGmtClient = mGmtClient;
    }

    public void getSearchResults(@NonNull String searchRequest, @NonNull Callback<CategoryPlace> callback) {
        Call<CategoryPlace> call = mGmtClient.getSearchservice().getSearchResults(searchRequest);
        call.enqueue(callback);
    }

    public void removeInstance() {
        if (sInstance != null) {
            sInstance = null;
        }
    }
}
