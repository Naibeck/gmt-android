package com.ctb.gmt.naibeck.guiamultiturismocentroamerica.ui.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.R;
import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.databinding.ItemPlaceListBinding;
import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.model.Places;
import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.viewmodel.ItemPlaceListViewModel;

import java.util.List;

public class PlaceListAdapter extends RecyclerView.Adapter<PlaceListAdapter.ViewHolder> {
    private static final String TAG = PlaceListAdapter.class.getName();

    private Context mContext;
    private List<Places> mPlaces;
    private ItemPlaceListViewModel.PlaceListItemClickListener mPlaceClickListener;

    public PlaceListAdapter(Context mContext,
                            List<Places> mPlaces,
                            ItemPlaceListViewModel.PlaceListItemClickListener mPlaceClickListener) {
        this.mContext = mContext;
        this.mPlaces = mPlaces;
        this.mPlaceClickListener = mPlaceClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_place_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Places place = mPlaces.get(position);

        holder.mBinding.setViewModel(new ItemPlaceListViewModel(place, mPlaceClickListener));
    }

    @Override
    public int getItemCount() {
        return mPlaces.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ItemPlaceListBinding mBinding;

        public ViewHolder(View itemView) {
            super(itemView);
            mBinding = DataBindingUtil.bind(itemView);
        }
    }
}
