package com.fund.flio.ui.main.mypage.buy;

import androidx.lifecycle.MutableLiveData;

import com.annimon.stream.Stream;
import com.fund.flio.data.DataManager;
import com.fund.flio.data.bus.ProductBus;
import com.fund.flio.data.enums.SaleYn;
import com.fund.flio.data.model.Product;
import com.fund.flio.di.provider.ResourceProvider;
import com.fund.flio.di.provider.SchedulerProvider;
import com.fund.flio.ui.base.BaseViewModel;

import java.util.List;

public class BuyListViewModel extends BaseViewModel {

    private MutableLiveData<List<Product>> products = new MutableLiveData<>();

    public MutableLiveData<List<Product>> getProducts() {
        return products;
    }

    public BuyListViewModel(DataManager dataManager, SchedulerProvider schedulerProvider, ResourceProvider resourceProvider) {
        super(dataManager, schedulerProvider, resourceProvider);
        subscribeEvent();
    }

    private void subscribeEvent() {
        getCompositeDisposable().add(ProductBus.getInstance().getReviewWriteDismiss()
                .subscribe(dismiss -> targetProduct()));
    }


    public void targetProduct() {
        getCompositeDisposable().add(getDataManager().targetProduct(getDataManager().getUserId(), getDataManager().getUserId())
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(products -> {
                    if (products.isSuccessful()) {
                        this.products.setValue(products.body().getProducts());
                    }
                }));
    }

}
