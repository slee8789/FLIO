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

    private int uid;

    private String email;

    @SerializedName("name")
    private String name;

    @SerializedName("image_url")
    private String imageUrl;

    @SerializedName("firebase_token")
    private String firebaseToken;


}