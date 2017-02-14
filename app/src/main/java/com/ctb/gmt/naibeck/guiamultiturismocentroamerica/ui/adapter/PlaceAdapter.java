package com.ctb.gmt.naibeck.guiamultiturismocentroamerica.ui.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.R;
import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.databinding.ItemPlacesBinding;
import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.model.Places;
import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.viewmodel.PlaceItemViewModel;

import java.util.List;

public class PlaceAdapter extends RecyclerView.Adapter<PlaceAdapter.ViewHolder> {
    private static final String TAG = PlaceAdapter.class.getName();

    private Context mContext;
    private List<Places> mPlacesList;
    private PlaceItemViewModel.PlaceViewModelListener.PlaceItemClickListener mPlaceItemClickListener;

    public PlaceAdapter(Context mContext,
                        List<Places> mPlacesList,
                        PlaceItemViewModel.PlaceViewModelListener.PlaceItemClickListener mPlaceItemClickListener) {
        this.mContext = mContext;
        this.mPlacesList = mPlacesList;
        this.mPlaceItemClickListener = mPlaceItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_places, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Places places = mPlacesList.get(position);
        holder.mBinding.setViewModel(new PlaceItemViewModel(mContext, places, mPlaceItemClickListener));
    }

    @Override
    public int getItemCount() {
        return mPlacesList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ItemPlacesBinding mBinding;

        public ViewHolder(View itemView) {
            super(itemView);
            mBinding = DataBindingUtil.bind(itemView);
        }
    }
}
