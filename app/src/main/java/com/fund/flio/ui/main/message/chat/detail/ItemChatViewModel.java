package com.fund.flio.ui.main.message.chat.detail;


import androidx.databinding.ObservableField;

import com.fund.flio.data.enums.MessageType;
import com.fund.flio.data.model.Chat;
import com.orhanobut.logger.Logger;

import static com.fund.flio.utils.CommonUtils.getChatDate;
import static com.fund.flio.utils.CommonUtils.getChatTime;

public class ItemChatViewModel {

    public ObservableField<String> chat = new ObservableField<>();
    public ObservableField<String> chatSourceMessage = new ObservableField<>();
    public ObservableField<String> chatTargetMessage = new ObservableField<>();
    public ObservableField<String> chatDate = new ObservableField<>();
    public ObservableField<String> imageUrl = new ObservableField<>();

    public ItemChatViewModel(Chat chat) {
//        Logger.d("ItemChatViewModel " + chat);
        MessageType[] messageTypes = MessageType.values();

        chatSourceMessage.set(chat.getChatSourceMessage());
        chatTargetMessage.set(chat.getChatTargetMessage());
        switch (messageTypes[chat.getChatType()]) {
            case DATE:
                chatDate.set(getChatDate(chat.getChatDate()));
                break;
            case LOCAL:
            case REMOTE:
                chatDate.set(getChatTime(chat.getChatDate()));
                imageUrl.set(chat.getImageUrl());
                break;
        }


    }

}
