package com.ctb.gmt.naibeck.guiamultiturismocentroamerica.ui.fragment;

import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;

import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.R;
import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.databinding.FragmentMapBinding;
import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.viewmodel.MapViewModel;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapFragment extends BaseFragment<FragmentMapBinding, MapViewModel> {
    private static final String TAG = MapFragment.class.getName();

    private static final float ZOOM_LEVEL = 15.0f;

    public static MapFragment getInstance() {
        return new MapFragment();
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_map;
    }

    @Override
    public MapViewModel getViewModel() {
        return MapViewModel.getInstance(this);
    }


    @Override
    public void setViewModelToBinding() {
        getBinding().setViewModel(getViewModel());
    }

    @Override
    public void initComponents() {
        super.initComponents();
        setupMapFragment();
    }


    private void setupMapFragment() {
        SupportMapFragment mSupportMapFragment = new SupportMapFragment();
        replaceFragment(R.id.mapContainer, mSupportMapFragment);
        mSupportMapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                if (getGmtPreferences().getLastStoredLocation() != null) {
                    if (isLocationPermissionGranted()) {
                        googleMap.setMyLocationEnabled(true);
                    }
                    googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(getViewModel().getLatLngFromUser(),
                            ZOOM_LEVEL));
                }
            }
        });
    }

    private MarkerOptions markersSetup(@NonNull LatLng markerPosition, @DrawableRes int resource) {
        return new MarkerOptions().icon(getViewModel().getIcon(resource))
                .position(markerPosition);
    }
}
