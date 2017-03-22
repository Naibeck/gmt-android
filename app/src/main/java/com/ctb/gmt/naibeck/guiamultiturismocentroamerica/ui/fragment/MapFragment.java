package com.ctb.gmt.naibeck.guiamultiturismocentroamerica.ui.fragment;

import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.R;
import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.databinding.FragmentMapBinding;
import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.model.PlacePin;
import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.viewmodel.MapViewModel;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

public class MapFragment extends BaseFragment<FragmentMapBinding, MapViewModel>
        implements TextView.OnEditorActionListener,
        MapViewModel.PlacePinLoadListener<PlacePin> {
    private static final String TAG = MapFragment.class.getName();

    private static final float ZOOM_LEVEL = 15.0f;

    public static MapFragment getInstance() {
        return new MapFragment();
    }

    private GoogleMap mGoogleMap;

    @Override
    public int getLayout() {
        return R.layout.fragment_map;
    }

    @Override
    public MapViewModel getViewModel() {
        return MapViewModel.getInstance(this, getGmtPreferences(), getPlacePinDomain(), this);
    }


    @Override
    public void setViewModelToBinding() {
        getBinding().setViewModel(getViewModel());
    }

    @Override
    public void initComponents() {
        super.initComponents();
        getBinding().searchTextBar.setOnEditorActionListener(this);
        setupMapFragment();
    }


    private void setupMapFragment() {
        SupportMapFragment mSupportMapFragment = new SupportMapFragment();
        replaceFragment(R.id.mapContainer, mSupportMapFragment);
        mSupportMapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                mGoogleMap = googleMap;
                getViewModel().pinsLoaded();
                if (getGmtPreferences().getLastStoredLocation() != null) {
                    if (isLocationPermissionGranted()) {
                        googleMap.setMyLocationEnabled(true);
                    }
                    googleMap.getUiSettings().setMyLocationButtonEnabled(false);
                    googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(getViewModel().getLatLngFromUser(),
                            ZOOM_LEVEL));
                }
            }
        });
    }

    private MarkerOptions markersSetup(@NonNull LatLng markerPosition, @DrawableRes int resource) {
        return new MarkerOptions()
//                .icon(getViewModel().getIcon(resource)) 
                .position(markerPosition);
    }

    @Override
    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        if (i == EditorInfo.IME_ACTION_SEARCH) {
            //TODO: Change this method to submit a search
            getViewModel().goSearchActivity();
            return true;
        }
        return false;
    }

    @Override
    public void onPinLoaded(List<PlacePin> items) {
        for (PlacePin placePin :  items) {
            LatLng latLng = new LatLng(placePin.getLat(), placePin.getLon());
            if (mGoogleMap != null) {
                mGoogleMap.addMarker(markersSetup(latLng, R.drawable.map_ic)
                        .title(placePin.getName()));
            }
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        getViewModel().removeIsntance();
        getPlacePinDomain().removeInstance();
    }
}
