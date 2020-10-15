package com.fund.flio.ui.main.mypage.sell.selling.buyer.list;


import androidx.databinding.ObservableField;

import com.fund.flio.data.model.Buyer;
import com.fund.flio.data.model.ChatRoom;
import com.google.firebase.auth.FirebaseAuth;
import com.orhanobut.logger.Logger;

public class ItemBuyerViewModel {

    public ObservableField<String> chat = new ObservableField<>();
    public ObservableField<String> targetName = new ObservableField<>();
    public ObservableField<String> time = new ObservableField<>();
    public ObservableField<String> imageUserUrl = new ObservableField<>();
    public ObservableField<String> imageProductUrl = new ObservableField<>();
    public Buyer mByuer;

    public ItemBuyerViewModel(Buyer buyer) {
        Logger.d("ItemBuyerViewModel " + buyer);
//        this.mChatRoom = chatRoom;
        mByuer = buyer;
        imageUserUrl.set(buyer.getTargetImageUrl());
        targetName.set(buyer.getTargetName());
//        this.chat.set(chatRoom.getChatLastMessage());
//        this.time.set(chatRoom.getChatLastDate());
//        imageUserUrl.set(FirebaseAuth.getInstance().getUid().equals(chatRoom.getChatSourceUid()) ? chatRoom.getChatTargetImageUrl() : chatRoom.getChatSourceImageUrl());
//        imageProductUrl.set("http://flio.iptime.org:8080/image/" + chatRoom.getProductBaseUrl() + "/" + chatRoom.getProductImageUrl().split(",")[0]);
//        imageProductUrl.set("https://picsum.photos/170/170");
    }


}
