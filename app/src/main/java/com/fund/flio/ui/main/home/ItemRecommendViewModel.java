package com.fund.flio.ui.main.home;

import android.view.View;

import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import androidx.navigation.Navigation;

import com.fund.flio.R;
import com.fund.flio.data.model.Product;
import com.fund.flio.ui.main.MainActivity;

public class ItemRecommendViewModel {

    public ObservableField<String> imageUrl = new ObservableField<>();
    public ObservableBoolean isLike = new ObservableBoolean();
    public ObservableField<String> comment = new ObservableField<>();
    public ObservableField<String> price = new ObservableField<>();


    public ItemRecommendViewModel(Product recommend) {
        imageUrl.set(recommend.getImageUrl());
        isLike.set(recommend.isLike());
        comment.set(recommend.getContent());
        price.set(recommend.getPurchasePrice());
    }

    public void onItemClick(View v) {
        if(Navigation.findNavController((MainActivity) v.getContext(), R.id.fragment_container).getCurrentDestination().getId() == R.id.nav_home) {
            Navigation.findNavController((MainActivity) v.getContext(), R.id.fragment_container).navigate(R.id.action_nav_home_to_nav_market_product);
        } else {
            Navigation.findNavController((MainActivity) v.getContext(), R.id.fragment_container).navigate(R.id.action_nav_market_product_self);
        }

    }
}
