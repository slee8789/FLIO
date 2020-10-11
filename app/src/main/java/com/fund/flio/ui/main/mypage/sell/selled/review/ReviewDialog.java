package com.fund.flio.ui.main.mypage.sell.selled.review;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.fund.flio.R;
import com.fund.flio.databinding.DialogLogoutBinding;
import com.fund.flio.databinding.DialogReviewBinding;
import com.fund.flio.ui.base.BaseDialog;
import com.fund.flio.ui.main.AuthViewModel;
import com.fund.flio.ui.main.MainActivity;
import com.fund.flio.ui.main.market.product.ProductFragmentArgs;

public class ReviewDialog extends BaseDialog {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        DialogReviewBinding binding = DataBindingUtil.inflate(inflater, R.layout.dialog_review, container, false);
        View view = binding.getRoot();
        binding.review.setText(ReviewDialogArgs.fromBundle(getArguments()).getReviewType().getType());
        return view;
    }
}
