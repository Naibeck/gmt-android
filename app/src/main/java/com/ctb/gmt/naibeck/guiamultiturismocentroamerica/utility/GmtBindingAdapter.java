package com.ctb.gmt.naibeck.guiamultiturismocentroamerica.utility;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.R;

public class GmtBindingAdapter {
    private static final String TAG = GmtBindingAdapter.class.getName();

    @BindingAdapter("app:logoUrl")
    public static void setLogoUrl(ImageView view, String url) {
        Glide.with(view.getContext())
                .load(url)
                .error(R.mipmap.ic_launcher)
                .into(view);
    }
}
