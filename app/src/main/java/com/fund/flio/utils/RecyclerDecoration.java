package com.fund.flio.utils;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fund.flio.R;
import com.nhn.android.idp.common.logger.Logger;

public class RecyclerDecoration extends RecyclerView.ItemDecoration {

    private Drawable mDivider;

    public RecyclerDecoration(Context context) {
        mDivider = context.getResources().getDrawable(R.drawable.line_divider);

    }

    @Override
    public void onDrawOver(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        int top = parent.getPaddingStart() + 50;
        int bottom = parent.getWidth() - parent.getPaddingEnd() - 50;

        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);

            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            int start = child.getRight() + params.rightMargin + 50;
            int end = start + mDivider.getIntrinsicWidth() + 50;

            mDivider.setBounds(start, top, end, bottom);
            mDivider.draw(c);
        }

    }
}
