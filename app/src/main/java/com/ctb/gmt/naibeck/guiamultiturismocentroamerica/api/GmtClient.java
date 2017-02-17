package com.ctb.gmt.naibeck.guiamultiturismocentroamerica.api;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.StringDef;

import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.MultiturismoApplication;
import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.api.service.CategoryService;
import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.api.service.PlacePinService;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GmtClient {
    private static final String TAG = GmtClient.class.getName();
    private static final String BASE_URL = "http://www.codetobe.com/guiamultiturismoapp/";

    private Context mContext;
    private CategoryService mCategoryService;
    private PlacePinService mPlacePinService;

    public static GmtClient getInstance(@NonNull Context context) {
        return new GmtClient(context);
    }

    private GmtClient(Context mContext) {
        this.mContext = mContext;
        initRetrofit();
    }

    private void initRetrofit() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        httpClient.addInterceptor(httpLoggingInterceptor);

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(MultiturismoApplication.getsInstance().getGson()))
                .baseUrl(BASE_URL)
                .client(httpClient.build())
                .build();

        mCategoryService = retrofit.create(CategoryService.class);
        mPlacePinService = retrofit.create(PlacePinService.class);
    }

    public CategoryService getCategoryService() {
        return mCategoryService;
    }

    public PlacePinService getPlacePinService() {
        return mPlacePinService;
    }
}
