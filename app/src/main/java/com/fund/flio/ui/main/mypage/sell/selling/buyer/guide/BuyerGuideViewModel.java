package com.fund.flio.ui.main.mypage.sell.selling.buyer.guide;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import androidx.databinding.ObservableField;
import androidx.lifecycle.MutableLiveData;
import androidx.navigation.Navigation;

import com.fund.flio.R;
import com.fund.flio.data.DataManager;
import com.fund.flio.data.model.Product;
import com.fund.flio.di.provider.ResourceProvider;
import com.fund.flio.di.provider.SchedulerProvider;
import com.fund.flio.ui.base.BaseViewModel;
import com.fund.flio.ui.main.message.MessageFragmentDirections;

import java.util.List;

public class BuyerGuideViewModel extends BaseViewModel {

    public ObservableField<String> imageUrl = new ObservableField<>();
    public ObservableField<String> productName = new ObservableField<>();
    public ObservableField<Integer> productId = new ObservableField<>();


    public BuyerGuideViewModel(DataManager dataManager, SchedulerProvider schedulerProvider, ResourceProvider resourceProvider) {
        super(dataManager, schedulerProvider, resourceProvider);
    }

    public void showBuyerList(View view) {
        Navigation.findNavController((Activity) view.getContext(), R.id.fragment_container).navigate(BuyerGuideFragmentDirections.actionNavBuyerGuideToNavBuyerList(productId.get()));
    }

    public void navigateUp(View view) {
        Navigation.findNavController((Activity) view.getContext(), R.id.fragment_container).navigateUp();
    }


}
