package com.fund.flio.data.enums;

public enum UseDate {
    A("일주일이내"),
    B("한달이내"),
    C("1년이내"),
    D("1년~3년"),
    E("3년~5년"),
    F("5년~10년"),
    G("빈티지");

    private String type;

    UseDate(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}