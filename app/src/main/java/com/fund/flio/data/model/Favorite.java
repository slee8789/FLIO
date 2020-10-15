package com.fund.flio.data.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Favorite {

    private String imageUrl;
    private int productId;
    private int productPrice;
    private String regDate;
    private String title;
}