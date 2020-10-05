package com.fund.flio.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.widget.Toolbar;
import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.fund.flio.R;
import com.google.android.material.appbar.AppBarLayout;
import com.orhanobut.logger.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public final class ViewUtils {

    private ViewUtils() {
        // This class is not publicly instantiable
    }

    public static int dpToPx(float dp, Context context) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.getResources().getDisplayMetrics());
    }

    public static int spToPx(float sp, Context context) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, context.getResources().getDisplayMetrics());
    }

    public static int dpToSp(float dp, Context context) {
        return (int) (dpToPx(dp, context) / context.getResources().getDisplayMetrics().scaledDensity);
    }

    public static String readAssetJson(Context context, String fileName) {
        StringBuilder buf = new StringBuilder();
        InputStream json = null;
        try {
            json = context.getAssets().open(fileName);
            BufferedReader in = new BufferedReader(new InputStreamReader(json, "UTF-8"));
            String str;
            while ((str = in.readLine()) != null) {
                buf.append(str);
            }

            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buf.toString();
    }

    public static int calculateNoOfColumns(Context context, float columnWidthDp) { // For example columnWidthdp=180
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        float screenWidthDp = displayMetrics.widthPixels / displayMetrics.density;
        int noOfColumns = (int) (screenWidthDp / columnWidthDp + 0.5); // +0.5 for correct rounding to int.
        return noOfColumns;
    }

    public static void setMarginsAppBar(Toolbar toolbar) {
        Logger.d("setMarginsAppBar");
        toolbar.setOnApplyWindowInsetsListener((v, insets) -> {
            Logger.d("setOnApplyWindowInsetsListener");
            v.setPadding(insets.getSystemWindowInsetLeft(), insets.getSystemWindowInsetTop(), insets.getSystemWindowInsetRight(), 0);

            return insets;
        });
    }

    public static void setPaddingStatusBarAndToolBar(Context mContext, View view, boolean isBottomPadding) {
        Logger.d("setPaddingStatusBarAndToolBar");
        final TypedValue tv = new TypedValue();
        final int[] actionBarHeight = {(int) dpToPx(56, mContext)};
        view.setOnApplyWindowInsetsListener((v, insets) -> {
            Logger.d("setOnApplyWindowInsetsListener");
            if (mContext.getTheme().resolveAttribute(R.attr.actionBarSize, tv, true))
                actionBarHeight[0] = TypedValue.complexToDimensionPixelSize(tv.data, mContext.getResources().getDisplayMetrics());
            if (isBottomPadding) {
                v.setPadding(insets.getSystemWindowInsetLeft(), insets.getSystemWindowInsetTop() + actionBarHeight[0], insets.getSystemWindowInsetRight(), insets.getSystemWindowInsetBottom());
            } else {
                v.setPadding(insets.getSystemWindowInsetLeft(), insets.getSystemWindowInsetTop() + actionBarHeight[0], insets.getSystemWindowInsetRight(), 0);
            }
            return insets;
        });
    }

    public static int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0)
            result = context.getResources().getDimensionPixelSize(resourceId);

        return result;
    }

    public static int getNavigationBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("navigation_bar_height", "dimen", "android");
        if (resourceId > 0)
            result = context.getResources().getDimensionPixelSize(resourceId);

        Logger.d("getNavigationBarHeight " + result);
        return result;
    }

}
