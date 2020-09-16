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

    private String uid;

    private String email;

    private String name;

    private String imageUrl;

    private String platformToken;

    private String userToken;

    private String messageToken;


}