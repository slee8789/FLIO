package com.fund.flio.data.model;



import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

@Data
@EqualsAndHashCode(callSuper = false)
public class Chat {

    private int chatSeq;
    private int chatIndex;
    private String chatDate;
    private String chatSourceMessage;
    private String chatTargetMessage;
    private int chatType;
    private String imageUrl;


    public Chat(int chatSeq, int chatIndex, boolean isSource, String chatDate, String chatMessage, String chatImageUrl, int chatType) {
        this.chatSeq = chatSeq;
        this.chatIndex = chatIndex;
        this.chatDate = chatDate;
        this.imageUrl = chatImageUrl;
        if(isSource) {
            chatSourceMessage = chatMessage;
        } else {
            chatTargetMessage = chatMessage;
        }

        this.chatType = chatType;
    }

    public Chat(String chatDate, int chatType) {
        this.chatDate = chatDate;
        this.chatType = chatType;
    }
}