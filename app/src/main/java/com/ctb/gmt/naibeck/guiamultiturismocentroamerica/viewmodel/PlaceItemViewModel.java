package com.ctb.gmt.naibeck.guiamultiturismocentroamerica.viewmodel;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.view.View;

import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.model.Places;

public class PlaceItemViewModel extends BaseObservable {
    private static final String TAG = PlaceItemViewModel.class.getName();

    private Context context;
    private Places mPlace;
    private PlaceViewModelListener.PlaceItemClickListener mPlaceItemClickListener;

    private String mPlaceName;

    public PlaceItemViewModel(Context context, Places mPlace, PlaceViewModelListener.PlaceItemClickListener mPlaceItemClickListener) {
        this.context = context;
        this.mPlace = mPlace;
        this.mPlaceItemClickListener = mPlaceItemClickListener;
        this.mPlaceName = mPlace.getName();
    }

    @Bindable
    public String getPlaceName() {
        return mPlaceName;
    }

    @Bindable
    public void setPlaceName(String mPlaceName) {
        this.mPlaceName = mPlaceName;
    }

    public void onPlaceClick(View view) {
        mPlaceItemClickListener.onItemClick(mPlace);
    }

    public interface PlaceViewModelListener {
        interface PlaceItemClickListener<T> {
            void onItemClick(T item);
        }
    }
}
