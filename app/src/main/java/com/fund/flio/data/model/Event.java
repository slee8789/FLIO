package com.fund.flio.data.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Event implements Serializable {

    private String eventId;

    private String title;

    private String date;

    private String description;

    private String imageUrl;


}