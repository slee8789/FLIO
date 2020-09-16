package com.fund.flio.ui.main.message.chat.list;


import android.app.Activity;
import android.view.View;

import androidx.lifecycle.MutableLiveData;
import androidx.navigation.Navigation;

import com.fund.flio.R;
import com.fund.flio.data.DataManager;
import com.fund.flio.data.model.ChatRoom;
import com.fund.flio.data.model.Message;
import com.fund.flio.data.model.body.ChatListBody;
import com.fund.flio.di.provider.ResourceProvider;
import com.fund.flio.di.provider.SchedulerProvider;
import com.fund.flio.ui.base.BaseViewModel;
import com.fund.flio.ui.main.message.MessageFragmentDirections;
import com.google.firebase.auth.FirebaseAuth;
import com.orhanobut.logger.Logger;

import java.util.List;

public class ChatListViewModel extends BaseViewModel {

    private MutableLiveData<List<ChatRoom>> mChatRooms = new MutableLiveData<>();

    public MutableLiveData<List<ChatRoom>> getChatRooms() {
        return mChatRooms;
    }

    public ChatListViewModel(DataManager dataManager, SchedulerProvider schedulerProvider, ResourceProvider resourceProvider) {
        super(dataManager, schedulerProvider, resourceProvider);
        selectMyChat();
    }

    private void selectMyChat() {
        getCompositeDisposable().add(getDataManager().selectMyChat(new ChatListBody(FirebaseAuth.getInstance().getCurrentUser().getUid()))
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(chatRooms -> {
                    if (chatRooms.isSuccessful()) {
                        mChatRooms.setValue(chatRooms.body().getChatRooms());
                    }
                }, onError -> Logger.e("chatRooms error " + onError)));
    }

    public void onItemClick(View view, ChatRoom chatRoom) {
        Logger.d("onItemClick " + chatRoom);
//        getCompositeDisposable().add(getDataManager().postChatDetail(new ChatDetailBody(((MainActivity) view.getContext()).getAuthViewModel().)).subscribe());
//        Navigation.findNavController((Activity) view.getContext(), R.id.fragment_container).navigate(R.id.action_nav_chat_list_to_nav_chat_detail);
        Navigation.findNavController((Activity) view.getContext(), R.id.fragment_container).navigate(MessageFragmentDirections.actionNavMessageToNavChatDetail(chatRoom.getChatSeq()));

    }

}
