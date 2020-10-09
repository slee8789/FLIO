package com.fund.flio.ui.main.mypage.sell.selling.buyer.list;

import com.fund.flio.data.DataManager;
import com.fund.flio.data.model.Product;
import com.fund.flio.di.provider.ResourceProvider;
import com.fund.flio.di.provider.SchedulerProvider;
import com.fund.flio.ui.base.BaseViewModel;

public class BuyerListViewModel extends BaseViewModel {

    public BuyerListViewModel(DataManager dataManager, SchedulerProvider schedulerProvider, ResourceProvider resourceProvider) {
        super(dataManager, schedulerProvider, resourceProvider);
    }

    public void targetList(int productId) {
        getCompositeDisposable().add(getDataManager().targetUserList(productId, getDataManager().getUserId())
                .observeOn(getSchedulerProvider().ui())
                .subscribeOn(getSchedulerProvider().io())
                .subscribe());
    }


}
