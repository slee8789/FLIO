package com.fund.flio.ui.main.mypage.setting;


import android.view.View;

import androidx.navigation.Navigation;

import com.fund.flio.R;
import com.fund.flio.data.DataManager;
import com.fund.flio.di.provider.ResourceProvider;
import com.fund.flio.di.provider.SchedulerProvider;
import com.fund.flio.ui.base.BaseViewModel;
import com.fund.flio.ui.main.MainActivity;

public class SettingViewModel extends BaseViewModel {

    public SettingViewModel(DataManager dataManager, SchedulerProvider schedulerProvider, ResourceProvider resourceProvider) {
        super(dataManager, schedulerProvider, resourceProvider);
    }

    public void goBack(View v) {
        Navigation.findNavController((MainActivity) v.getContext(), R.id.fragment_container).navigateUp();
    }

    public void goSetting(View v) {
        Navigation.findNavController((MainActivity) v.getContext(), R.id.fragment_container).navigateUp();
    }

    public void goSellList(View v) {
        Navigation.findNavController((MainActivity) v.getContext(), R.id.fragment_container).navigate(R.id.action_nav_my_page_to_nav_sell_list);
    }


}
