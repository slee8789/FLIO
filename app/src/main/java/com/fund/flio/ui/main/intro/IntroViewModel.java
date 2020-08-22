package com.fund.flio.ui.main.intro;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.fund.flio.data.DataManager;
import com.fund.flio.data.enums.AuthenticationState;
import com.fund.flio.di.provider.ResourceProvider;
import com.fund.flio.di.provider.SchedulerProvider;
import com.fund.flio.ui.base.BaseViewModel;
import com.orhanobut.logger.Logger;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;

public class IntroViewModel extends BaseViewModel {

    private MutableLiveData<Boolean> introDelay = new MutableLiveData<>();

    public MutableLiveData<Boolean> getIntroDelay() {
        return introDelay;
    }

    public IntroViewModel(DataManager dataManager, SchedulerProvider schedulerProvider, ResourceProvider resourceProvider) {
        super(dataManager, schedulerProvider, resourceProvider);
        getCompositeDisposable().add(Observable.timer(2, TimeUnit.SECONDS)
                .observeOn(schedulerProvider.ui())
                .subscribe(Void -> {
                    introDelay.setValue(true);
//                    getNavigator().checkAuth()
                }));
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        Logger.i("IntroViewModel onCleared");
    }
}
