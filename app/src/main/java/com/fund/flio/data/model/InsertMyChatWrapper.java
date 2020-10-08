package com.fund.flio.data.model;

import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;

@lombok.Data
@AllArgsConstructor
public class InsertMyChatWrapper {

    @SerializedName("data")
    private ChatRoom chatRoom;
}
