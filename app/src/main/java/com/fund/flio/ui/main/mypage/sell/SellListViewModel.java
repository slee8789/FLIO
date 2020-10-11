package com.fund.flio.ui.main.mypage.sell;

import androidx.lifecycle.MutableLiveData;

import com.annimon.stream.Stream;
import com.fund.flio.data.DataManager;
import com.fund.flio.data.enums.SaleYn;
import com.fund.flio.data.model.Product;
import com.fund.flio.di.provider.ResourceProvider;
import com.fund.flio.di.provider.SchedulerProvider;
import com.fund.flio.ui.base.BaseViewModel;

import java.util.List;

public class SellListViewModel extends BaseViewModel {

    private MutableLiveData<List<Product>> products = new MutableLiveData<>();

    public MutableLiveData<List<Product>> getProducts() {
        return products;
    }

    public SellListViewModel(DataManager dataManager, SchedulerProvider schedulerProvider, ResourceProvider resourceProvider) {
        super(dataManager, schedulerProvider, resourceProvider);
    }

    public void myPageProduct(SaleYn saleYn) {
        getCompositeDisposable().add(getDataManager().myPageProduct(getDataManager().getUserId())
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(products -> {
                    if (products.isSuccessful()) {
                        this.products.setValue(Stream.of(products.body().getProducts()).filter(product -> product.getSaleYn().equals(saleYn.name())).toList());
                    }
                }));
    }

}
