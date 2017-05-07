package com.ctb.gmt.naibeck.guiamultiturismocentroamerica.ui.fragment;

import android.support.annotation.NonNull;
import android.view.View;

import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.R;
import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.databinding.FragmentHomeBinding;
import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.ui.activity.CategoryActivity;
import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.viewmodel.HomeViewModel;

import static com.ctb.gmt.naibeck.guiamultiturismocentroamerica.ui.activity.MainActivity.NITE_LIFE;
import static com.ctb.gmt.naibeck.guiamultiturismocentroamerica.ui.activity.MainActivity.SELECTED_CATEGORY;
import static com.ctb.gmt.naibeck.guiamultiturismocentroamerica.ui.activity.MainActivity.TOURISTIC_PLACE;
import static com.ctb.gmt.naibeck.guiamultiturismocentroamerica.ui.activity.MainActivity.WHAT_WE_DO;
import static com.ctb.gmt.naibeck.guiamultiturismocentroamerica.ui.activity.MainActivity.WHAT_WE_EAT;
import static com.ctb.gmt.naibeck.guiamultiturismocentroamerica.ui.activity.MainActivity.WHERE_DO_WE_SHOP;
import static com.ctb.gmt.naibeck.guiamultiturismocentroamerica.ui.activity.MainActivity.WHERE_WE_STAY;

public class HomeFragment extends BaseFragment<FragmentHomeBinding, HomeViewModel> implements View.OnClickListener {
    private static final String TAG = HomeFragment.class.getName();

    public static HomeFragment getInstance() {
        return new HomeFragment();
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_home;
    }

    @Override
    public HomeViewModel getViewModel() {
        return HomeViewModel.getInstance(this);
    }

    @Override
    public void setViewModelToBinding() {
        getBinding().setViewModel(getViewModel());
    }

    @Override
    public void onStop() {
        super.onStop();
        getViewModel().removeInstance();
    }

    @Override
    public void initComponents() {
        getBinding().homeActividades.setOnClickListener(this);
        getBinding().homeComida.setOnClickListener(this);
        getBinding().homeCompra.setOnClickListener(this);
        getBinding().homeHospedaje.setOnClickListener(this);
        getBinding().homeNocturno.setOnClickListener(this);
        getBinding().homeLugares.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.homeActividades:
                goCategoryActivity(WHAT_WE_DO);
                break;
            case R.id.homeComida:
                goCategoryActivity(WHAT_WE_EAT);
                break;
            case R.id.homeCompra:
                goCategoryActivity(WHERE_DO_WE_SHOP);
                break;
            case R.id.homeHospedaje:
                goCategoryActivity(WHERE_WE_STAY);
                break;
            case R.id.homeNocturno:
                goCategoryActivity(NITE_LIFE);
                break;
            case R.id.homeLugares:
                goCategoryActivity(TOURISTIC_PLACE);
                break;
        }
    }

    public void goCategoryActivity(@NonNull String categoryId) {
        startActivity(goNextActivity(CategoryActivity.class)
                .putExtra(SELECTED_CATEGORY, categoryId));
    }
}
