package com.fund.flio.ui.main.market;

import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;

import com.fund.flio.data.model.Banner;
import com.fund.flio.data.model.Product;

public class ItemProductViewModel {

    public ObservableField<String> imageUrl = new ObservableField<>();
    public ObservableBoolean isLike = new ObservableBoolean();

    public ItemProductViewModel(Product product) {
        imageUrl.set(product.getImageUrl());
        isLike.set(true);
    }
}
