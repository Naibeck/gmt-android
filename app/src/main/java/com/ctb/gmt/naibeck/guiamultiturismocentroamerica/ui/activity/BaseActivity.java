package com.ctb.gmt.naibeck.guiamultiturismocentroamerica.ui.activity;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Handles the base logic for the rest of activities.
 *
 * @param <VB> reference to this databinding class instance
 * @param <VM> reference to this viewmodel class instance
 */
public abstract class BaseActivity<VB extends ViewDataBinding, VM> extends AppCompatActivity {
    private static final String TAG = BaseActivity.class.getName();

    private VB mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, getLayout());
        setViewModelToBinding();
    }

    @LayoutRes
    public abstract int getLayout();

    public VB getBinding() {
        return mBinding;
    }

    public abstract VM getViewModel();

    public abstract void setViewModelToBinding();

    public Context getContext() {
        return getApplicationContext();
    }
}
