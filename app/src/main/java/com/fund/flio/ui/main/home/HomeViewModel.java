package com.fund.flio.ui.main.home;


import com.fund.flio.data.DataManager;
import com.fund.flio.di.provider.ResourceProvider;
import com.fund.flio.di.provider.SchedulerProvider;
import com.fund.flio.ui.base.BaseViewModel;

public class HomeViewModel extends BaseViewModel<HomeNavigator> {

    public HomeViewModel(DataManager dataManager, SchedulerProvider schedulerProvider, ResourceProvider resourceProvider) {
        super(dataManager, schedulerProvider, resourceProvider);
    }

    public void testDetail() {
        getNavigator().goDetail();
    }

    public void testSelect() {
        getDataManager().getTestSelect().subscribe();
    }


}
