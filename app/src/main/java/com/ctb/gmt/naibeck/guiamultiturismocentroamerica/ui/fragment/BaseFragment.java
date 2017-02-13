package com.ctb.gmt.naibeck.guiamultiturismocentroamerica.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.R;

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

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initComponents();
    }

    @LayoutRes
    public abstract int getLayout();

    public VB getBinding() {
        return mBinding;
    }

    public abstract VM getViewModel();

    public abstract void setViewModelToBinding();

    public Intent goNextActivity(@NonNull Context context, @NonNull Class<?> activity) {
        return new Intent(context, activity);
    }

    public void replaceFragment(@IdRes int containerId, @NonNull Fragment fragment) {
        getFragmentManager().beginTransaction()
                .replace(containerId, fragment)
                .commit();
    }

    public void initComponents() {

    }

    public void addFragment(@IdRes int containerId, @NonNull Fragment fragment) {
        getFragmentManager().beginTransaction()
                .add(containerId, fragment)
                .commit();
    }

    public @ColorInt int getColor(@ColorRes int resourceColor) {
        return ContextCompat.getColor(getContext(), resourceColor);
    }
}
