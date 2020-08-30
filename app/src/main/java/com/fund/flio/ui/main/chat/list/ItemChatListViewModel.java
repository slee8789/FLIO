package com.fund.flio.ui.main.chat.list;


import androidx.databinding.ObservableField;

import com.fund.flio.data.model.Message;
import com.orhanobut.logger.Logger;

public class ItemChatListViewModel {

    public ObservableField<String> message = new ObservableField<>();
    public ObservableField<String> time = new ObservableField<>();
    public ObservableField<String> imageUserUrl = new ObservableField<>();
    public ObservableField<String> imageProductUrl = new ObservableField<>();
    public Message mMessage;

    public ItemChatListViewModel(Message message) {
        Logger.d("ItemChatViewModel " + message);
        this.mMessage = message;
        this.message.set("무슨 역으로 가면 될까요?");
        this.time.set("2020.08.04, 08:30");
        imageUserUrl.set("https://k.kakaocdn.net/dn/bUomyI/btqDXEY6Ybm/0eJ7kQjwF6qwjztpV7QZGK/img_640x640.jpg");
        imageProductUrl.set("https://homepages.cae.wisc.edu/~ece533/images/baboon.png");
    }



}
