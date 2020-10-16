package com.fund.flio.ui.main.mypage.setting.logout;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.fund.flio.R;
import com.fund.flio.data.DataManager;
import com.fund.flio.databinding.DialogDeleteCacheBinding;
import com.fund.flio.databinding.DialogLogoutBinding;
import com.fund.flio.di.provider.SchedulerProvider;
import com.fund.flio.ui.base.BaseDialog;
import com.fund.flio.ui.main.AuthViewModel;
import com.fund.flio.ui.main.MainActivity;

import javax.inject.Inject;

public class DeleteCacheDialog extends BaseDialog {

    @Inject
    DataManager dataManager;

    @Inject
    SchedulerProvider schedulerProvider;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        DialogDeleteCacheBinding binding = DataBindingUtil.inflate(inflater, R.layout.dialog_delete_cache, container, false);
        View view = binding.getRoot();

        binding.confirm.setOnClickListener(v -> {
            dataManager.deleteAll()
                    .subscribeOn(schedulerProvider.io2())
                    .observeOn(schedulerProvider.ui2())
                    .subscribe(result -> ((MainActivity) getBaseActivity()).getNavController().navigateUp());

        });
        binding.cancel.setOnClickListener(v -> ((MainActivity) getBaseActivity()).getNavController().navigateUp());
        return view;
    }
}
