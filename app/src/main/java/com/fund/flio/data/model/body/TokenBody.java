package com.fund.flio.data.model.body;

import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class TokenBody {
    private String type;

    @SerializedName("platform_token")
    private String platformToken;
    
    @SerializedName("message_token")
    private String messageToken;
}
