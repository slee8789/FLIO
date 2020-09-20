package com.fund.flio.data.model;


import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

@Data
@EqualsAndHashCode(callSuper = false)
public class ChatRoom implements Serializable {

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

}