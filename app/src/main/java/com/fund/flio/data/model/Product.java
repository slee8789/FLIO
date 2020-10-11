package com.fund.flio.data.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Product implements Serializable {

    private int productId;

    private String categoryDepth1;
    private String categoryDepth2;
    private String categoryOption;
    private String brand;
    private String boxYn;
    private String chgDate;
    private String imageUrl;
    private String modelNo;
    private String tag;
    private String title;
    private String uid;
    private String userImageUrl;
    private String userName;
    private String useDate;
    private String status;
    private String serialNo;
    private String regDate;
    private String repairYn;
    private String saleYn;
    private String purchaseKind;
    private String baseUrl;
    private String productRelatedUrl;
    private String purpose;
    private String purchasePrice;
    private String content;
    private String flioYn;
    private String faithYn;
    private String favoriteYn;
    private String productReview;

    private int recommendCnt;
    private int selectCnt;
    private int productPrice;
}
