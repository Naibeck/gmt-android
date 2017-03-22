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
import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.ui.fragment.DirectoryFragment;
import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.ui.fragment.HomeFragment;
import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.ui.fragment.MapFragment;
import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.utility.LocationDomain;
import com.google.android.gms.common.ConnectionResult;

import java.lang.ref.WeakReference;

public class MainActivity extends BaseActivity<ActivityMainBinding, Void>
        implements LocationDomain.LocationDomainListener {

    private static final String TAG = MainActivity.class.getName();

    public static final String SELECTED_CATEGORY = "selectedCategory";
    public static final String WHAT_WE_EAT = "1";
    public static final String WHERE_WE_STAY = "3";
    public static final String WHAT_WE_DO = "2";

    private NavigationView mNavMenu;
    private DrawerLayout mDrawerMenu;
    private MapFragment mMap;
    private HomeFragment mHome;
    private DirectoryFragment mDirectoryFragment;

    private WeakReference<LocationDomain> mLocationWeakDomain;

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
        super.onStop();
        getLocationDomain().handleOnStop();
        getGmtPreferences().removeInstance();
        mDirectoryFragment = null;
    }

    @Override
    protected void onPause() {
        super.onPause();
        getLocationDomain().removeLocationUpdates();
        getLocationDomain().removeInstance();
    }

    private void setupNavMenu() {
        mNavMenu.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.dondeQuedamos:
                        goCategoryActivity(WHERE_WE_STAY);
                        break;
                    case R.id.dondeCompramos:
                        Toast.makeText(getContext(), "Item", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.miPueblo:
                        Toast.makeText(getContext(), "Item", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.queComemos:
                        goCategoryActivity(WHAT_WE_EAT);
                        break;
                    case R.id.queHacemos:
                        goCategoryActivity(WHAT_WE_DO);
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
                    case R.id.bottomDirectory:
                        replaceFragment(R.id.mainContainer, new DirectoryFragment());
                        break;
                    case R.id.bottomMap:
                        storeLocation(getLocationDomain().getLastKnownLocation());
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

    private DirectoryFragment getDirectoryFragment() {
        if (mDirectoryFragment == null) {
            mDirectoryFragment = DirectoryFragment.getInstance();
        }
        return mDirectoryFragment;
    }

    public void goCategoryActivity(@NonNull String categoryId) {
        startActivity(goNextActivity(CategoryActivity.class)
                .putExtra(SELECTED_CATEGORY, categoryId));
    }

    private LocationDomain getLocationDomain() {
        mLocationWeakDomain = new WeakReference<>(getLocationDomain(this));
        return mLocationWeakDomain.get();
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
            storeLocation(getLocationDomain().getLastKnownLocation());
            getLocationDomain().startLocationUpdates();
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
