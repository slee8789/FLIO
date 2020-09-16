package com.fund.flio.ui.main.community;


import com.fund.flio.data.DataManager;
import com.fund.flio.di.provider.ResourceProvider;
import com.fund.flio.di.provider.SchedulerProvider;
import com.fund.flio.ui.base.BaseViewModel;

public class CommunityViewModel extends BaseViewModel {

    public CommunityViewModel(DataManager dataManager, SchedulerProvider schedulerProvider, ResourceProvider resourceProvider) {
        super(dataManager, schedulerProvider, resourceProvider);
    }


}
