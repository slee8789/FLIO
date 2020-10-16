package com.fund.flio.ui.main.home;

import android.view.View;

import androidx.databinding.ObservableField;

import com.fund.flio.data.model.Banner;
import com.orhanobut.logger.Logger;

public class ItemBannerImageViewModel {

    public ObservableField<String> imageUrl = new ObservableField<>();

    public ItemBannerImageViewModel(Banner product) {
        Logger.d("ItemProductImageViewModel " + product);
        imageUrl.set(product.getImageUrl());

    }


    public void onItemClick(View v) {

    }

}
