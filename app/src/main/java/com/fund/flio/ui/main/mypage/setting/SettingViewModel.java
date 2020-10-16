package com.fund.flio.ui.main.mypage.setting;


import android.content.Intent;
import android.net.Uri;
import android.view.View;

import androidx.databinding.ObservableField;
import androidx.navigation.Navigation;

import com.fund.flio.BuildConfig;
import com.fund.flio.R;
import com.fund.flio.data.DataManager;
import com.fund.flio.di.provider.ResourceProvider;
import com.fund.flio.di.provider.SchedulerProvider;
import com.fund.flio.ui.base.BaseViewModel;
import com.fund.flio.ui.main.MainActivity;
import com.google.android.material.snackbar.Snackbar;

public class SettingViewModel extends BaseViewModel {

    public ObservableField<String> version = new ObservableField<>();

    public SettingViewModel(DataManager dataManager, SchedulerProvider schedulerProvider, ResourceProvider resourceProvider) {
        super(dataManager, schedulerProvider, resourceProvider);
        version.set(BuildConfig.VERSION_NAME);
    }

    public void deleteCache(View v) {
        Navigation.findNavController((MainActivity) v.getContext(), R.id.fragment_container).navigate(R.id.nav_delete_cache);
    }

    public void showLogout(View v) {
        Navigation.findNavController((MainActivity) v.getContext(), R.id.fragment_container).navigate(R.id.nav_logout);
    }

    public void signOut(View v) {
        Snackbar.make(((MainActivity) v.getContext()).getViewDataBinding().getRoot(), "관리자에게 문의하세요. slee8789@gmail.com", Snackbar.LENGTH_SHORT).show();
    }

    public void privacy(View v) {
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse("https://www.notion.so/6a00035c9e514d0393964063d2c9ef34"));
        (v.getContext()).startActivity(i);
    }

    public void notice(View v) {
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse("https://www.notion.so/50f2a34800df45c38816f5ca26a5df52"));
        (v.getContext()).startActivity(i);
    }


}
