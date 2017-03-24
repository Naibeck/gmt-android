package com.ctb.gmt.naibeck.guiamultiturismocentroamerica.ui.activity;

import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;

import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.R;
import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.databinding.ActivityCategoryBinding;
import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.ui.fragment.CategoryFragment;

public class CategoryActivity extends BaseActivity<ActivityCategoryBinding, Void> {
    private static final String TAG = CategoryActivity.class.getName();

    private CategoryFragment mCategoryFragment;
    private String mCategoryId;

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

    @Override
    public void initComponents(ActivityCategoryBinding binding) {
        categoryTitle();
        replaceFragment(R.id.categoryContainer, getCategoryFragment(mCategoryId));
    }

    private void categoryTitle() {
        mCategoryId = getIntent().getStringExtra(MainActivity.SELECTED_CATEGORY);
        String categoryTitle;
        switch (mCategoryId) {
            case MainActivity.WHAT_WE_EAT:
                categoryTitle = getString(R.string.que_comemos);
                break;
            case MainActivity.WHAT_WE_DO:
                categoryTitle = getString(R.string.que_hacemos);
                break;
            case MainActivity.WHERE_WE_STAY:
                categoryTitle = getString(R.string.donde_quedamos);
                break;
            case MainActivity.NITE_LIFE:
                categoryTitle = getString(R.string.nite_life);
                break;
            case MainActivity.WHERE_DO_WE_SHOP:
                categoryTitle = getString(R.string.donde_compramos);
                break;
            default:
                categoryTitle = "Error";
        }
        setTitle(categoryTitle);
    }

    private CategoryFragment getCategoryFragment(@NonNull String categoryId) {
        if (mCategoryFragment != null) {
            return mCategoryFragment;
        }

        mCategoryFragment = CategoryFragment.getInstance(categoryId);
        return mCategoryFragment;
    }

    @Override
    protected void onStop() {
        super.onStop();
        getGmtPreferences().removeInstance();
        getCategoryDomain().removeInstance();
    }
}
