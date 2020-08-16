package com.fund.flio.ui.main.home;

import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;

import com.bumptech.glide.request.RequestCoordinator;
import com.fund.flio.data.model.Banner;
import com.fund.flio.data.model.Recommend;

public class ItemRecommendViewModel {

    public ObservableField<String> imageUrl = new ObservableField<>();
    public ObservableBoolean isLike = new ObservableBoolean();
    public ObservableField<String> comment = new ObservableField<>();
    public ObservableField<String> price = new ObservableField<>();


    public ItemRecommendViewModel(Recommend recommend) {
        imageUrl.set(recommend.getImageUrl());
        isLike.set(recommend.isLike());
        comment.set(recommend.getComment());
        price.set(recommend.getPrice());
    }
}
