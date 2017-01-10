package com.ctb.gmt.naibeck.guiamultiturismocentroamerica.ui.activity;

import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.R;
import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.databinding.ActivityHomeBinding;
import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.viewmodel.HomeViewModel;

public class HomeActivity extends BaseActivity<ActivityHomeBinding, HomeViewModel> {
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
}