package com.fund.flio.ui.main.market;


import androidx.lifecycle.MutableLiveData;

import com.fund.flio.data.DataManager;
import com.fund.flio.data.enums.ProductCategory;
import com.fund.flio.data.enums.SpeakerType;
import com.fund.flio.data.model.Product;
import com.fund.flio.di.provider.ResourceProvider;
import com.fund.flio.di.provider.SchedulerProvider;
import com.fund.flio.ui.base.BaseViewModel;

import java.util.List;

public class MarketViewModel extends BaseViewModel {

    private MutableLiveData<List<Product>> mProducts = new MutableLiveData<>();

    public MutableLiveData<List<Product>> getProducts() {
        return mProducts;
    }

    public MarketViewModel(DataManager dataManager, SchedulerProvider schedulerProvider, ResourceProvider resourceProvider) {
        super(dataManager, schedulerProvider, resourceProvider);
//        recommandProducts(ProductCategory.SPEAKER.name(), SpeakerType.PRO.name());
    }

    public void mainProduct() {
        getCompositeDisposable().add(getDataManager().mainProduct()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(product -> {
                    if (product.isSuccessful()) {
                        mProducts.setValue(product.body().getProducts());
                    }

                }));
    }

    public void recommandProducts(String productCategory, String secondCategory) {
        getCompositeDisposable().add(getDataManager().recommandProduct(productCategory, secondCategory)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(products -> {
                    if(products.isSuccessful()) {
                        mProducts.setValue(products.body().getProducts());
                    }

                }));
    }



}
