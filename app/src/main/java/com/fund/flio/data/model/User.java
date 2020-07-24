package com.fund.flio.data.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class User implements Serializable {

    private int id;

    private String email;

    @SerializedName("user_name")
    private String userName;

    private String phone;

    @SerializedName("image_url")
    private String imageUrl;

    @SerializedName("sns_type")
    private String snsType;

    private int age;

    private String accessToken;

    private String refreshToken;


}