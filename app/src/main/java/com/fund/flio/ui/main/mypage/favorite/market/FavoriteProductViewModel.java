package com.fund.flio.ui.main.mypage.favorite.market;

import android.app.Activity;
import android.view.View;

import androidx.lifecycle.MutableLiveData;
import androidx.navigation.Navigation;

import com.fund.flio.R;
import com.fund.flio.data.DataManager;
import com.fund.flio.data.model.Product;
import com.fund.flio.di.provider.ResourceProvider;
import com.fund.flio.di.provider.SchedulerProvider;
import com.fund.flio.ui.base.BaseViewModel;
import com.fund.flio.ui.main.message.MessageFragmentDirections;
import com.fund.flio.ui.main.mypage.favorite.FavoriteListFragment;
import com.orhanobut.logger.Logger;

import java.util.List;

public class FavoriteProductViewModel extends BaseViewModel {

    private MutableLiveData<List<Product>> products = new MutableLiveData<>();

    public MutableLiveData<List<Product>> getProducts() {
        return products;
    }

    public FavoriteProductViewModel(DataManager dataManager, SchedulerProvider schedulerProvider, ResourceProvider resourceProvider) {
        super(dataManager, schedulerProvider, resourceProvider);
    }


    public void selectFavorite() {
        getCompositeDisposable().add(getDataManager().selectFavorite(getDataManager().getUserId())
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(products -> {
                    if (products.isSuccessful()) {
                        this.products.setValue(products.body().getProducts());
                    }
                }));
    }

    public void onFavoriteToggle(View v, Product product) {
        Logger.d("onFavoriteToggle " + product);
        getCompositeDisposable().add(getDataManager().switchFavorite(getDataManager().getUserId(), product.getProductId())
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(Void -> {
                    v.setSelected(!v.isSelected());
                    this.products.getValue().remove(product);
                    this.products.setValue(this.products.getValue());
                }));
    }

    public void goMarket(View view) {
        Navigation.findNavController((Activity) view.getContext(), R.id.fragment_container).navigate(R.id.action_nav_favorite_list_to_nav_market);
    }

}
