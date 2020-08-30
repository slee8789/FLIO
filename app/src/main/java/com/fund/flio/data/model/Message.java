package com.fund.flio.data.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldNameConstants;

@Data
@EqualsAndHashCode(callSuper = false)
public class Message {

    private String time;
    private String message;
    private int messageType;
    private String imageUrl;

    public Message(@NonNull String time, @NonNull String message, @NonNull int messageType) {
        this.time = time;
        this.message = message;
        this.messageType = messageType;
    }
}