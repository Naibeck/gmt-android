package com.ctb.gmt.naibeck.guiamultiturismocentroamerica.ui.fragment;

import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.R;
import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.databinding.FragmentMapBinding;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

public class MapFragment extends BaseFragment<FragmentMapBinding, Void> {
    private static final String TAG = MapFragment.class.getName();

    private SupportMapFragment mSupportMapFragment;

    public static MapFragment getInstance() {
        return new MapFragment();
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_map;
    }

    @Override
    public Void getViewModel() {
        return null;
    }


    @Override
    public void setViewModelToBinding() {

    }

    @Override
    public void initComponents() {
        super.initComponents();
        mSupportMapFragment = new SupportMapFragment();
        replaceFragment(R.id.mapContainer, mSupportMapFragment);
        mSupportMapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                //TODO: Add my current location and add makers
            }
        });
    }
}
