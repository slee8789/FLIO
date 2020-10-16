package com.fund.flio.ui.main.market;

import android.os.Bundle;
import android.view.View;

import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.FragmentNavigator;

import com.fund.flio.R;
import com.fund.flio.data.enums.FaithYn;
import com.fund.flio.data.enums.FavoriteYn;
import com.fund.flio.data.enums.FlioYn;
import com.fund.flio.data.enums.ReviewType;
import com.fund.flio.data.model.Product;
import com.fund.flio.databinding.ItemProductBinding;
import com.fund.flio.databinding.ItemProductBuyBinding;
import com.fund.flio.databinding.ItemProductFavoriteBinding;
import com.fund.flio.databinding.ItemProductHomeBinding;
import com.fund.flio.databinding.ItemProductSelledBinding;
import com.fund.flio.databinding.ItemProductSellingBinding;
import com.fund.flio.ui.main.MainActivity;
import com.orhanobut.logger.Logger;

import java.text.DecimalFormat;
import java.util.Objects;

public class ItemProductViewModel {

    public ObservableField<String> title = new ObservableField<>();
    public ObservableField<String> imageUrl = new ObservableField<>();
    public ObservableField<String> date = new ObservableField<>();
    public ObservableBoolean flioYn = new ObservableBoolean(false);
    public ObservableBoolean faithYn = new ObservableBoolean(false);
    public ObservableBoolean favoriteYn = new ObservableBoolean(false);
    public ObservableField<String> comment = new ObservableField<>();
    public ObservableField<String> review = new ObservableField<>();
    public ObservableField<String> price = new ObservableField<>();
    private ItemProductBinding itemProductBinding;
    private ItemProductHomeBinding itemProductHomeBinding;
    private ItemProductSellingBinding itemProductSellingBinding;
    private ItemProductSelledBinding itemProductSelledBinding;
    private ItemProductBuyBinding itemProductBuyBinding;
    private ItemProductFavoriteBinding itemProductFavoriteBinding;
    private Product mProduct;

    public Product getProduct() {
        return mProduct;
    }

    private void setImage(Product product) {
        if (product.getImageUrl() != null) {
            String[] images = product.getImageUrl().split(",");
            imageUrl.set("http://flio.iptime.org:8080/image/" + product.getBaseUrl() + "/" + images[0]);
            Logger.d("setImage " + imageUrl.get());
        } else {
            imageUrl.set(null);
        }
    }

    private DecimalFormat formatter = new DecimalFormat("###,###");

    public ItemProductViewModel(View v, ItemProductBinding binding, Product product) {
        this.itemProductBinding = binding;
        mProduct = product;
        title.set(product.getTitle());
        setImage(product);
        favoriteYn.set(product.getFavoriteYn().equals(FavoriteYn.Y.name()));
        v.setSelected(favoriteYn.get());
        flioYn.set(product.getFlioYn() != null && product.getFlioYn().equals(FlioYn.Y.name()));
        faithYn.set(product.getFaithYn() != null && product.getFaithYn().equals(FaithYn.Y.name()));
        comment.set(product.getContent());
        price.set(formatter.format(product.getProductPrice()) + "원");
        binding.image.setTransitionName(v.getResources().getString(R.string.transition_product_image, product.getProductId()));
        binding.flio.setTransitionName(v.getResources().getString(R.string.transition_product_flio, product.getProductId()));
        binding.faith.setTransitionName(v.getResources().getString(R.string.transition_product_faith, product.getProductId()));
    }

    public ItemProductViewModel(View v, ItemProductHomeBinding binding, Product product) {
        this.itemProductHomeBinding = binding;
        mProduct = product;
        setImage(product);
        title.set(product.getTitle());
        favoriteYn.set(product.getFavoriteYn().equals(FavoriteYn.Y.name()));
        v.setSelected(favoriteYn.get());
        flioYn.set(product.getFlioYn() != null && product.getFlioYn().equals(FlioYn.Y.name()));
        faithYn.set(product.getFaithYn() != null && product.getFaithYn().equals(FaithYn.Y.name()));
        comment.set(product.getContent());
        price.set(formatter.format(product.getProductPrice()) + "원");
        binding.image.setTransitionName(v.getResources().getString(R.string.transition_product_image, product.getProductId()));
        binding.flio.setTransitionName(v.getResources().getString(R.string.transition_product_flio, product.getProductId()));
        binding.faith.setTransitionName(v.getResources().getString(R.string.transition_product_faith, product.getProductId()));
    }

    public ItemProductViewModel(View v, ItemProductSellingBinding binding, Product product) {
        Logger.d("ItemProductSellingBinding  " + product);
        this.itemProductSellingBinding = binding;
        mProduct = product;
        setImage(product);
        title.set(product.getTitle());
        date.set(product.getRegDate());
        flioYn.set(product.getFlioYn() != null && product.getFlioYn().equals(FlioYn.Y.name()));
        faithYn.set(product.getFaithYn() != null && product.getFaithYn().equals(FaithYn.Y.name()));
        comment.set(product.getContent());
        price.set(formatter.format(product.getProductPrice()) + "원");
    }

