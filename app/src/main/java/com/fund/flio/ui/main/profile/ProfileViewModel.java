package com.fund.flio.ui.main.profile;


import android.view.View;

import androidx.databinding.ObservableField;

import com.fund.flio.data.DataManager;
import com.fund.flio.di.provider.ResourceProvider;
import com.fund.flio.di.provider.SchedulerProvider;
import com.fund.flio.ui.base.BaseViewModel;
import com.orhanobut.logger.Logger;

public class ProfileViewModel extends BaseViewModel {

    public ObservableField<String> sellerImage = new ObservableField<>();
    public ObservableField<String> sellerName = new ObservableField<>();
    public ObservableField<String> rating = new ObservableField<>("0");

    public ProfileViewModel(DataManager dataManager, SchedulerProvider schedulerProvider, ResourceProvider resourceProvider) {
        super(dataManager, schedulerProvider, resourceProvider);
        Logger.d("LoginViewModel constructor");
    }

    public void showProduct(View v) {
        Logger.d("LoginViewModel onTest");

    }

    public void showReview(View v) {
        Logger.d("LoginViewModel onTest");

    }

    public void showBoard(View v) {
        Logger.d("LoginViewModel onTest");

    }


}
