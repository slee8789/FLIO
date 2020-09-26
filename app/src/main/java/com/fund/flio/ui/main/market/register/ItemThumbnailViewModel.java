package com.fund.flio.ui.main.market.register;

import android.view.View;

import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import androidx.navigation.Navigation;

import com.fund.flio.R;
import com.fund.flio.data.model.Product;
import com.fund.flio.ui.main.MainActivity;

public class ItemThumbnailViewModel {

    public ObservableField<String> imageUrl = new ObservableField<>();
    public ObservableBoolean isLike = new ObservableBoolean();
    public ObservableField<String> comment = new ObservableField<>();
    public ObservableField<String> price = new ObservableField<>();
    private Product mProduct;

    public ItemThumbnailViewModel(Product product) {
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
        Navigation.findNavController((MainActivity) v.getContext(), R.id.fragment_container).navigate(R.id.action_nav_market_to_nav_market_product);
//        Navigation.findNavController((MainActivity) v.getContext(), R.id.fragment_container).navigate(MarketFragmentDirections.actionNavMarketToNavMarketProduct(false), extras);
//        Navigation.findNavController((MainActivity) v.getContext(), R.id.fragment_container).navigate(R.id.action_nav_market_to_nav_market_product, null, null, extras);
//        Navigation.findNavController((MainActivity) v.getContext(), R.id.fragment_container).navigate(MarketFragmentDirections.actionNavMarketToNavMarketProduct(false));
//        Navigation.findNavController((MainActivity) v.getContext(), R.id.fragment_container).navigate(MarketFragmentDirections.actionNavMarketToNavMarketProduct(false),extras);

//        FragmentNavigator.Extras extras = new FragmentNavigator.Extras.Builder()
//                .
//                .build();
//        Navigation.findNavController((MainActivity) v.getContext(), R.id.fragment_container).navigate(MarketFragmentDirections.actionNavMarketToNavMarketProduct(false),extras);


    }

}
