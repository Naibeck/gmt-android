package com.ctb.gmt.naibeck.guiamultiturismocentroamerica.ui.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.R;
import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.databinding.ItemPlaceDetailGalleryBinding;
import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.model.GalleryImage;
import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.viewmodel.DetailGalleryItemViewModel;

import java.util.List;

/**
 * Created by Kevin Gomez on 3/25/2017.
 */

public class DetailGalleryAdapter extends RecyclerView.Adapter<DetailGalleryAdapter.ViewHolder> {
    private List<GalleryImage> mGalleryImageList;

    public DetailGalleryAdapter(List<GalleryImage> mGalleryImageList) {
        this.mGalleryImageList = mGalleryImageList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_place_detail_gallery, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        GalleryImage galleryImage = mGalleryImageList.get(position);
        holder.binding.setViewModel(new DetailGalleryItemViewModel(galleryImage));
    }

    @Override
    public int getItemCount() {
        return mGalleryImageList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ItemPlaceDetailGalleryBinding binding;

        public ViewHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }
}
