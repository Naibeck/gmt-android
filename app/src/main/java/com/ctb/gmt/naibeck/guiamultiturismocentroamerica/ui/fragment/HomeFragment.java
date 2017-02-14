package com.ctb.gmt.naibeck.guiamultiturismocentroamerica.ui.fragment;

import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.R;
import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.databinding.FragmentHomeBinding;
import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.viewmodel.HomeViewModel;

public class HomeFragment extends BaseFragment<FragmentHomeBinding, HomeViewModel> {
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
        return HomeViewModel.getInstance(this, getGmtPreferences());
    }

    @Override
    public void setViewModelToBinding() {
        getBinding().setViewModel(getViewModel());
    }
}
