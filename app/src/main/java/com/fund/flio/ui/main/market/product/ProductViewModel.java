package com.fund.flio.ui.main.market.product;


import android.view.View;

import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import androidx.lifecycle.MutableLiveData;
import androidx.navigation.Navigation;

import com.fund.flio.R;
import com.fund.flio.data.DataManager;
import com.fund.flio.data.model.Product;
import com.fund.flio.di.provider.ResourceProvider;
import com.fund.flio.di.provider.SchedulerProvider;
import com.fund.flio.ui.base.BaseViewModel;
import com.fund.flio.ui.main.MainActivity;
import com.orhanobut.logger.Logger;

import java.util.List;

public class ProductViewModel extends BaseViewModel {

    public ObservableField<String> title = new ObservableField<>();
    public ObservableField<String> content = new ObservableField<>();
    public ObservableField<String> price = new ObservableField<>();
    public ObservableField<String> productName = new ObservableField<>();
    public ObservableField<String> image = new ObservableField<>();
    public ObservableField<String> tag1 = new ObservableField<>();
    public ObservableField<String> tag2 = new ObservableField<>();
    public ObservableField<String> tag3 = new ObservableField<>();
    public ObservableField<String> purpose = new ObservableField<>();
    public ObservableBoolean tag_visible_1 = new ObservableBoolean(false);
    public ObservableBoolean tag_visible_2 = new ObservableBoolean(false);
    public ObservableBoolean tag_visible_3 = new ObservableBoolean(false);
    public ObservableField<String> sellerImage = new ObservableField<>();
    public ObservableField<String> sellerName = new ObservableField<>();
    public ObservableField<String> rating = new ObservableField<>("0");
    public ObservableField<String> newsUrl = new ObservableField<>("http://flio.iptime.org:8080/image/dummy/event/event_1.png");

    private MutableLiveData<List<Product>> mProducts = new MutableLiveData<>();

    public MutableLiveData<List<Product>> getProducts() {
        return mProducts;
    }

    public ProductViewModel(DataManager dataManager, SchedulerProvider schedulerProvider, ResourceProvider resourceProvider) {
        super(dataManager, schedulerProvider, resourceProvider);
    }

    public void detailProduct(String pid) {
        getCompositeDisposable().add(getDataManager().detailProduct(pid)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(product -> {
                    title.set(product.body().getProducts().get(0).getTitle());
                    content.set(product.body().getProducts().get(0).getContent());
                    price.set(product.body().getProducts().get(0).getPurchasePrice());
                    productName.set(product.body().getProducts().get(0).getProductName());

                    setImage(product.body().getProducts().get(0));
                    purpose.set(product.body().getProducts().get(0).getPurpose());
                    sellerImage.set(image.get());
                    sellerName.set("홍길동");
                    rating.set("3.5");
                    setTag(product.body().getProducts().get(0).getTag().split(","));

                }));
    }

    public void purposeProduct(String purpose) {
        getCompositeDisposable().add(getDataManager().purposeProduct(purpose)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(products -> {
                    if(products.isSuccessful()) {
                        mProducts.setValue(products.body().getProducts());
                    }
                }));
    }

    private void setTag(String[] tags) {
        switch (tags.length) {
            case 1:
                tag1.set(tags[0]);
                tag_visible_1.set(true);
                break;
            case 2:
                tag1.set(tags[0]);
                tag2.set(tags[1]);
                tag_visible_1.set(true);
                tag_visible_2.set(true);
                break;
            case 3:
                tag1.set(tags[0]);
                tag2.set(tags[1]);
                tag3.set(tags[2]);
                tag_visible_1.set(true);
                tag_visible_2.set(true);
                tag_visible_3.set(true);
                break;
            default:
        }
    }

    private void setImage(Product product) {
        image.set("http://flio.iptime.org:8080/image/" + product.getBaseUrl() + "/" + product.getImageUrl());
    }

}
