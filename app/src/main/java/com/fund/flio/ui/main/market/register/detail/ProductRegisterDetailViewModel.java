package com.fund.flio.ui.main.market.register.detail;

import android.content.Context;
import android.net.Uri;
import android.view.View;

import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import androidx.lifecycle.MutableLiveData;

import com.annimon.stream.Collectors;
import com.annimon.stream.Stream;
import com.fund.flio.R;
import com.fund.flio.data.DataManager;
import com.fund.flio.di.provider.ResourceProvider;
import com.fund.flio.di.provider.SchedulerProvider;
import com.fund.flio.ui.base.BaseViewModel;
import com.orhanobut.logger.Logger;

import java.io.File;
import java.util.List;

import gun0912.tedimagepicker.builder.TedRxImagePicker;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import static com.fund.flio.utils.CommonUtils.getRealPathFromUri;

public class ProductRegisterDetailViewModel extends BaseViewModel {


    public ProductRegisterDetailViewModel(DataManager dataManager, SchedulerProvider schedulerProvider, ResourceProvider resourceProvider) {
        super(dataManager, schedulerProvider, resourceProvider);
    }



}
