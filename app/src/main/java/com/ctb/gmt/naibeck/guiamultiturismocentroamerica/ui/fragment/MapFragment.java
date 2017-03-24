package com.ctb.gmt.naibeck.guiamultiturismocentroamerica.ui.fragment;

import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;

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
        implements MapViewModel.PlacePinLoadListener<PlacePin> {
    private static final String TAG = MapFragment.class.getName();
    private static final float ZOOM_LEVEL = 15.0f;
    private static final int WHAT_WE_EAT = 1;
    private static final int WHERE_WE_STAY = 2;
    private static final int WHAT_WE_DO = 3;
    private static final int WHERE_DO_WE_SHOP = 4;
    private static final int NITE_LIFE = 5;

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
                .icon(getViewModel().getIcon(resource))
                .position(markerPosition);
    }

    @Override
    public void onPinLoaded(List<PlacePin> items) {
        for (PlacePin placePin : items) {
            LatLng latLng = new LatLng(placePin.getLat(), placePin.getLon());
            if (mGoogleMap != null) {
                int resources = 0;
                switch (placePin.getMapIcon()) {
                    case WHAT_WE_EAT:
                        resources = R.drawable.phrestaurante;
                        break;
                    case WHERE_WE_STAY:
                        resources = R.drawable.phhotel;
                        break;
                    case WHAT_WE_DO:
                        resources = R.drawable.phactividades;
                        break;
                    case WHERE_DO_WE_SHOP:
                        resources = R.drawable.phcompras;
                        break;
                    case NITE_LIFE:
                        resources = R.drawable.phnocturna;
                        break;
                }
                mGoogleMap.addMarker(markersSetup(latLng, resources)
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
