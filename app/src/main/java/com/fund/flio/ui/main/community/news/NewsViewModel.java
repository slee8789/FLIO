package com.fund.flio.ui.main.community.news;


import com.fund.flio.data.DataManager;
import com.fund.flio.di.provider.ResourceProvider;
import com.fund.flio.di.provider.SchedulerProvider;
import com.fund.flio.ui.base.BaseViewModel;

public class NewsViewModel extends BaseViewModel {

    public NewsViewModel(DataManager dataManager, SchedulerProvider schedulerProvider, ResourceProvider resourceProvider) {
        super(dataManager, schedulerProvider, resourceProvider);
    }


}
