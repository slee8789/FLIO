package com.fund.flio.ui.main.home;


import android.view.View;

import androidx.databinding.ObservableField;

import com.fund.flio.data.DataManager;
import com.fund.flio.di.provider.ResourceProvider;
import com.fund.flio.di.provider.SchedulerProvider;
import com.fund.flio.ui.base.BaseViewModel;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.LogoutResponseCallback;
import com.orhanobut.logger.Logger;

public class HomeViewModel extends BaseViewModel {

    public ObservableField<String> newsUrl = new ObservableField<>();

    public HomeViewModel(DataManager dataManager, SchedulerProvider schedulerProvider, ResourceProvider resourceProvider) {
        super(dataManager, schedulerProvider, resourceProvider);
        newsUrl.set("https://picsum.photos/500/200");
    }

//    public void testDetail() {
//        getNavigator().goDetail();
//    }

//    public void testSelect() {
//        getDataManager().getTestSelect().subscribe();
//    }

    public void onCategoryClick(View v) {

    }

}
