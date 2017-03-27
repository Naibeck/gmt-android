package com.ctb.gmt.naibeck.guiamultiturismocentroamerica.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.R;
import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.model.GalleryImage;

/**
 * Created by Kevin Gomez on 3/26/2017.
 */

public class GalleryItemActivity extends AppCompatActivity {
    private ImageView mGalleryItem;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery_item);
        mGalleryItem = (ImageView) findViewById(R.id.galleryItem);

        String galleryImage = getIntent().getStringExtra(PlaceDetailActivity.GALLERY_IMAGE);
        Log.d("GalleryFull", "onCreate: " + galleryImage);
        Glide.with(this)
                .load("http://" + galleryImage)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(mGalleryItem);
    }
}
