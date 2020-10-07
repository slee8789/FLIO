package com.fund.flio.data.enums;

public enum UseDate {
    A("일주일이내"),
    B("한달이내"),
    C("1년"),
    D("3년"),
    E("5년"),
    F("빈티지");

    private String type;

    UseDate(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}