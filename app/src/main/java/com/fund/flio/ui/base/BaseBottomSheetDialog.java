package com.fund.flio.ui.base;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.RelativeLayout;

import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import com.fund.flio.di.ViewModelProviderFactory;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.orhanobut.logger.Logger;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;
import io.reactivex.annotations.NonNull;

public abstract class BaseBottomSheetDialog extends BottomSheetDialogFragment {

    @Inject
    public ViewModelProviderFactory viewModelProviderFactory;

    private BaseActivity mActivity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof BaseActivity) {
            BaseActivity mActivity = (BaseActivity) context;
            this.mActivity = mActivity;
        }
    }

    @Override
    public void onDetach() {
        mActivity = null;
        super.onDetach();
    }

    public BaseActivity getBaseActivity() {
        return mActivity;
    }
}
