package com.ctb.gmt.naibeck.guiamultiturismocentroamerica.domain;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

public class LocationDomain implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener {
    private static final String TAG = LocationDomain.class.getName();

    private static final int FASTEST_INTERVAL = 60000 * 5;
    private static final int INTERVAL = 60000 * 15;

    private AppCompatActivity mActivity;
    private LocationDomainListener mLocationDomainListener;
    private GoogleApiClient mGoogleApiClient;
    private LocationRequest mLocationRequest;

    public LocationDomain(@NonNull AppCompatActivity mActivity,
                          @NonNull LocationDomainListener listener) {
        this.mActivity = mActivity;
        this.mLocationDomainListener = listener;
        initGoogleApiClient();
    }

    private void initGoogleApiClient() {
        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(mActivity.getApplicationContext())
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }
        getRequest();
    }

    public void handleOnStart() {
        mGoogleApiClient.connect();
    }

    public void handleOnStop() {
        mGoogleApiClient.disconnect();
    }

    public Location getLastKnownLocation() {
        if (ActivityCompat.checkSelfPermission(mActivity.getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(mActivity.getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return null;
        }

        return LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
    }

    public void startLocationUpdates() {
        if (ActivityCompat.checkSelfPermission(mActivity.getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(mActivity.getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
    }

    public void removeLocationUpdates() {
        LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
    }

    private LocationRequest getRequest() {
        if (mLocationRequest != null) {
            return mLocationRequest;
        }

        mLocationRequest = new LocationRequest()
                .setInterval(INTERVAL)
                .setFastestInterval(FASTEST_INTERVAL)
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        return mLocationRequest;
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        mLocationDomainListener.onConnected(bundle);
    }

    @Override
    public void onConnectionSuspended(int i) {
        mLocationDomainListener.onConnectionSuspended(i);
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        mLocationDomainListener.onConnectionFailed(connectionResult);
    }

    @Override
    public void onLocationChanged(Location location) {
        mLocationDomainListener.onLocationChanged(location);
    }

    public interface LocationDomainListener {
        void onConnected(@Nullable Bundle bundle);
        void onConnectionSuspended(int i);
        void onConnectionFailed(@NonNull ConnectionResult connectionResult);
        void onLocationChanged(Location location);
    }
}
