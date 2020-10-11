package com.fund.flio.ui.main.mypage.buy.review;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.Navigation;

import com.fund.flio.R;
import com.fund.flio.data.DataManager;
import com.fund.flio.data.bus.ProductBus;
import com.fund.flio.data.enums.ReviewType;
import com.fund.flio.databinding.DialogLogoutBinding;
import com.fund.flio.databinding.DialogReviewBinding;
import com.fund.flio.databinding.DialogReviewWriteBinding;
import com.fund.flio.di.provider.SchedulerProvider;
import com.fund.flio.ui.base.BaseDialog;
import com.fund.flio.ui.main.AuthViewModel;
import com.fund.flio.ui.main.MainActivity;

import java.util.Locale;

import javax.inject.Inject;

public class ReviewWriteDialog extends BaseDialog {

    @Inject
    DataManager dataManager;

    @Inject
    SchedulerProvider schedulerProvider;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        DialogReviewWriteBinding binding = DataBindingUtil.inflate(inflater, R.layout.dialog_review_write, container, false);
        View view = binding.getRoot();
        binding.message.setText(String.format(Locale.getDefault(), getString(R.string.product_buy_review_write_message), ReviewWriteDialogArgs.fromBundle(getArguments()).getUserName()));
        binding.confirm.setOnClickListener(v -> {
            ReviewType reviewType = ReviewType.A;
            switch (binding.radio.getCheckedRadioButtonId()) {
                case R.id.a:
                    reviewType = ReviewType.A;
                    break;
                case R.id.b:
                    reviewType = ReviewType.B;
                    break;
                case R.id.c:
                    reviewType = ReviewType.C;
                    break;
                case R.id.d:
                    reviewType = ReviewType.D;
                    break;
                case R.id.e:
                    reviewType = ReviewType.E;
                    break;
            }
            dataManager.targetUserReview(ReviewWriteDialogArgs.fromBundle(getArguments()).getProductId(), dataManager.getUserId(), reviewType.name())
                    .subscribeOn(schedulerProvider.io())
                    .observeOn(schedulerProvider.ui())
                    .subscribe(Void -> {
                        ProductBus.getInstance().reviewWriteDismiss();
                        ((MainActivity) getBaseActivity()).getNavController().navigateUp();
                    });
        });
        return view;
    }
}
