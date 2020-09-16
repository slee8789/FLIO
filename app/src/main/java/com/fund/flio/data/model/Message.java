package com.fund.flio.data.model;



import com.google.gson.annotations.SerializedName;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

@Data
@EqualsAndHashCode(callSuper = false)
public class Message {

    private int messageId;
    private String time;
    private String message;
    private int messageType;
    private String imageUrl;

    public Message(int messageId, @NonNull String time, @NonNull String message, int messageType) {
        this.messageId = messageId;
        this.time = time;
        this.message = message;
        this.messageType = messageType;
    }
}