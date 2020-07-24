package com.fund.flio.ui.main.home;

import androidx.databinding.ObservableField;

import com.fund.flio.data.model.Banner;

public class ItemBannerViewModel {

    public ObservableField<String> imageUrl = new ObservableField<>();

    public ItemBannerViewModel(Banner banner) {
        imageUrl.set(banner.getImageUrl());
    }
}
