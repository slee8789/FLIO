package com.fund.flio.ui.main.message.chat.detail;


import androidx.databinding.ObservableField;

import com.fund.flio.data.model.Message;
import com.orhanobut.logger.Logger;

public class ItemChatViewModel {

    public ObservableField<String> message = new ObservableField<>();
    public ObservableField<String> time = new ObservableField<>();
    public ObservableField<String> imageUrl = new ObservableField<>();

    public ItemChatViewModel(Message message) {
        Logger.d("ItemChatViewModel " + message);
        switch (message.getMessageType()) {
            case 0:
                this.time.set("2020년 8월 27일");
                break;
            case 1:
                this.message.set(message.getMessage());
                break;
            case 2:
                this.message.set(message.getMessage());
                imageUrl.set("https://k.kakaocdn.net/dn/bUomyI/btqDXEY6Ybm/0eJ7kQjwF6qwjztpV7QZGK/img_640x640.jpg");
                break;
        }


    }

}
