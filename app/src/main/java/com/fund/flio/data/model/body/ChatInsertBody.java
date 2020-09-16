package com.fund.flio.data.model.body;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ChatInsertBody {

    private long chatSeq;
    private String chatSourceMessage;
    private String chatTargetMessage;

}
