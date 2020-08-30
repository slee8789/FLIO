package com.fund.flio.ui.main.market;

import android.view.View;

import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import androidx.navigation.Navigation;

import com.fund.flio.R;
import com.fund.flio.data.model.Banner;
import com.fund.flio.data.model.Product;
import com.fund.flio.ui.main.MainActivity;

public class ItemProductViewModel {

    public ObservableField<String> imageUrl = new ObservableField<>();
    public ObservableBoolean isLike = new ObservableBoolean();

    public ItemProductViewModel(Product product) {
        imageUrl.set(product.getImageUrl());
        isLike.set(true);
    }

    public void onItemClick(View v) {
        Navigation.findNavController((MainActivity) v.getContext(), R.id.fragment_container).navigate(R.id.action_nav_market_to_nav_market_product);
    }

}
