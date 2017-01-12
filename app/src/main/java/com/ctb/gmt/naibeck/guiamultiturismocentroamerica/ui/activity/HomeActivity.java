package com.ctb.gmt.naibeck.guiamultiturismocentroamerica.ui.activity;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.R;
import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.databinding.ActivityHomeBinding;
import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.viewmodel.HomeViewModel;

public class HomeActivity extends BaseActivity<ActivityHomeBinding, HomeViewModel> {
    private static final String TAG = HomeActivity.class.getName();

    private NavigationView mNavMenu;
    private DrawerLayout mDrawerMenu;

    @Override
    public int getLayout() {
        return R.layout.activity_home;
    }

    @Override
    public HomeViewModel getViewModel() {
        return new HomeViewModel();
    }

    @Override
    public void setViewModelToBinding() {
        getBinding().setViewModel(getViewModel());
    }

    @Override
    public Toolbar getToolbar() {
        return getBinding().homeToolbar.toolbar;
    }

    @Override
    public void initComponents(ActivityHomeBinding binding) {
        mDrawerMenu = binding.homeDrawerLayout;
        mNavMenu = binding.homeNavigationView;

        setupNavMenu();
    }

    private void setupNavMenu() {
        mNavMenu.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.hotelesHostales:
                        Toast.makeText(getContext(), "Item", Toast.LENGTH_SHORT).show();
                        return true;
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
}
