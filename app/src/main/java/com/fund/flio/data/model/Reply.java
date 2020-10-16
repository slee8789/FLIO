package com.fund.flio.data.model;



import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class Reply {

    private int replyId;
    private String imageUrl;
    private String name;
    private String content;
    private String date;
}