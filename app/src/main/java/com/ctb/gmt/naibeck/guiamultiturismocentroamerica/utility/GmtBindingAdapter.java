package com.ctb.gmt.naibeck.guiamultiturismocentroamerica.utility;

import android.databinding.BindingAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.R;

public class GmtBindingAdapter {
    private static final String TAG = GmtBindingAdapter.class.getName();
    private static final String HTTP = "http://";

    private static final int FACEBOOK = 1;
    private static final int TWITTER = 2;
    private static final int INSTAGRAM = 3;
    private static final int WEB = 4;

    @BindingAdapter("app:logoUrl")
    public static void setLogoUrl(ImageView view, String url) {
        Glide.with(view.getContext())
                .load(HTTP + url)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .error(R.mipmap.ic_launcher)
                .into(view);
    }

    @BindingAdapter("app:logoSocial")
    public static void setLogoSocial(ImageView view, int socialId) {
        switch (socialId) {
            case FACEBOOK:
                view.setImageDrawable(ContextCompat.getDrawable(view.getContext(), R.drawable.facebook));
                break;
            case TWITTER:
                view.setImageDrawable(ContextCompat.getDrawable(view.getContext(), R.drawable.twitter));
                break;
            case INSTAGRAM:
                view.setImageDrawable(ContextCompat.getDrawable(view.getContext(), R.drawable.insta));
                break;
            case WEB:
                view.setImageDrawable(ContextCompat.getDrawable(view.getContext(), R.drawable.web));
                break;
        }
    }
}
