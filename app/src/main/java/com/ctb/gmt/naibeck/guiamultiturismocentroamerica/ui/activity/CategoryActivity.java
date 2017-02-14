package com.ctb.gmt.naibeck.guiamultiturismocentroamerica.ui.activity;

import android.support.v7.widget.Toolbar;

import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.R;
import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.databinding.ActivityCategoryBinding;

public class CategoryActivity extends BaseActivity<ActivityCategoryBinding, Void> {
    @Override
    public int getLayout() {
        return R.layout.activity_category;
    }

    @Override
    public Void getViewModel() {
        return null;
    }

    @Override
    public void setViewModelToBinding() {

    }

    @Override
    public Toolbar getToolbar() {
        return getBinding().categoryToolbar.toolbar;
    }

    @Override
    public boolean isHomeAsUpEnable() {
        return true;
    }
}
