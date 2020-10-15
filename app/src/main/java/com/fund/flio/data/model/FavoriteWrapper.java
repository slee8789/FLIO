package com.fund.flio.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FavoriteWrapper {
    @SerializedName("data")
    private List<Favorite> favorites;
}