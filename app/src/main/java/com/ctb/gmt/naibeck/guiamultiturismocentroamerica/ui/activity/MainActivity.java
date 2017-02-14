package com.ctb.gmt.naibeck.guiamultiturismocentroamerica.ui.activity;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.R;
import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.databinding.ActivityMainBinding;
import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.ui.fragment.HomeFragment;
import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.ui.fragment.MapFragment;
import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.utility.LocationDomain;
import com.google.android.gms.common.ConnectionResult;

public class MainActivity extends BaseActivity<ActivityMainBinding, Void>
        implements LocationDomain.LocationDomainListener {

    private static final String TAG = MainActivity.class.getName();

    public static final String SELECTED_CATEGORY = "selectedCategory";

    private NavigationView mNavMenu;
    private DrawerLayout mDrawerMenu;
    private MapFragment mMap;
    private HomeFragment mHome;

    private LocationDomain mLocationDomain;

    @Override
    public int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    public Void getViewModel() {
        return null;
    }

    @Override
    public void setViewModelToBinding() {

    }

    @Override
    public Toolbar getToolbar() {
        return getBinding().homeToolbar.toolbar;
    }

    @Override
    public void initComponents(final ActivityMainBinding binding) {
        mDrawerMenu = binding.homeDrawerLayout;
        mNavMenu = binding.homeNavigationView;

        setupNavMenu();
        bottomNavigationSetup();

        replaceFragment(R.id.mainContainer, getHomeFragment());
    }

    @Override
    protected void onStart() {
        getLocationDomain().handleOnStart();
        getHomeFragment();
        super.onStart();

    }

    @Override
    protected void onStop() {
        getLocationDomain().handleOnStop();
        super.onStop();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mLocationDomain.removeLocationUpdates();
    }

    private void setupNavMenu() {
        mNavMenu.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.dondeQuedamos:
                        Toast.makeText(getContext(), "Item", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.dondeCompramos:
                        Toast.makeText(getContext(), "Item", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.miPueblo:
                        Toast.makeText(getContext(), "Item", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.queComemos:
                        Toast.makeText(getContext(), "Item", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.queHacemos:
                        Toast.makeText(getContext(), "Item", Toast.LENGTH_SHORT).show();
                        break;
                }
                return false;
            }
        });
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, mDrawerMenu,
                getToolbar(), R.string.open_drawer, R.string.close_drawer) {
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                invalidateOptionsMenu();
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu();
            }
        };

        mDrawerMenu.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
    }

    private void bottomNavigationSetup() {
        BottomNavigationView mBottomView = getBinding().contentHome.bottomNavigation;
        mBottomView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                switch (itemId) {
                    case R.id.bottomHome:
                        replaceFragment(R.id.mainContainer, getHomeFragment());
                        break;
                    case R.id.bottomMap:
                        storeLocation(mLocationDomain.getLastKnownLocation());
                        replaceFragment(R.id.mainContainer, getMapFragment());
                        break;
                }
                return true;
            }
        });
    }

    private HomeFragment getHomeFragment() {
        if (mHome != null) {
            return mHome;
        }

        mHome = HomeFragment.getInstance();
        return mHome;
    }

    private MapFragment getMapFragment() {
        if (mMap != null) {
            return mMap;
        }

        mMap = MapFragment.getInstance();
        return mMap;
    }

    public void goCategoryActivity(@NonNull String categoryId) {
//        goNextActivity(CategoryActivity.class)
//                .putExtra(SELECTED_CATEGORY, categoryId);
    }

    private LocationDomain getLocationDomain() {
        if (mLocationDomain != null) {
            return mLocationDomain;
        }

        mLocationDomain = getLocationDomain(this);
        return mLocationDomain;
    }

    /**
     * Will store the last know location to preferences; this will allow to use that location without asking
     * for it again
     */
    private void storeLocation(@NonNull Location location) {
        if (isProviderEnabled()) {
            getGmtPreferences().putLastStoredLocation(location);
        } else {
            //Launch settings to enable provider
            startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
        }
    }

    /**
     * Will launch settings screen in case Location providers are turned off
     * @return true if provider is enabled false if is not
     */
    public boolean isProviderEnabled() {
        int locationMode;

        try {
            locationMode = Settings.Secure.getInt(getContentResolver(), Settings.Secure.LOCATION_MODE);
        } catch (Settings.SettingNotFoundException e) {
            Log.d(TAG, "Exception: ", e);
            return false;
        }

        return locationMode != Settings.Secure.LOCATION_MODE_OFF;
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        Log.d(TAG, "Connection succeed");
        if (isLocationPermissionGranted()) { //Check if permission were granted on Android 6 >
            storeLocation(mLocationDomain.getLastKnownLocation());
            mLocationDomain.startLocationUpdates();
        }
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.d(TAG, "Connection suspended");
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.d(TAG, "Connection failed: " + connectionResult.getErrorMessage());
    }

    @Override
    public void onLocationChanged(Location location) {
        storeLocation(location);
    }
}
