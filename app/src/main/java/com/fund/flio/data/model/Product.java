package com.fund.flio.data.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Product implements Serializable {

    private int productId;
    private String productName;
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
    private boolean isFlio;
    private boolean isFaith;
    private boolean isLike;
    private int recommendCnt;
    private int selectCnt;
}
