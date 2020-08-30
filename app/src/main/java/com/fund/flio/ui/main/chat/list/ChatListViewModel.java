package com.fund.flio.ui.main.chat.list;


import android.app.Activity;
import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.navigation.Navigation;

import com.fund.flio.R;
import com.fund.flio.data.DataManager;
import com.fund.flio.data.model.Message;
import com.fund.flio.data.model.body.ChatDetailBody;
import com.fund.flio.data.model.body.ChatListBody;
import com.fund.flio.di.provider.ResourceProvider;
import com.fund.flio.di.provider.SchedulerProvider;
import com.fund.flio.ui.base.BaseViewModel;
import com.fund.flio.ui.main.MainActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.orhanobut.logger.Logger;

public class ChatListViewModel extends BaseViewModel {

    public static MutableLiveData<Integer> counter = new MutableLiveData<>();

    public ChatListViewModel(DataManager dataManager, SchedulerProvider schedulerProvider, ResourceProvider resourceProvider) {
        super(dataManager, schedulerProvider, resourceProvider);
        selectMyChat();
    }

    private void selectMyChat() {
        getCompositeDisposable().add(getDataManager().selectMyChat(new ChatListBody(FirebaseAuth.getInstance().getCurrentUser().getUid()))
                .observeOn(getSchedulerProvider().io())
                .subscribe());
    }

    public void onItemClick(View view, Message message) {
        Logger.d("onItemClick " + message);
//        getCompositeDisposable().add(getDataManager().postChatDetail(new ChatDetailBody(((MainActivity) view.getContext()).getAuthViewModel().)).subscribe());
        Navigation.findNavController((Activity) view.getContext(), R.id.fragment_container).navigate(R.id.action_nav_chat_list_to_nav_chat_detail);

    }

}
