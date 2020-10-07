package com.fund.flio.ui.main.market.product.detail;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.fund.flio.R;
import com.fund.flio.data.enums.PurchaseKind;
import com.fund.flio.data.enums.UseDate;
import com.fund.flio.databinding.DialogLogoutBinding;
import com.fund.flio.databinding.DialogProductDetailBinding;
import com.fund.flio.ui.base.BaseDialog;
import com.fund.flio.ui.main.AuthViewModel;
import com.fund.flio.ui.main.MainActivity;
import com.fund.flio.ui.main.market.MarketFragmentArgs;

public class ProductDetailDialog extends BaseDialog {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        DialogProductDetailBinding binding = DataBindingUtil.inflate(inflater, R.layout.dialog_product_detail, container, false);
        View view = binding.getRoot();

        binding.useDate.setText(UseDate.valueOf(ProductDetailDialogArgs.fromBundle(getArguments()).getProduct().getUseDate()).getType());
        binding.boxYn.setText(ProductDetailDialogArgs.fromBundle(getArguments()).getProduct().getBoxYn().equals("Y") ? "있음" : "없음");
        binding.purchaseKind.setText(PurchaseKind.valueOf(ProductDetailDialogArgs.fromBundle(getArguments()).getProduct().getPurchaseKind()).getType());
        binding.repairYn.setText(ProductDetailDialogArgs.fromBundle(getArguments()).getProduct().getRepairYn().equals("Y") ? "있음" : "없음");
        binding.brand.setText(ProductDetailDialogArgs.fromBundle(getArguments()).getProduct().getBrand());
        binding.model.setText(ProductDetailDialogArgs.fromBundle(getArguments()).getProduct().getModelNo());
        binding.serial.setText(ProductDetailDialogArgs.fromBundle(getArguments()).getProduct().getSerialNo());
        return view;
    }
}
