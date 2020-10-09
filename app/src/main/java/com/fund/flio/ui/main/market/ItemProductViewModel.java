package com.fund.flio.ui.main.market;

import android.view.View;

import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.FragmentNavigator;

import com.annimon.stream.Stream;
import com.fund.flio.R;
import com.fund.flio.data.model.Product;
import com.fund.flio.databinding.ItemProductBinding;
import com.fund.flio.databinding.ItemProductHomeBinding;
import com.fund.flio.databinding.ItemProductSelledBinding;
import com.fund.flio.databinding.ItemProductSellingBinding;
import com.fund.flio.ui.main.MainActivity;
import com.orhanobut.logger.Logger;

public class ItemProductViewModel {

    public ObservableField<String> imageUrl = new ObservableField<>();
    public ObservableBoolean isLike = new ObservableBoolean();
    public ObservableField<String> comment = new ObservableField<>();
    public ObservableField<String> price = new ObservableField<>();
    private ItemProductBinding itemProductBinding;
    private ItemProductHomeBinding itemRecommendBinding;
    private ItemProductSellingBinding itemProductSellingBinding;
    private ItemProductSelledBinding itemProductSelledBinding;
    private Product mProduct;

    public Product getProduct() {
        return mProduct;
    }

    private void setImage(Product product) {
        if (product.getImageUrl() != null) {
            String[] images = product.getImageUrl().split(",");
            imageUrl.set("http://flio.iptime.org:8080/image/" + product.getBaseUrl() + "/" + images[0]);
        } else {
            imageUrl.set(null);
        }
    }

    public ItemProductViewModel(View v, ItemProductBinding binding, Product product) {
        this.itemProductBinding = binding;
        mProduct = product;
        setImage(product);
//        isLike.set(product.getFavoriteYn());
        comment.set(product.getContent());
        price.set(product.getProductPrice() + "원");
        binding.image.setTransitionName(v.getResources().getString(R.string.transition_product_image, product.getProductId()));
        binding.flio.setTransitionName(v.getResources().getString(R.string.transition_product_flio, product.getProductId()));
        binding.faith.setTransitionName(v.getResources().getString(R.string.transition_product_faith, product.getProductId()));
    }

    public ItemProductViewModel(View v, ItemProductHomeBinding binding, Product product) {
        this.itemRecommendBinding = binding;
        Logger.d("ItemProductViewModel  " + product);
        mProduct = product;
        setImage(product);

//        isLike.set(product.isLike());
        comment.set(product.getContent());
        price.set(product.getProductPrice() + "원");
        binding.image.setTransitionName(v.getResources().getString(R.string.transition_product_image, product.getProductId()));
        binding.flio.setTransitionName(v.getResources().getString(R.string.transition_product_flio, product.getProductId()));
        binding.faith.setTransitionName(v.getResources().getString(R.string.transition_product_faith, product.getProductId()));
    }

    public ItemProductViewModel(View v, ItemProductSellingBinding binding, Product product) {
        this.itemProductSellingBinding = binding;
        mProduct = product;
        setImage(product);
//        isLike.set(product.isLike());
        comment.set(product.getContent());
        price.set(product.getProductPrice() + "원");
    }

    public ItemProductViewModel(View v, ItemProductSelledBinding binding, Product product) {
        this.itemProductSelledBinding = binding;
        mProduct = product;
        imageUrl.set(product.getImageUrl());
//        isLike.set(product.isLike());
        comment.set(product.getContent());
        price.set(product.getProductPrice() + "원");
    }

    public void onItemClick(View v) {
        FragmentNavigator.Extras extras = null;
        if (itemProductBinding == null) {
            extras = new FragmentNavigator.Extras.Builder()
                    .addSharedElement(itemRecommendBinding.image, itemRecommendBinding.image.getTransitionName())
                    .addSharedElement(itemRecommendBinding.flio, itemRecommendBinding.flio.getTransitionName())
                    .addSharedElement(itemRecommendBinding.faith, itemRecommendBinding.faith.getTransitionName())
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
        Navigation.findNavController((MainActivity) v.getContext(), R.id.fragment_container).navigate(R.id.action_global_to_buyer_guide);
    }


}