    public ItemProductViewModel(View v, ItemProductSelledBinding binding, Product product) {
        Logger.d("ItemProductSelledBinding  " + product);
        this.itemProductSelledBinding = binding;
        mProduct = product;
        setImage(product);
        title.set(product.getTitle());
        date.set(product.getRegDate());
        flioYn.set(product.getFlioYn() != null && product.getFlioYn().equals(FlioYn.Y.name()));
        faithYn.set(product.getFaithYn() != null && product.getFaithYn().equals(FaithYn.Y.name()));
        comment.set(product.getContent());
        price.set(formatter.format(product.getProductPrice()) + "원");
    }

    public ItemProductViewModel(View v, ItemProductBuyBinding binding, Product product) {
        Logger.d("itemProductBuyBinding  " + product);
        this.itemProductBuyBinding = binding;
        mProduct = product;
        setImage(product);
        title.set(product.getTitle());
        date.set(product.getRegDate());
        review.set(product.getProductReview() == null ? v.getContext().getResources().getString(R.string.product_buy_review_write) : v.getContext().getResources().getString(R.string.product_sell_view_review));
        flioYn.set(product.getFlioYn() != null && product.getFlioYn().equals(FlioYn.Y.name()));
        faithYn.set(product.getFaithYn() != null && product.getFaithYn().equals(FaithYn.Y.name()));
        comment.set(product.getContent());
        price.set(formatter.format(product.getProductPrice()) + "원");
    }

    public ItemProductViewModel(View v, ItemProductFavoriteBinding binding, Product product) {
        Logger.d("ItemProductFavoriteBinding  " + product);
        this.itemProductFavoriteBinding = binding;
        mProduct = product;
        setImage(product);
        if (product.getFavoriteYn() != null) {
            favoriteYn.set(product.getFavoriteYn().equals(FavoriteYn.Y.name()));
        }
        v.setSelected(favoriteYn.get());
        title.set(product.getTitle());
        date.set(product.getRegDate());
        flioYn.set(product.getFlioYn() != null && product.getFlioYn().equals(FlioYn.Y.name()));
        faithYn.set(product.getFaithYn() != null && product.getFaithYn().equals(FaithYn.Y.name()));
        comment.set(product.getContent());
        price.set(formatter.format(product.getProductPrice()) + "원");
    }

    public void onItemClick(View v) {
        FragmentNavigator.Extras extras = null;
        if (itemProductBinding == null) {
            extras = new FragmentNavigator.Extras.Builder()
                    .addSharedElement(itemProductHomeBinding.image, itemProductHomeBinding.image.getTransitionName())
                    .addSharedElement(itemProductHomeBinding.flio, itemProductHomeBinding.flio.getTransitionName())
                    .addSharedElement(itemProductHomeBinding.faith, itemProductHomeBinding.faith.getTransitionName())
                    .build();
        } else {
            extras = new FragmentNavigator.Extras.Builder()
                    .addSharedElement(itemProductBinding.image, itemProductBinding.image.getTransitionName())
                    .addSharedElement(itemProductBinding.flio, itemProductBinding.flio.getTransitionName())
                    .addSharedElement(itemProductBinding.faith, itemProductBinding.faith.getTransitionName())
                    .build();
        }
        Navigation.findNavController((MainActivity) v.getContext(), R.id.fragment_container).navigate(MarketFragmentDirections.actionGlobalToNavMarketProduct(mProduct), extras);
    }

    public void showBuyerGuide(View v) {
        Bundle bundle = new Bundle();
        bundle.putInt("productId", mProduct.getProductId());
        bundle.putString("productImage", imageUrl.get());
        bundle.putString("title", mProduct.getTitle());
        Navigation.findNavController((MainActivity) v.getContext(), R.id.fragment_container).navigate(R.id.action_global_to_buyer_guide, bundle);
    }

    public void showReview(View v) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("reviewType", mProduct.getProductReview() == null ? ReviewType.NO_REVIEW : ReviewType.valueOf(mProduct.getProductReview()));
        Navigation.findNavController((MainActivity) v.getContext(), R.id.fragment_container).navigate(R.id.action_nav_sell_list_to_nav_review, bundle);
    }

    public void showReviewWrite(View v) {

        if (mProduct.getProductReview() == null) {
            Bundle bundle = new Bundle();
            bundle.putInt("productId", mProduct.getProductId());
            bundle.putString("userName", mProduct.getUserName());
            Navigation.findNavController((MainActivity) v.getContext(), R.id.fragment_container).navigate(R.id.action_nav_buy_list_to_nav_review_write, bundle);
        } else {
            Bundle bundle = new Bundle();
            bundle.putSerializable("reviewType", mProduct.getProductReview() == null ? ReviewType.NO_REVIEW : ReviewType.valueOf(mProduct.getProductReview()));
            Navigation.findNavController((MainActivity) v.getContext(), R.id.fragment_container).navigate(R.id.action_nav_buy_list_to_nav_review, bundle);
        }

    }

}
