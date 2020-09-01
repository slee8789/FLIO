package com.fund.flio.ui.main.market;

import android.view.View;

import androidx.core.view.ViewCompat;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.FragmentNavigator;

import com.fund.flio.R;
import com.fund.flio.data.model.Banner;
import com.fund.flio.data.model.Product;
import com.fund.flio.ui.main.MainActivity;
import com.fund.flio.ui.main.product.ProductFragmentDirections;

public class ItemProductViewModel {

    public ObservableField<String> imageUrl = new ObservableField<>();
    public ObservableBoolean isLike = new ObservableBoolean();
    public ObservableField<String> comment = new ObservableField<>();
    public ObservableField<String> price = new ObservableField<>();
    private Product mProduct;

    public ItemProductViewModel(Product product) {
        mProduct = product;
        imageUrl.set(product.getImageUrl());
        isLike.set(product.isLike());
        comment.set(product.getComment());
        price.set(product.getPrice());
    }

    public void onItemClick(View v) {

//        FragmentNavigator.Extras extras = new FragmentNavigator.Extras.Builder()
//                .addSharedElement(v, "image" + mProduct.getPid())
//                .build();
//        Navigation.findNavController((MainActivity) v.getContext(), R.id.fragment_container).navigate(R.id.action_nav_market_to_nav_market_product,extras);
//        Navigation.findNavController((MainActivity) v.getContext(), R.id.fragment_container).navigate(MarketFragmentDirections.actionNavMarketToNavMarketProduct(false), extras);
//        Navigation.findNavController((MainActivity) v.getContext(), R.id.fragment_container).navigate(R.id.action_nav_market_to_nav_market_product, null, null, extras);
        Navigation.findNavController((MainActivity) v.getContext(), R.id.fragment_container).navigate(MarketFragmentDirections.actionNavMarketToNavMarketProduct(false));
//        Navigation.findNavController((MainActivity) v.getContext(), R.id.fragment_container).navigate(MarketFragmentDirections.actionNavMarketToNavMarketProduct(false),extras);
    }

}
