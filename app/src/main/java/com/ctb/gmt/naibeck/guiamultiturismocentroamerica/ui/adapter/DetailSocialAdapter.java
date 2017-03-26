package com.ctb.gmt.naibeck.guiamultiturismocentroamerica.ui.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.R;
import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.databinding.ItemPlaceDetailSocialBinding;
import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.model.Socials;
import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.ui.activity.PlaceDetailActivity;
import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.viewmodel.DetailSocialItemViewModel;

import java.util.List;

/**
 * Created by Kevin Gomez on 3/26/2017.
 */

public class DetailSocialAdapter extends RecyclerView.Adapter<DetailSocialAdapter.ViewHolder> {
    private List<Socials> mSocialList;
    private DetailSocialItemViewModel.OnSocialClickListener<Socials> mSocialClickListener;

    public DetailSocialAdapter(List<Socials> mSocialList, DetailSocialItemViewModel.OnSocialClickListener<Socials> mSocialClickListener) {
        this.mSocialList = mSocialList;
        this.mSocialClickListener = mSocialClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_place_detail_social, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Socials social = mSocialList.get(position);
        holder.binding.setViewModel(new DetailSocialItemViewModel(social, mSocialClickListener));
    }

    @Override
    public int getItemCount() {
        return mSocialList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public ItemPlaceDetailSocialBinding binding;

        public ViewHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }
}
