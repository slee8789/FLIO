package com.fund.flio.ui.main.profile;


import android.view.View;

import com.fund.flio.data.DataManager;
import com.fund.flio.di.provider.ResourceProvider;
import com.fund.flio.di.provider.SchedulerProvider;
import com.fund.flio.ui.base.BaseViewModel;
import com.orhanobut.logger.Logger;

public class ProfileViewModel extends BaseViewModel {

    public ProfileViewModel(DataManager dataManager, SchedulerProvider schedulerProvider, ResourceProvider resourceProvider) {
        super(dataManager, schedulerProvider, resourceProvider);
        Logger.d("LoginViewModel constructor");
    }

    public void onTest(View v) {
        Logger.d("LoginViewModel onTest");

    }
}
