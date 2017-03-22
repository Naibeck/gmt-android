package com.ctb.gmt.naibeck.guiamultiturismocentroamerica.ui.fragment;

import android.os.Bundle;
import android.util.Log;

import com.android.annotations.NonNull;
import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.R;
import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.databinding.FragmentSearchBinding;
import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.model.CategoryPlace;
import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.model.Places;
import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.ui.activity.PlaceDetailActivity;
import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.ui.adapter.PlaceListAdapter;
import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.utility.LineItemDecoratorSeparator;
import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.viewmodel.ItemPlaceListViewModel;
import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.viewmodel.SearchViewModel;

import static com.ctb.gmt.naibeck.guiamultiturismocentroamerica.ui.fragment.CategoryFragment.PLACE;

public class SearchFragment extends BaseFragment<FragmentSearchBinding, SearchViewModel>
        implements SearchViewModel.SearchListListener<CategoryPlace>,
        ItemPlaceListViewModel.PlaceListItemClickListener<Places> {
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
        return SearchViewModel.getInstance(this, getSearchdomain(), this);
    }

    @Override
    public void setViewModelToBinding() {
        getBinding().setViewModel(getViewModel());
    }

    @Override
    public void initComponents() {
        super.initComponents();
        String searchRequest = getArguments().getString(SEARCH_REQUEST);
        getBinding().searchRecycler.addItemDecoration(new LineItemDecoratorSeparator(getContext()));
        getViewModel().loadData(searchRequest);
    }

    @Override
    public void onStop() {
        super.onStop();
        getViewModel().onDestroyInstance();
    }


    @Override
    public void onDataLoad(CategoryPlace item) {
        getBinding().searchRecycler.setAdapter(new PlaceListAdapter(getContext(), item.getPlaceList(), this));
    }

    @Override
    public void onItemClick(Places item) {
        getContext().startActivity(goNextActivity(PlaceDetailActivity.class)
                .putExtra(PLACE, item));
    }
}
