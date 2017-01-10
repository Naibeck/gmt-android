package com.ctb.gmt.naibeck.guiamultiturismocentroamerica.ui.fragment;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Handles the base logic for the rest of fragments.
 *
 * @param <VB> reference to this databinding class instance
 * @param <VM> reference to this viewmodel class instance
 */
public abstract class BaseFragment<VB extends ViewDataBinding, VM> extends Fragment {
    private static final String TAG = BaseFragment.class.getName();

    private VB mBinding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root;
        mBinding = DataBindingUtil.inflate(inflater, getLayout(), container, false);
        setViewModelToBinding();
        root = getBinding().getRoot();
        return root;
    }

    @LayoutRes
    public abstract int getLayout();

    public VB getBinding() {
        return mBinding;
    }

    public abstract VM getViewModel();

    public abstract void setViewModelToBinding();

    public Context getContext() {
        return getContext();
    }
}
