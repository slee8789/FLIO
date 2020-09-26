package com.fund.flio.ui.main.message.chat.list;


import android.app.Activity;
import android.view.View;

import androidx.lifecycle.MutableLiveData;
import androidx.navigation.Navigation;

import com.annimon.stream.Collectors;
import com.annimon.stream.Stream;
import com.fund.flio.R;
import com.fund.flio.data.DataManager;
import com.fund.flio.data.bus.MessageBus;
import com.fund.flio.data.model.ChatRoom;
import com.fund.flio.data.model.body.ChatListBody;
import com.fund.flio.data.model.body.TestBody;
import com.fund.flio.di.provider.ResourceProvider;
import com.fund.flio.di.provider.SchedulerProvider;
import com.fund.flio.ui.base.BaseViewModel;
import com.fund.flio.ui.main.message.MessageFragmentDirections;
import com.google.firebase.auth.FirebaseAuth;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

public class ChatListViewModel extends BaseViewModel {

    private MutableLiveData<List<ChatRoom>> mChatRooms = new MutableLiveData<>();

    public MutableLiveData<List<ChatRoom>> getChatRooms() {
        return mChatRooms;
    }

    public ChatListViewModel(DataManager dataManager, SchedulerProvider schedulerProvider, ResourceProvider resourceProvider) {
        super(dataManager, schedulerProvider, resourceProvider);
//        selectMyChat();
        subscribeEvent();
    }

    public void selectMyChat() {
        getCompositeDisposable().add(getDataManager().selectMyChat(new ChatListBody(FirebaseAuth.getInstance().getCurrentUser().getUid()))
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(chatRooms -> {
                    if (chatRooms.isSuccessful()) {
                        mChatRooms.setValue(chatRooms.body().getChatRooms());
                    }
                }, onError -> Logger.e("chatRooms error " + onError)));

//        getCompositeDisposable().add(getDataManager().test(new TestBody("Default", "kjmhercules@gmail.com", "1234"))
//                .subscribeOn(getSchedulerProvider().io())
//                .observeOn(getSchedulerProvider().ui())
//                .subscribe());
    }

    private void subscribeEvent() {
        getCompositeDisposable().add(MessageBus.getInstance().getMessage()
                .observeOn(getSchedulerProvider().ui())
                .subscribe(message -> {
                    mChatRooms.setValue(Stream.of(mChatRooms.getValue()).map(chatRoom -> chatRoom.clone()).map(chatRoom -> {
                        if (chatRoom.getChatSeq() == message.getChatSeq()) {
                            chatRoom.setChatLastMessage(message.getChatSourceMessage() != null ? message.getChatSourceMessage() : message.getChatTargetMessage());
                            chatRoom.setChatLastDate(message.getChatDate());
                        }
                        return chatRoom;
                    }).collect(Collectors.toList()));
                }));
    }

    public void onItemClick(View view, ChatRoom chatRoom) {
        Logger.d("onItemClick " + chatRoom);
        Navigation.findNavController((Activity) view.getContext(), R.id.fragment_container).navigate(MessageFragmentDirections.actionNavMessageToNavChatDetail(chatRoom));

    }

}
