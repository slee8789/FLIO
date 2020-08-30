package com.fund.flio.ui.main.chat.detail;


import com.fund.flio.data.DataManager;
import com.fund.flio.data.model.body.ChatDetailBody;
import com.fund.flio.data.model.body.ChatListBody;
import com.fund.flio.di.provider.ResourceProvider;
import com.fund.flio.di.provider.SchedulerProvider;
import com.fund.flio.ui.base.BaseViewModel;
import com.google.firebase.auth.FirebaseAuth;

public class ChatDetailViewModel extends BaseViewModel {


    public ChatDetailViewModel(DataManager dataManager, SchedulerProvider schedulerProvider, ResourceProvider resourceProvider) {
        super(dataManager, schedulerProvider, resourceProvider);
        selectMyChatDetail();
    }

    private void selectMyChatDetail() {
        getCompositeDisposable().add(getDataManager().selectMyChatDetail(new ChatDetailBody(1))
                .observeOn(getSchedulerProvider().io())
                .subscribe());
    }

}
