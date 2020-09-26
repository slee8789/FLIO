package com.fund.flio.data.model;

import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity(indices = @Index(value = {"keyword"}, unique = true))
@EqualsAndHashCode(callSuper = false)
public class Keyword implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String keyword;


}