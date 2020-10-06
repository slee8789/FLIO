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
import com.fund.flio.databinding.DialogLogoutBinding;
import com.fund.flio.di.provider.SchedulerProvider;
import com.fund.flio.ui.base.BaseDialog;
import com.fund.flio.ui.main.AuthViewModel;
import com.fund.flio.ui.main.MainActivity;

import javax.inject.Inject;

public class LogoutDialog extends BaseDialog {

    private AuthViewModel authViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        DialogLogoutBinding binding = DataBindingUtil.inflate(inflater, R.layout.dialog_logout, container, false);
        View view = binding.getRoot();
//        authViewModel = getViewModelProvider().get(AuthViewModel.class);
        authViewModel = ((MainActivity) getBaseActivity()).getAuthViewModel();
        binding.setAuthViewModel(authViewModel);
        binding.cancel.setOnClickListener(v -> ((MainActivity) getBaseActivity()).getNavController().navigateUp());
        return view;
    }
}
