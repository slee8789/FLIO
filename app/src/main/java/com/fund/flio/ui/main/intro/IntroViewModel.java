package com.fund.flio.ui.main.intro;


import androidx.databinding.ObservableField;
import androidx.lifecycle.MutableLiveData;

import com.fund.flio.data.DataManager;
import com.fund.flio.di.provider.ResourceProvider;
import com.fund.flio.di.provider.SchedulerProvider;
import com.fund.flio.ui.base.BaseViewModel;
import com.orhanobut.logger.Logger;

import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.core.Observable;


public class IntroViewModel extends BaseViewModel {

    private MutableLiveData<Boolean> introDelay = new MutableLiveData<>();

    public MutableLiveData<Boolean> getIntroDelay() {
        return introDelay;
    }

    public ObservableField<Integer> progress = new ObservableField<>();
    public ObservableField<Integer> progressEnd = new ObservableField<>(200);

    public IntroViewModel(DataManager dataManager, SchedulerProvider schedulerProvider, ResourceProvider resourceProvider) {
        super(dataManager, schedulerProvider, resourceProvider);

    }

    public void progress() {
        Logger.d("progress start");
        getCompositeDisposable().add(Observable.interval(10, TimeUnit.MILLISECONDS)
                .takeUntil(progressIndex -> progressIndex == progressEnd.get().longValue())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(progressIndex -> {
                    progress.set(progressIndex.intValue());
                    if (progressIndex == 200) {
                        introDelay.setValue(true);
                    }
                }, onError -> {

                }, () -> {
                    Logger.i("progress onCompleted");
                }));
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        Logger.i("IntroViewModel onCleared");
    }
}
