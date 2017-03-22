package com.ctb.gmt.naibeck.guiamultiturismocentroamerica.ui.fragment;

import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.R;
import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.databinding.FragmentDirectoryBinding;
import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.model.CategoryPlace;
import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.model.Places;
import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.ui.activity.PlaceDetailActivity;
import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.ui.adapter.PlaceListAdapter;
import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.utility.LineItemDecoratorSeparator;
import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.viewmodel.DirectoryViewModel;
import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.viewmodel.ItemPlaceListViewModel;

import static com.ctb.gmt.naibeck.guiamultiturismocentroamerica.ui.fragment.CategoryFragment.PLACE;

public class DirectoryFragment extends BaseFragment<FragmentDirectoryBinding, DirectoryViewModel>
        implements DirectoryViewModel.DirectoryListListener<CategoryPlace>,ItemPlaceListViewModel.PlaceListItemClickListener<Places> {

    private static final String TAG = DirectoryFragment.class.getName();

    public static DirectoryFragment getInstance() {
        DirectoryFragment directoryFragment = new DirectoryFragment();
        return directoryFragment;
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_directory;
    }

    @Override
    public DirectoryViewModel getViewModel() {
        return DirectoryViewModel.getInstance(this, getCategoryDomain(), this);
    }

    @Override
    public void setViewModelToBinding() {
        getBinding().setViewModel(getViewModel());
    }

    @Override
    public void initComponents() {
        getBinding().directoryRecycler.addItemDecoration(new LineItemDecoratorSeparator(getContext()));
        getViewModel().onDataLoad();

    }

    @Override
    public void onDataLoad(CategoryPlace item) {
        getBinding().directoryRecycler.setAdapter(new PlaceListAdapter(getContext(), item.getPlaceList(), this));
    }

    @Override
    public void onItemClick(Places item) {
        getContext().startActivity(goNextActivity(PlaceDetailActivity.class)
                .putExtra(PLACE, item));
    }
}
