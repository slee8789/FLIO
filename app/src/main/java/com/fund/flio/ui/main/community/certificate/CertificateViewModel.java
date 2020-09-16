package com.fund.flio.ui.main.community.certificate;


import com.fund.flio.data.DataManager;
import com.fund.flio.di.provider.ResourceProvider;
import com.fund.flio.di.provider.SchedulerProvider;
import com.fund.flio.ui.base.BaseViewModel;

public class CertificateViewModel extends BaseViewModel {

    public CertificateViewModel(DataManager dataManager, SchedulerProvider schedulerProvider, ResourceProvider resourceProvider) {
        super(dataManager, schedulerProvider, resourceProvider);
    }


}
