package com.ctb.gmt.naibeck.guiamultiturismocentroamerica.ui.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.R;
import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.databinding.ItemCategoryHeaderBinding;
import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.model.CategoryPlace;
import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.model.Places;
import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.viewmodel.CategoryHeaderItemViewModel;

import java.util.List;

public class HeaderCategoryAdapter extends RecyclerView.Adapter<HeaderCategoryAdapter.ViewHolder> {
    private static final String TAG = HeaderCategoryAdapter.class.getName();

    private Context mContext;
    private List<CategoryPlace> mCategoryPlaces;
    private CategoryHeaderItemViewModel.OnHeaderClickListener mOnHeaderClickListener;

    public HeaderCategoryAdapter(Context mContext,
                                 List<CategoryPlace> mCategoryPlaces,
                                 CategoryHeaderItemViewModel.OnHeaderClickListener mOnHeaderClickListener) {
        this.mContext = mContext;
        this.mCategoryPlaces = mCategoryPlaces;
        this.mOnHeaderClickListener = mOnHeaderClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category_header, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        CategoryPlace categoryPlace = mCategoryPlaces.get(position);
        holder.mBinding.setViewModel(new CategoryHeaderItemViewModel(categoryPlace, mOnHeaderClickListener));
    }

    @Override
    public int getItemCount() {
        return mCategoryPlaces.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ItemCategoryHeaderBinding mBinding;

        public ViewHolder(View itemView) {
            super(itemView);
            mBinding = DataBindingUtil.bind(itemView);
        }
    }
}
