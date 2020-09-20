package com.fund.flio.ui.main.message.chat.list;


import androidx.databinding.ObservableField;

import com.fund.flio.data.model.ChatRoom;
import com.google.firebase.auth.FirebaseAuth;
import com.orhanobut.logger.Logger;

public class ItemChatListViewModel {

    public ObservableField<String> chat = new ObservableField<>();
    public ObservableField<String> targetName = new ObservableField<>();
    public ObservableField<String> time = new ObservableField<>();
    public ObservableField<String> imageUserUrl = new ObservableField<>();
    public ObservableField<String> imageProductUrl = new ObservableField<>();
    public ChatRoom mChatRoom;

    public ItemChatListViewModel(ChatRoom chatRoom) {
        Logger.d("ItemChatViewModel " + chatRoom);
        this.mChatRoom = chatRoom;
        targetName.set(FirebaseAuth.getInstance().getUid().equals(chatRoom.getChatSourceUid()) ? chatRoom.getChatTargetName() : chatRoom.getChatSourceName());
        this.chat.set(chatRoom.getChatLastMessage());
        this.time.set(chatRoom.getChatLastDate());
        imageUserUrl.set(FirebaseAuth.getInstance().getUid().equals(chatRoom.getChatSourceUid()) ? chatRoom.getChatTargetImageUrl() : chatRoom.getChatSourceImageUrl());
        imageProductUrl.set("https://picsum.photos/170/170");
    }


}
