package com.fund.flio.ui.main.search;


import android.view.View;

import androidx.databinding.ObservableInt;

import com.fund.flio.data.DataManager;
import com.fund.flio.di.provider.ResourceProvider;
import com.fund.flio.di.provider.SchedulerProvider;
import com.fund.flio.ui.base.BaseViewModel;
import com.orhanobut.logger.Logger;

public class SearchViewModel extends BaseViewModel {

    public ObservableInt viewMode = new ObservableInt();

    public SearchViewModel(DataManager dataManager, SchedulerProvider schedulerProvider, ResourceProvider resourceProvider) {
        super(dataManager, schedulerProvider, resourceProvider);
    }

    public void onBackClick(View v) {

    }

    public void onSearchClick(View v) {
        Logger.d("onSearchClick");
        viewMode.set(View.GONE);
    }

    public void onCloseClick(View v) {

    }

}
