package com.fund.flio.ui.main.market.product.fullscreen;


import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;

import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import androidx.lifecycle.MutableLiveData;
import androidx.navigation.Navigation;

import com.annimon.stream.Collectors;
import com.annimon.stream.Stream;
import com.fund.flio.R;
import com.fund.flio.data.DataManager;
import com.fund.flio.data.enums.FaithYn;
import com.fund.flio.data.enums.FavoriteYn;
import com.fund.flio.data.enums.FlioYn;
import com.fund.flio.data.enums.Purpose;
import com.fund.flio.data.model.ChatRoom;
import com.fund.flio.data.model.Product;
import com.fund.flio.data.model.body.InsertMyChatBody;
import com.fund.flio.di.provider.ResourceProvider;
import com.fund.flio.di.provider.SchedulerProvider;
import com.fund.flio.ui.base.BaseViewModel;
import com.fund.flio.ui.main.MainActivity;
import com.fund.flio.ui.main.market.product.ProductFragmentDirections;
import com.orhanobut.logger.Logger;

import java.text.DecimalFormat;
import java.util.List;

public class ProductImageFullScreenViewModel extends BaseViewModel {

    public ObservableField<String> image = new ObservableField<>();

    public ProductImageFullScreenViewModel(DataManager dataManager, SchedulerProvider schedulerProvider, ResourceProvider resourceProvider) {
        super(dataManager, schedulerProvider, resourceProvider);
    }

}
