package com.ctb.gmt.naibeck.guiamultiturismocentroamerica.ui.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;

import com.android.annotations.NonNull;
import com.android.annotations.Nullable;
import com.crashlytics.android.Crashlytics;
import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.api.GmtClient;
import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.api.domain.CategoryDomain;
import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.utility.LocationDomain;
import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.utility.AnimationUtil;
import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.utility.GmtSharedPreferences;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.util.List;

import io.fabric.sdk.android.Fabric;

/**
 * Handles the base logic for the rest of activities.
 *
 * @param <VB> reference to this databinding class instance
 * @param <VM> reference to this viewmodel class instance
 */
public abstract class BaseActivity<VB extends ViewDataBinding, VM> extends AppCompatActivity {
    private static final String TAG = BaseActivity.class.getName();

    private VB mBinding;
    private Toolbar mToolbar;
    private LocationDomain mLocationDomain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, getLayout());
        setViewModelToBinding();

        Fabric.with(this, new Crashlytics());

        initToolbar(getToolbar());
        initComponents(mBinding);
        if (!isLocationPermissionGranted()) {
            checkLocationPermission();
        }
    }

    public void initComponents(VB binding) {

    }

    //Toolbar
    public  Toolbar  getToolbar() {
        return mToolbar;
    }

    public void initToolbar(Toolbar toolbar) {
        mToolbar = toolbar;
        if (mToolbar != null) {
            setSupportActionBar(toolbar);
            if (getSupportActionBar() != null) {
                getSupportActionBar().setDisplayHomeAsUpEnabled(isHomeAsUpEnable());
            }
        }
    }

    public boolean isHomeAsUpEnable() {
        return false;
    }

    @LayoutRes
    public abstract int getLayout();

    //Binding methods

    /**
     * Get ViewDataBinding for current activity, required in order to use binding features.
     *
     * @return current data binding instance
     */
    public VB getBinding() {
        return mBinding;
    }

    /**
     * Get viewmodel for current activity, required to access lower methods inside viewmodel.
     *
     * @return current viewmodel instance
     */
    public abstract VM getViewModel();

    public abstract void setViewModelToBinding();

    public Context getContext() {
        return getApplicationContext();
    }

    public Intent goNextActivity(@NonNull Class<?> activity) {
        return new Intent(this, activity);
    }

    public void openUrl(@NonNull String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(intent);
    }

    public void openGoogleMaps(String latitude, String longitude) {
        Intent map = new Intent(android.content.Intent.ACTION_VIEW,
                Uri.parse("google.navigation:q=" + latitude + "," + longitude));
        startActivity(map);
    }

    public void openDialer(String phone) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + phone));
        startActivity(intent);
    }

    private GmtClient getClient() {
        return GmtClient.getInstance(this);
    }

    public CategoryDomain getCategoryDomain() {
        return new CategoryDomain(getClient());
    }

    public void startFadeInAnimation(@NonNull View resourceId, @NonNull int interval, @Nullable Animation.AnimationListener listener) {
        Animation fadeIn = AnimationUtil.getFadeInAnimation(interval);
        fadeIn.setAnimationListener(listener);
        resourceId.setAnimation(fadeIn);
    }

    /**
     * Will handle the replacement of a fragment in a view
     * @param fragment a fragment to be replaced
     */
    public void replaceFragment(@NonNull @IdRes int view, @NonNull Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(view, fragment)
                .commit();
    }

    private void checkLocationPermission() {
        Dexter.checkPermissions(new MultiplePermissionsListener() {
            @Override
            public void onPermissionsChecked(MultiplePermissionsReport report) {
                if (report.areAllPermissionsGranted()) {
                    Log.d(TAG, "All permissions granted");
                }
            }

            @Override
            public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {

            }
        }, Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION);
    }

    public boolean isLocationPermissionGranted() {
        return
                ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                        && ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED;
    }

    public LocationDomain getLocationDomain(@NonNull LocationDomain.LocationDomainListener listener) {
        if (mLocationDomain != null) {
            return mLocationDomain;
        }

        mLocationDomain = LocationDomain.getInstance(this, listener);
        return mLocationDomain;
    }

//    public GmtSharedPreferences getGmtPreferences() {
//        return GmtSharedPreferences.getInstance(this);
//    }
}
