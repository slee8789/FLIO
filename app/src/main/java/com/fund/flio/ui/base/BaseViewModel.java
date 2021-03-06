package com.fund.flio.ui.base;

import androidx.databinding.ObservableBoolean;
import androidx.lifecycle.ViewModel;

import com.fund.flio.data.DataManager;
import com.fund.flio.di.provider.ResourceProvider;
import com.fund.flio.di.provider.SchedulerProvider;

import io.reactivex.rxjava3.disposables.CompositeDisposable;


public abstract class BaseViewModel extends ViewModel {

    private final DataManager mDataManager;

    private final ObservableBoolean isLoading = new ObservableBoolean(false);

    private final SchedulerProvider mSchedulerProvider;

    private final ResourceProvider mResourceProvider;

    private CompositeDisposable mCompositeDisposable;
    private io.reactivex.disposables.CompositeDisposable mCompositeDisposable2;

    public BaseViewModel(DataManager dataManager, SchedulerProvider schedulerProvider, ResourceProvider resourceProvider) {
        this.mDataManager = dataManager;
        this.mSchedulerProvider = schedulerProvider;
        this.mResourceProvider = resourceProvider;
        this.mCompositeDisposable = new CompositeDisposable();
        this.mCompositeDisposable2 = new io.reactivex.disposables.CompositeDisposable();
    }

    @Override
    protected void onCleared() {
        mCompositeDisposable.clear();
        mCompositeDisposable2.clear();
        super.onCleared();
    }

    public CompositeDisposable getCompositeDisposable() {
        return mCompositeDisposable;
    }

    public io.reactivex.disposables.CompositeDisposable getCompositeDisposable2() {
        return mCompositeDisposable2;
    }

    public DataManager getDataManager() {
        return mDataManager;
    }

    public ObservableBoolean getIsLoading() {
        return isLoading;
    }

    public void setIsLoading(boolean isLoading) {
        this.isLoading.set(isLoading);
    }

    public SchedulerProvider getSchedulerProvider() {
        return mSchedulerProvider;
    }

    public ResourceProvider getResourceProvider() {
        return mResourceProvider;
    }
}
