package com.fund.flio.data.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SearchWrapper implements Serializable {

    @SerializedName("data")
    private List<Search> searchs;
}
