package com.fund.flio.data.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class News implements Serializable {

    private String newsId;

    private String title;

    private String date;

    private String description;

    private String imageUrl;


}