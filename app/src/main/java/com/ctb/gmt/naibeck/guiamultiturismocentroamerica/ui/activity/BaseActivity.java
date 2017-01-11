package com.ctb.gmt.naibeck.guiamultiturismocentroamerica.ui.activity;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

/**
 * Handles the base logic for the rest of activities.
 *
 * @param <VB> reference to this databinding class instance
 * @param <VM> reference to this viewmodel class instance
 */
public abstract class BaseActivity<VB extends ViewDataBinding, VM> extends AppCompatActivity {
    private static final String TAG = BaseActivity.class.getName();

    private VB mBinding;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, getLayout());
        setViewModelToBinding();

        initToolbar(getToolbar());
    }

    public  Toolbar  getToolbar() {
        return mToolbar;
    }

    public void initToolbar(Toolbar toolbar) {
        mToolbar = toolbar;
        if (mToolbar != null) {
            setSupportActionBar(toolbar);
            if (getSupportActionBar() != null) {
                getSupportActionBar().setDisplayHomeAsUpEnabled(isHomeAsUpEnable());
            }
        }
    }

    public boolean isHomeAsUpEnable() {
        return false;
    }

    @LayoutRes
    public abstract int getLayout();

    //Binding methods

    /**
     * Get ViewDataBinding for current activity, required in order to use binding features.
     *
     * @return current data binding instance
     */
    public VB getBinding() {
        return mBinding;
    }

    /**
     * Get viewmodel for current activity, required to access lower methods inside viewmodel.
     *
     * @return current viewmodel instance
     */
    public abstract VM getViewModel();

    public abstract void setViewModelToBinding();

    public Context getContext() {
        return getApplicationContext();
    }
}
