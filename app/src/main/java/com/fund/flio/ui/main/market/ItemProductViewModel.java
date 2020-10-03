package com.fund.flio.ui.main.market;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.FragmentNavigator;

import com.fund.flio.R;
import com.fund.flio.data.model.Product;
import com.fund.flio.databinding.ItemProductBinding;
import com.fund.flio.ui.main.MainActivity;

public class ItemProductViewModel {

    public ObservableField<String> imageUrl = new ObservableField<>();
    public ObservableBoolean isLike = new ObservableBoolean();
    public ObservableField<String> comment = new ObservableField<>();
    public ObservableField<String> price = new ObservableField<>();
    private ItemProductBinding binding;

    public ItemProductViewModel(ItemProductBinding binding, Product product) {
        this.binding = binding;
        imageUrl.set(product.getImageUrl());
        isLike.set(product.isLike());
        comment.set(product.getComment());
        price.set(product.getPrice());
    }

    public void onItemClick(View v) {
//        Navigation.findNavController((MainActivity) v.getContext(), R.id.fragment_container).navigate(R.id.action_nav_market_to_nav_market_product);

        FragmentNavigator.Extras extras = new FragmentNavigator.Extras.Builder()
                .addSharedElement(binding.image, "image")
//                .addSharedElement(titleView, "header_title")
                .build();
        Navigation.findNavController((MainActivity) v.getContext(), R.id.fragment_container).navigate(R.id.action_nav_market_to_nav_market_product,
                null, // Bundle of args
                null, // NavOptions
                extras);


    }

}
