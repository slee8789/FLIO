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
    }

    private void subscribeEvent() {
        getCompositeDisposable().add(MessageBus.getInstance().getMessage()
                .observeOn(getSchedulerProvider().ui())
                .subscribe(message -> {
                    //Todo : 안돼네 일단 보류
                    Logger.d("ChatListVM " + message);

//                    mChatRooms.setValue(Stream.of(mChatRooms.getValue()).map(chatRoom -> {
//                        if (chatRoom.getChatSeq() == message.getChatSeq()) {
//                            chatRoom.setChatLastMessage(message.getChatSourceMessage() != null ? message.getChatSourceMessage() : message.getChatTargetMessage());
//                            chatRoom.setChatLastDate(message.getChatDate());
//                        }
//                        return chatRoom;
//                    }).collect(Collectors.toList()));

//                    for (int i = 0; i < mChatRooms.getValue().size(); i++) {
//                        if (mChatRooms.getValue().get(i).getChatSeq() == message.getChatSeq()) {
//                            mChatRooms.getValue().get(i).setChatLastMessage(message.getChatSourceMessage() != null ? message.getChatSourceMessage() : message.getChatTargetMessage());
//                            mChatRooms.getValue().get(i).setChatLastDate(message.getChatDate());
//                        }
//                    }
//                    mChatRooms.setValue(mChatRooms.getValue());

                }));
    }

    public void onItemClick(View view, ChatRoom chatRoom) {
        Logger.d("onItemClick " + chatRoom);
//        getCompositeDisposable().add(getDataManager().postChatDetail(new ChatDetailBody(((MainActivity) view.getContext()).getAuthViewModel().)).subscribe());
//        Navigation.findNavController((Activity) view.getContext(), R.id.fragment_container).navigate(R.id.action_nav_chat_list_to_nav_chat_detail);
        Navigation.findNavController((Activity) view.getContext(), R.id.fragment_container).navigate(MessageFragmentDirections.actionNavMessageToNavChatDetail(chatRoom));

    }

}
