package com.fund.flio.data.model;


import androidx.annotation.NonNull;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ChatRoom implements Cloneable, Serializable {

    private int chatSeq;
    private String chatSourceUid;
    private String chatSourceName;
    private String chatSourceImageUrl;
    private String chatSourceMessageToken;
    private String chatTargetUid;
    private String chatTargetName;
    private String chatTargetImageUrl;
    private String chatTargetMessageToken;
    private String chatLastDate;
    private String chatLastMessage;
    private int productId;
    private String productName;
    private String productPrice;

    @NonNull
    @Override
    public ChatRoom clone() {
        ChatRoom chatRoom = new ChatRoom();
        chatRoom.setChatSeq(chatSeq);
        chatRoom.setChatSourceUid(chatSourceUid);
        chatRoom.setChatSourceName(chatSourceName);
        chatRoom.setChatSourceImageUrl(chatSourceImageUrl);
        chatRoom.setChatSourceMessageToken(chatSourceMessageToken);
        chatRoom.setChatTargetUid(chatTargetUid);
        chatRoom.setChatTargetName(chatTargetName);
        chatRoom.setChatTargetImageUrl(chatTargetImageUrl);
        chatRoom.setChatTargetMessageToken(chatTargetMessageToken);
        chatRoom.setChatLastDate(chatLastDate);
        chatRoom.setChatLastMessage(chatLastMessage);
        chatRoom.setProductId(productId);
        chatRoom.setProductName(productName);
        chatRoom.setProductPrice(productPrice);
        return chatRoom;
    }
}