package com.fund.flio.data.model;



import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

@Data
@EqualsAndHashCode(callSuper = false)
public class ChatRoom {

    private int chatSeq;
    private String chatDate;
    private String chatSourceName;
    private String chatSourceUid;
    private String chatTargetName;
    private String chatTargetUid;
    private int productId;
    private String productName;

}