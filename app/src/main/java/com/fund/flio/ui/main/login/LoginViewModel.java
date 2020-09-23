package com.fund.flio.ui.main.login;


import android.view.View;

import androidx.databinding.ObservableField;

import com.fund.flio.data.DataManager;
import com.fund.flio.data.model.body.TestBody;
import com.fund.flio.di.provider.ResourceProvider;
import com.fund.flio.di.provider.SchedulerProvider;
import com.fund.flio.ui.base.BaseViewModel;
import com.orhanobut.logger.Logger;

public class LoginViewModel extends BaseViewModel {

    public LoginViewModel(DataManager dataManager, SchedulerProvider schedulerProvider, ResourceProvider resourceProvider) {
        super(dataManager, schedulerProvider, resourceProvider);
        Logger.d("LoginViewModel constructor");
    }

    public void onTest(View v) {
        Logger.d("LoginViewModel onTest");

    }
}
