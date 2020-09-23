package com.fund.flio.data.model;



import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity(indices = @Index(value = {"title"}, unique = true))
@EqualsAndHashCode(callSuper = false)
public class SearchResult {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title;
    private long date;
}
