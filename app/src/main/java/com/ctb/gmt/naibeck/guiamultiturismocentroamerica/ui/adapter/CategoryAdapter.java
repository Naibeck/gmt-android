package com.ctb.gmt.naibeck.guiamultiturismocentroamerica.ui.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.R;
import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.databinding.ItemCategoryBinding;
import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.model.CategoryPlace;
import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.viewmodel.CategoryItemViewModel;
import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.viewmodel.PlaceItemViewModel;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
    private static final String TAG = CategoryAdapter.class.getName();

    private Context mContext;
    private List<CategoryPlace> mCategoryPlaces;
    private CategoryItemViewModel.CategoryItemViewModelListener mCategoryItemViewModelListener;
    private PlaceItemViewModel.PlaceViewModelListener.PlaceItemClickListener mPlaceItemClickListener;

    public CategoryAdapter(Context mContext,
                           List<CategoryPlace> mCategoryPlaces,
                           CategoryItemViewModel.CategoryItemViewModelListener mCategoryItemViewModelListener,
                           PlaceItemViewModel.PlaceViewModelListener.PlaceItemClickListener mPlaceItemClickListener) {
        this.mContext = mContext;
        this.mCategoryPlaces = mCategoryPlaces;
        this.mCategoryItemViewModelListener = mCategoryItemViewModelListener;
        this.mPlaceItemClickListener = mPlaceItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        CategoryPlace categoryPlace = mCategoryPlaces.get(position);
        RecyclerView placeList = holder.mBinding.placeList;

        holder.mBinding.setViewModel(new CategoryItemViewModel(categoryPlace, mCategoryItemViewModelListener));

        placeList.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
        placeList.setAdapter(new PlaceAdapter(mContext, categoryPlace.getPlaceList(), mPlaceItemClickListener));
        holder.mBinding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return mCategoryPlaces.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ItemCategoryBinding mBinding;

        public ViewHolder(View itemView) {
            super(itemView);
            mBinding = DataBindingUtil.bind(itemView);
        }
    }
}
