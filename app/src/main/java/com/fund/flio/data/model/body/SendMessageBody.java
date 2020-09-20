package com.fund.flio.data.model.body;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class SendMessageBody {

    private long chatSeq;
    private String chatSourceName;
    private String chatSourceMessage;
    private String chatSourceMessageToken;
    private String chatSourceImageUrl;
    private String chatTargetName;
    private String chatTargetMessage;
    private String chatTargetMessageToken;
    private String chatTargetImageUrl;

    public SendMessageBody(long chatSeq, boolean isSource, String name, String message, String imageUrl, String chatSourceMessageToken, String chatTargetMessageToken) {
        this.chatSeq = chatSeq;
        if(isSource) {
            this.chatSourceName = name;
            this.chatSourceMessage = message;
            this.chatSourceImageUrl = imageUrl;
            this.chatTargetMessageToken = chatTargetMessageToken;
        } else {
            this.chatTargetName = name;
            this.chatTargetMessage = message;
            this.chatTargetImageUrl = imageUrl;
            this.chatSourceMessageToken = chatSourceMessageToken;
        }

    }
}
