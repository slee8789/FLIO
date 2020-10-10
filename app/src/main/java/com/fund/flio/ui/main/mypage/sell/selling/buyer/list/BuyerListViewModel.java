package com.fund.flio.ui.main.mypage.sell.selling.buyer.list;

import android.view.View;

import androidx.lifecycle.MutableLiveData;

import com.fund.flio.data.DataManager;
import com.fund.flio.data.model.Buyer;
import com.fund.flio.di.provider.ResourceProvider;
import com.fund.flio.di.provider.SchedulerProvider;
import com.fund.flio.ui.base.BaseViewModel;

import java.util.List;

public class BuyerListViewModel extends BaseViewModel {

    private MutableLiveData<List<Buyer>> mBuyers = new MutableLiveData<>();

    public MutableLiveData<List<Buyer>> getBuyers() {
        return mBuyers;
    }

    public BuyerListViewModel(DataManager dataManager, SchedulerProvider schedulerProvider, ResourceProvider resourceProvider) {
        super(dataManager, schedulerProvider, resourceProvider);
    }

    public void targetList(int productId) {
        getCompositeDisposable().add(getDataManager().targetUserList(productId, getDataManager().getUserId())
                .observeOn(getSchedulerProvider().ui())
                .subscribeOn(getSchedulerProvider().io())
                .subscribe(buyers -> {
                    if (buyers.isSuccessful()) {
                        mBuyers.setValue(buyers.body().getBuyers());
                    }
                }));
    }

    public void onItemClick(View v, Buyer buyer) {
        getCompositeDisposable().add(getDataManager().targetUserUpdate(19, getDataManager().getUserId(), buyer.getTargetUid())
                .observeOn(getSchedulerProvider().ui())
                .subscribeOn(getSchedulerProvider().io())
                .subscribe());
    }


}
