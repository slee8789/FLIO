package com.fund.flio.ui.main.intro;


import com.fund.flio.data.DataManager;
import com.fund.flio.di.provider.ResourceProvider;
import com.fund.flio.di.provider.SchedulerProvider;
import com.fund.flio.ui.base.BaseViewModel;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;

public class IntroViewModel extends BaseViewModel {

    public IntroViewModel(DataManager dataManager, SchedulerProvider schedulerProvider, ResourceProvider resourceProvider) {
        super(dataManager, schedulerProvider, resourceProvider);
//        getCompositeDisposable().add(Observable.timer(2, TimeUnit.SECONDS)
//                .observeOn(schedulerProvider.ui())
//                .subscribe(Void -> {
////                    getNavigator().checkAuth()
//                }));
    }

}
