package com.ctb.gmt.naibeck.guiamultiturismocentroamerica.utility;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ctb.gmt.naibeck.guiamultiturismocentroamerica.R;

public class LineItemDecoratorSeparator extends RecyclerView.ItemDecoration {
    private static final String TAG = LineItemDecoratorSeparator.class.getName();

    private Drawable mDrawable;

    public LineItemDecoratorSeparator(@NonNull Context context) {
        mDrawable = ContextCompat.getDrawable(context, R.drawable.line_separator);
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();

        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);

            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();

            int top = child.getBottom() + params.bottomMargin;
            int bottom = top + mDrawable.getIntrinsicHeight();

            mDrawable.setBounds(left, top, right, bottom);
            
            mDrawable.draw(c);
        }
    }
}
