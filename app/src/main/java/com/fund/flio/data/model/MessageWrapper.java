package com.fund.flio.data.model;



import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class MessageWrapper {

    @SerializedName("data")
    private List<Message> messages;

}