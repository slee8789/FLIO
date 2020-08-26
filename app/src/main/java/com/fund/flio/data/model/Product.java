package com.fund.flio.data.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Product {

    private String imageUrl;
    private boolean isFlio;
    private boolean isFaith;
    private boolean isLike;
    private String comment;
    private String price;
}
