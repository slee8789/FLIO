package com.fund.flio.ui.main.market;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.core.view.ViewCompat;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.FragmentNavigator;

import com.fund.flio.R;
import com.fund.flio.data.model.Product;
import com.fund.flio.databinding.ItemProductBinding;
import com.fund.flio.databinding.ItemRecommendBinding;
import com.fund.flio.ui.main.MainActivity;

public class ItemProductViewModel {

    public ObservableField<String> imageUrl = new ObservableField<>();
    public ObservableBoolean isLike = new ObservableBoolean();
    public ObservableField<String> comment = new ObservableField<>();
    public ObservableField<String> price = new ObservableField<>();
    private ItemProductBinding itemProductBinding;
    private ItemRecommendBinding itemRecommendBinding;
    private Product mProduct;

    public ItemProductViewModel(View v, ItemProductBinding binding, Product product) {
        this.itemProductBinding = binding;
        mProduct = product;
        imageUrl.set(product.getImageUrl());
        isLike.set(product.isLike());
        comment.set(product.getComment());
        price.set(product.getPrice());
        binding.image.setTransitionName(v.getResources().getString(R.string.transition_product_image, product.getPid()));
        binding.flio.setTransitionName(v.getResources().getString(R.string.transition_product_flio, product.getPid()));
        binding.faith.setTransitionName(v.getResources().getString(R.string.transition_product_faith, product.getPid()));
    }

    public ItemProductViewModel(View v, ItemRecommendBinding binding, Product product) {
        this.itemRecommendBinding = binding;
        mProduct = product;
        imageUrl.set(product.getImageUrl());
        isLike.set(product.isLike());
        comment.set(product.getComment());
        price.set(product.getPrice());
        binding.image.setTransitionName(v.getResources().getString(R.string.transition_product_image, product.getPid()));
        binding.flio.setTransitionName(v.getResources().getString(R.string.transition_product_flio, product.getPid()));
        binding.faith.setTransitionName(v.getResources().getString(R.string.transition_product_faith, product.getPid()));
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

}
