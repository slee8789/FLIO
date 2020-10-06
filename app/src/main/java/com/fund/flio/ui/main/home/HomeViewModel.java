package com.fund.flio.ui.main.home;


import android.os.Bundle;
import android.view.View;

import androidx.databinding.ObservableField;
import androidx.lifecycle.MutableLiveData;
import androidx.navigation.Navigation;

import com.fund.flio.R;
import com.fund.flio.data.DataManager;
import com.fund.flio.data.enums.ProductCategory;
import com.fund.flio.data.model.ChatRoom;
import com.fund.flio.data.model.Product;
import com.fund.flio.di.provider.ResourceProvider;
import com.fund.flio.di.provider.SchedulerProvider;
import com.fund.flio.ui.base.BaseViewModel;
import com.fund.flio.ui.main.MainActivity;
import com.orhanobut.logger.Logger;

import java.util.List;

public class HomeViewModel extends BaseViewModel {

    public ObservableField<String> newsUrl = new ObservableField<>();
    private Bundle bundle = new Bundle();

    private MutableLiveData<List<Product>> mProducts = new MutableLiveData<>();

    public MutableLiveData<List<Product>> getProducts() {
        return mProducts;
    }

    public HomeViewModel(DataManager dataManager, SchedulerProvider schedulerProvider, ResourceProvider resourceProvider) {
        super(dataManager, schedulerProvider, resourceProvider);
        newsUrl.set("http://flio.iptime.org:8080/image/dummy/event/event_1.png");
        Logger.d("HomeViewModel creator");
        mainProduct();
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

    public void onCategoryClick(View v, ProductCategory productCategory) {

        bundle.putSerializable("productCategory", productCategory);
        Navigation.findNavController((MainActivity) v.getContext(), R.id.fragment_container).navigate(R.id.action_nav_home_to_nav_market, bundle);


    }

}
