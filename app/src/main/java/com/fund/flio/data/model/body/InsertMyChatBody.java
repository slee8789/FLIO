package com.fund.flio.data.model.body;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class InsertMyChatBody {
    private String chatSourceUid;
    private String chatTargetUid;
    private int productId;
}
