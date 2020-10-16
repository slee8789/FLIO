package com.fund.flio.ui.main.message.reply.list;


import androidx.databinding.ObservableField;

import com.fund.flio.data.model.ChatRoom;
import com.fund.flio.data.model.Reply;
import com.google.firebase.auth.FirebaseAuth;
import com.orhanobut.logger.Logger;

public class ItemReplyListViewModel {

    public ObservableField<String> imageUserUrl = new ObservableField<>();
    public ObservableField<String> content = new ObservableField<>();
    public ObservableField<String> name = new ObservableField<>();
    public ObservableField<String> date = new ObservableField<>();


    public ItemReplyListViewModel(Reply chatRoom) {
        Logger.d("ItemChatViewModel " + chatRoom);
        imageUserUrl.set(chatRoom.getImageUrl());
        content.set(chatRoom.getContent());
        name.set(chatRoom.getName());
        date.set(chatRoom.getDate());

    }


}
