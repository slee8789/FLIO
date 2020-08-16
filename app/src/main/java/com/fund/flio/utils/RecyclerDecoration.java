package com.fund.flio.utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fund.flio.R;
import com.nhn.android.idp.common.logger.Logger;

public class RecyclerDecoration extends DividerItemDecoration {
    public RecyclerDecoration(Context context, int orientation) {
        super(context, orientation);
    }

    @Override
    public void setOrientation(int orientation) {
        super.setOrientation(orientation);
    }

    @Override
    public void setDrawable(@NonNull Drawable drawable) {
        super.setDrawable(drawable);
    }

    @Nullable
    @Override
    public Drawable getDrawable() {
        return super.getDrawable();
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
    }
}
