package com.fund.flio.ui.main.mypage.sell.selling.buyer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.fund.flio.R;
import com.fund.flio.databinding.BottomSheetWriteBinding;
import com.fund.flio.ui.base.BaseBottomSheetDialog;
import com.fund.flio.ui.main.MainActivity;

import dagger.android.support.AndroidSupportInjection;

public class SellCompleteBottomSheetDialog extends BaseBottomSheetDialog {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        AndroidSupportInjection.inject(this);
        BottomSheetWriteBinding binding = DataBindingUtil.inflate(inflater, R.layout.bottom_sheet_write, container, false);
        View view = binding.getRoot();
        binding.market.item.setOnClickListener(v -> ((MainActivity) getBaseActivity()).getNavController().navigate(R.id.action_nav_bottom_sheet_write_to_nav_market_product_register));
        binding.certificate.item.setOnClickListener(v -> ((MainActivity) getBaseActivity()).getNavController().navigate(R.id.action_nav_bottom_sheet_write_to_nav_community_certificate_register));
        binding.event.item.setOnClickListener(v -> ((MainActivity) getBaseActivity()).getNavController().navigate(R.id.action_nav_bottom_sheet_write_to_nav_community_event_register));
        return view;
    }


}
