package com.fund.flio.ui.main.market;

import android.view.View;

import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.FragmentNavigator;

import com.fund.flio.R;
import com.fund.flio.data.model.Banner;
import com.fund.flio.data.model.Product;
import com.fund.flio.ui.main.MainActivity;

public class ItemProductViewModel {

    public ObservableField<String> imageUrl = new ObservableField<>();
    public ObservableBoolean isLike = new ObservableBoolean();
    private Product mProduct;

    public ItemProductViewModel(Product product) {
        mProduct = product;
        imageUrl.set(product.getImageUrl());
        isLike.set(true);
    }

    public void onItemClick(View v) {
        FragmentNavigator.Extras extras = new FragmentNavigator.Extras.Builder()
                .addSharedElement(v, "image" + mProduct.getPid())
                .build();
//        Navigation.findNavController((MainActivity) v.getContext(), R.id.fragment_container).navigate(R.id.action_nav_market_to_nav_market_product,extras);
//        Navigation.findNavController((MainActivity) v.getContext(), R.id.fragment_container).navigate(MarketFragmentDirections.actionNavMarketToNavMarketProduct(false), extras);
        Navigation.findNavController((MainActivity) v.getContext(), R.id.fragment_container).navigate(R.id.action_nav_market_to_nav_market_product, null, null, extras);
    }

}
