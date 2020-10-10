package com.fund.flio.data.model;



import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class Buyer {

    private String targetUid;
    private String targetName;
    private String targetImageUrl;

}