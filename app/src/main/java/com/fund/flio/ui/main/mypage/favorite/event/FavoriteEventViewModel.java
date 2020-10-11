package com.fund.flio.ui.main.mypage.favorite.event;

import androidx.lifecycle.MutableLiveData;

import com.fund.flio.data.DataManager;
import com.fund.flio.data.model.Product;
import com.fund.flio.di.provider.ResourceProvider;
import com.fund.flio.di.provider.SchedulerProvider;
import com.fund.flio.ui.base.BaseViewModel;

import java.util.List;

public class FavoriteEventViewModel extends BaseViewModel {

    private MutableLiveData<List<Product>> products = new MutableLiveData<>();

    public MutableLiveData<List<Product>> getProducts() {
        return products;
    }

    public FavoriteEventViewModel(DataManager dataManager, SchedulerProvider schedulerProvider, ResourceProvider resourceProvider) {
        super(dataManager, schedulerProvider, resourceProvider);
    }


    public void targetProduct() {
        getCompositeDisposable().add(getDataManager().targetProduct(getDataManager().getUserId())
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(products -> {
                    if (products.isSuccessful()) {
                        this.products.setValue(products.body().getProducts());
                    }
                }));
    }

}
