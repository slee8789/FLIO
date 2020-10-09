package com.fund.flio.ui.main.market.product;


import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.view.View;

import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import androidx.lifecycle.MutableLiveData;
import androidx.navigation.Navigation;

import com.annimon.stream.Stream;
import com.fund.flio.R;
import com.fund.flio.data.DataManager;
import com.fund.flio.data.model.ChatRoom;
import com.fund.flio.data.model.Product;
import com.fund.flio.data.model.body.InsertMyChatBody;
import com.fund.flio.di.provider.ResourceProvider;
import com.fund.flio.di.provider.SchedulerProvider;
import com.fund.flio.ui.base.BaseViewModel;
import com.fund.flio.ui.main.MainActivity;
import com.fund.flio.ui.main.message.MessageFragmentDirections;
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
    public ObservableBoolean isSeller = new ObservableBoolean();
    public ObservableBoolean linkVisible = new ObservableBoolean();

    private MutableLiveData<List<Product>> mProducts = new MutableLiveData<>();
    private MutableLiveData<List<String>> mProductImages = new MutableLiveData<>();
    private Product product;

    public MutableLiveData<List<Product>> getProducts() {
        return mProducts;
    }

    public MutableLiveData<List<String>> getProductImages() {
        return mProductImages;
    }

    public ProductViewModel(DataManager dataManager, SchedulerProvider schedulerProvider, ResourceProvider resourceProvider) {
        super(dataManager, schedulerProvider, resourceProvider);
    }

    public void detailProduct(String pid) {
        getCompositeDisposable().add(getDataManager().detailProduct(pid)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(product -> {
                    if (product.isSuccessful()) {
                        this.product = product.body().getProducts().get(0);
                        Logger.d("detailProduct " + this.product);
                        title.set(this.product.getTitle());
                        content.set(this.product.getContent());
                        price.set(this.product.getPurchasePrice());
                        productName.set(this.product.getProductName());

                        setImage(this.product);
                        purpose.set(this.product.getPurpose());
                        sellerImage.set(this.product.getUserImageUrl());
                        sellerName.set(this.product.getUserName());
                        rating.set("3.5");
                        setTag(product.body().getProducts().get(0).getTag().split(","));
                        isSeller.set(getDataManager().getUserId().equals(this.product.getUid()));
                        linkVisible.set(this.product.getProductRelatedUrl() != null);
                        Logger.d("detailProduct " + linkVisible.get() + ", " + (this.product.getProductRelatedUrl() != null) + ", " + this.product.getProductRelatedUrl());
                    }


                }));
    }

    public void purposeProduct(String purpose) {
        getCompositeDisposable().add(getDataManager().purposeProduct(purpose)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(products -> {
                    if (products.isSuccessful()) {
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
        String[] images = product.getImageUrl().split(",");
        mProductImages.setValue(Stream.of(images).map(image -> "http://flio.iptime.org:8080/image/" + product.getBaseUrl() + "/" + image).toList());
    }

    public void goChat(View view) {
        getCompositeDisposable().add(getDataManager().insertMyChat(new InsertMyChatBody(product.getUid(), getDataManager().getUserId(), product.getProductId()))
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(chatRoom -> {
                    if (chatRoom.isSuccessful()) {
                        Logger.d("goChat " + chatRoom.body());
                        ChatRoom mChatRoom = new ChatRoom();
                        mChatRoom.setChatSeq(chatRoom.body().getChatRoom().getChatSeq());
                        mChatRoom.setChatSourceUid(product.getUid());
                        mChatRoom.setChatSourceName(product.getUserName());
                        mChatRoom.setChatSourceImageUrl(product.getUserImageUrl());
                        mChatRoom.setChatSourceMessageToken(chatRoom.body().getChatRoom().getChatSourceMessageToken());
                        mChatRoom.setTitle(product.getTitle());
                        mChatRoom.setProductPrice(product.getProductPrice());
                        mChatRoom.setProductId(product.getProductId());
                        Navigation.findNavController((Activity) view.getContext(), R.id.fragment_container).navigate(ProductFragmentDirections.actionNavMarketProductToNavChatDetail(mChatRoom));
                    }
                }));

    }

    public void showDetail(View v) {
        Navigation.findNavController((MainActivity) v.getContext(), R.id.fragment_container).navigate(ProductFragmentDirections.actionNavMarketProductToNavMarketProductDetail(product));
    }

    public void showLink(View v) {
        Intent i = new Intent(Intent.ACTION_VIEW);
        String targetUrl = product.getProductRelatedUrl();
        if (!targetUrl.startsWith("http://") && !targetUrl.startsWith("https://"))
            targetUrl = "http://" + targetUrl;
        i.setData(Uri.parse(targetUrl));
        (v.getContext()).startActivity(i);
    }

    public void showProfile(View v) {
        Navigation.findNavController((MainActivity) v.getContext(), R.id.fragment_container).navigate(ProductFragmentDirections.actionNavMarketProductToNavProfile());
    }

}
