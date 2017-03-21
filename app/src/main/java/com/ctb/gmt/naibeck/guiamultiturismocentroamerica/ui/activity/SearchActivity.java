package com.ctb.gmt.naibeck.guiamultiturismocentroamerica.ui.activity;

import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;

import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.R;
import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.databinding.ActivitySearchBinding;
import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.ui.fragment.SearchFragment;

/**
 * Created by Kevin Gomez on 3/20/2017.
 */

public class SearchActivity extends BaseActivity<ActivitySearchBinding, Void> {
    private SearchFragment mSearchFragment;
    private String mSearchRequest;

    @Override
    public int getLayout() {
        return R.layout.activity_search;
    }

    @Override
    public Void getViewModel() {
        return null;
    }

    @Override
    public void setViewModelToBinding() {

    }

    @Override
    public void initComponents(ActivitySearchBinding binding) {
        mSearchRequest = getIntent().getStringExtra(SearchFragment.SEARCH_REQUEST);
        replaceFragment(R.id.searchContainer, getSearchFragment(mSearchRequest));
    }

    private SearchFragment getSearchFragment(@NonNull String searchRequest) {
        if (mSearchFragment == null) {
            mSearchFragment = SearchFragment.getInstance(searchRequest);
        }
        return mSearchFragment;
    }

    @Override
    public Toolbar getToolbar() {
        return getBinding().searchToolbar.toolbar;
    }

    @Override
    public boolean isHomeAsUpEnable() {
        return true;
    }
}
