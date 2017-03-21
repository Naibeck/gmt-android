package com.ctb.gmt.naibeck.guiamultiturismocentroamerica.ui.fragment;

import android.os.Bundle;

import com.android.annotations.NonNull;
import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.R;
import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.databinding.FragmentSearchBinding;
import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.viewmodel.SearchViewModel;

/**
 * Created by Kevin Gomez on 3/20/2017.
 */

public class SearchFragment extends BaseFragment<FragmentSearchBinding, SearchViewModel> {
    private static final String TAG = SearchFragment.class.getName();

    public static final String SEARCH_REQUEST = "searchRequest";

    public static SearchFragment getInstance(@NonNull String searchRequest) {
        SearchFragment searchFragment = new SearchFragment();
        Bundle args = new Bundle();
        args.putString(SEARCH_REQUEST, searchRequest);
        searchFragment.setArguments(args);
        return searchFragment;
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_search;
    }

    @Override
    public SearchViewModel getViewModel() {
        return SearchViewModel.getInstance(this, getSearchdomain());
    }

    @Override
    public void setViewModelToBinding() {
        getBinding().setViewModel(getViewModel());
    }

    @Override
    public void initComponents() {
        super.initComponents();
        String searchRequest = getArguments().getString(SEARCH_REQUEST);
        getViewModel().loadData(searchRequest);
    }

    @Override
    public void onStop() {
        super.onStop();
        getViewModel().onDestroyInstance();
    }
}
