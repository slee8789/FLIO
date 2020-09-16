package com.fund.flio.ui.main.message.chat.list;


import androidx.databinding.ObservableField;

import com.fund.flio.data.model.ChatRoom;
import com.orhanobut.logger.Logger;

public class ItemChatListViewModel {

    public ObservableField<String> message = new ObservableField<>();
    public ObservableField<String> targetName = new ObservableField<>();
    public ObservableField<String> time = new ObservableField<>();
    public ObservableField<String> imageUserUrl = new ObservableField<>();
    public ObservableField<String> imageProductUrl = new ObservableField<>();
    public ChatRoom mChatRoom;

    public ItemChatListViewModel(ChatRoom chatRoom) {
        Logger.d("ItemChatViewModel " + chatRoom);
        this.mChatRoom = chatRoom;
        targetName.set(chatRoom.getChatTargetName());
        this.message.set("무슨 역으로 가면 될까요?");
        this.time.set("2020.08.04, 08:30");
        imageUserUrl.set("https://picsum.photos/170/170");
        imageProductUrl.set("https://picsum.photos/170/170");
    }



}
