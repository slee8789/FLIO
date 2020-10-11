package com.fund.flio.data.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductsWrapper implements Serializable {

    @SerializedName("data")
    private List<Product> products;

}
