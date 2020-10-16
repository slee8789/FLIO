package com.fund.flio.ui.main.market.product;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;

import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import androidx.lifecycle.MutableLiveData;
import androidx.navigation.Navigation;

import com.annimon.stream.Collectors;
import com.annimon.stream.Stream;
import com.fund.flio.R;
import com.fund.flio.data.DataManager;
import com.fund.flio.data.enums.FaithYn;
import com.fund.flio.data.enums.FavoriteYn;
import com.fund.flio.data.enums.FlioYn;
import com.fund.flio.data.enums.Purpose;
import com.fund.flio.data.model.ChatRoom;
import com.fund.flio.data.model.Product;
import com.fund.flio.data.model.body.InsertMyChatBody;
import com.fund.flio.di.provider.ResourceProvider;
import com.fund.flio.di.provider.SchedulerProvider;
import com.fund.flio.ui.base.BaseViewModel;
import com.fund.flio.ui.main.MainActivity;
import com.orhanobut.logger.Logger;

import java.text.DecimalFormat;
import java.util.List;

public class ProductViewModel extends BaseViewModel {

    public ObservableField<String> title = new ObservableField<>();
    public ObservableField<String> content = new ObservableField<>();
    public ObservableField<String> price = new ObservableField<>();
    public ObservableField<String> modelNo = new ObservableField<>();
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
    public ObservableBoolean isSeller = new ObservableBoolean(false);
    public ObservableBoolean linkVisible = new ObservableBoolean();
    public ObservableBoolean detailVisible = new ObservableBoolean();
    public ObservableBoolean flioYn = new ObservableBoolean();
    public ObservableBoolean faithYn = new ObservableBoolean();
    public ObservableBoolean favoriteYn = new ObservableBoolean();

    private MutableLiveData<List<Product>> mProducts = new MutableLiveData<>();
    private MutableLiveData<List<String>> mProductImages = new MutableLiveData<>();
    private Product product;

    public MutableLiveData<List<Product>> getProducts() {
        return mProducts;
    }

    public MutableLiveData<List<String>> getProductImages() {
        return mProductImages;
    }

    public Product getProduct() {
        return product;
    }

    private DecimalFormat formatter = new DecimalFormat("###,###");

    private ProductFragment productFragment;

    public void setProductFragment(ProductFragment productFragment) {
        this.productFragment = productFragment;
    }

    public ProductViewModel(DataManager dataManager, SchedulerProvider schedulerProvider, ResourceProvider resourceProvider) {
        super(dataManager, schedulerProvider, resourceProvider);
    }


    public void detailProduct(String pid) {
        getCompositeDisposable().add(getDataManager().detailProduct(pid, getDataManager().getUserId())
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(product -> {
                    if (product.isSuccessful()) {
                        this.product = product.body().getProduct();
                        Logger.d("detailProduct " + this.product);
                        title.set(this.product.getTitle());
                        content.set(this.product.getContent());
                        price.set(formatter.format(this.product.getProductPrice()) + "원");
                        modelNo.set(this.product.getModelNo());
                        setImage(this.product);
                        sellerImage.set(this.product.getUserImageUrl());
                        sellerName.set(this.product.getUserName());
                        rating.set("5");
                        if (this.product.getPurpose().length() != 0) {
                            purpose.set(TextUtils.join(",", Stream.of(this.product.getPurpose().split(",")).map(purposeStr -> Purpose.valueOf(purposeStr).getType()).collect(Collectors.toList())));
                        }
                        if (product.body().getProduct().getTag() != null) {
                            setTag(product.body().getProduct().getTag().split(","));
                        }
                        isSeller.set(getDataManager().getUserId().equals(this.product.getUid()));
                        linkVisible.set(this.product.getProductRelatedUrl() != null);
                        detailVisible.set(this.product.getUseDate() != null);
                        favoriteYn.set(this.product.getFavoriteYn().equals(FavoriteYn.Y.name()));
                        if (this.product.getFlioYn() != null) {
                            flioYn.set(this.product.getFlioYn().equals(FlioYn.Y.name()));
                        }

                        if (this.product.getFaithYn() != null) {
                            faithYn.set(this.product.getFaithYn().equals(FaithYn.Y.name()));
                        }

                        Logger.d("detailProduct " + linkVisible.get() + ", " + (this.product.getProductRelatedUrl() != null) + ", " + this.product.getProductRelatedUrl());
                    }


                }));
    }

    public void purposeProduct(int productId, String purpose) {
        getCompositeDisposable().add(getDataManager().purposeProduct(productId, purpose, getDataManager().getUserId())
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
                        Logger.d("goChat 2 " + product);
                        ChatRoom mChatRoom = new ChatRoom();
                        mChatRoom.setChatSeq(chatRoom.body().getChatRoom().getChatSeq());
                        mChatRoom.setChatSourceUid(product.getUid());
                        mChatRoom.setChatSourceName(product.getUserName());
                        mChatRoom.setChatSourceImageUrl(product.getUserImageUrl());
                        mChatRoom.setChatSourceMessageToken(chatRoom.body().getChatRoom().getChatSourceMessageToken());
                        mChatRoom.setTitle(product.getTitle());
                        mChatRoom.setProductPrice(product.getProductPrice());
                        mChatRoom.setProductId(product.getProductId());
                        mChatRoom.setProductBaseUrl(product.getBaseUrl());
                        mChatRoom.setProductImageUrl(product.getImageUrl());
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

    public void onFavoriteToggle(View v, int productId) {
        Logger.d("onFavoriteToggle");
        getCompositeDisposable().add(getDataManager().switchFavorite(getDataManager().getUserId(), productId)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(Void -> {
                    v.setSelected(!v.isSelected());
                }));
    }

    public void fullScreen(View v, String imageUrl) {
        Logger.d("fullScreen");
        Navigation.findNavController((MainActivity) productFragment.getContext(), R.id.fragment_container).navigate(ProductFragmentDirections.actionNavMarketProductToNavMarketProductImage(imageUrl));
//        Intent i = new Intent(Intent.ACTION_VIEW);
//        i.setData(Uri.parse(imageUrl));
//        (v.getContext()).startActivity(i);
    }

}
