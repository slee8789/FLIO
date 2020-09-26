package com.fund.flio.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.fund.flio.R;
import com.fund.flio.databinding.BottomSheetWriteBinding;
import com.fund.flio.ui.base.BaseBottomSheetDialog;

import dagger.android.support.AndroidSupportInjection;

public class WriteBottomSheetDialog extends BaseBottomSheetDialog {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        AndroidSupportInjection.inject(this);
        BottomSheetWriteBinding binding = DataBindingUtil.inflate(inflater, R.layout.bottom_sheet_write, container, false);
        View view = binding.getRoot();
        binding.market.setOnClickListener(v -> ((MainActivity)getBaseActivity()).getNavController().navigate(R.id.action_nav_bottom_sheet_write_to_nav_market_product_register));
        return view;
    }


}
