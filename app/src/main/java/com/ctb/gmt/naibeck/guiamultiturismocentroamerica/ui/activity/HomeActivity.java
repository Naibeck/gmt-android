package com.ctb.gmt.naibeck.guiamultiturismocentroamerica.ui.activity;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
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

    public static final String SELECTED_CATEGORY = "selectedCategory";

    private NavigationView mNavMenu;
    private DrawerLayout mDrawerMenu;
    private BottomNavigationView mBottomView;
//    private HomeFragment mHome;
//    private MapFragment mMap;

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
    public void initComponents(final ActivityHomeBinding binding) {
        mDrawerMenu = binding.homeDrawerLayout;
        mNavMenu = binding.homeNavigationView;

        setupNavMenu();
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
        mBottomView = getBinding().contentHome.bottomNavigation;
        mBottomView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.bottomHome:
                        //TODO: Load and create a fragment
                        break;
                    case R.id.bottomMap:
                        //TODO: Load and create a fragment
                        break;
                }
                return false;
            }
        });
    }

//    private HomeFragment() {
//        if (mHome != null) {
//            return mHome;
//        }
//
//        mHome = HomeFragment.getInstance();
//        return mHome;
//    }
//
//    private MapFragment() {
//        if (mMap != null) {
//            return mMap;
//        }
//
//        mMap = MapFragment.getInstance();
//        return mMap;
//    }

    public void goCategoryActivity(@NonNull String categoryId) {
//        goNextActivity(CategoryActivity.class)
//                .putExtra(SELECTED_CATEGORY, categoryId);
    }
}
