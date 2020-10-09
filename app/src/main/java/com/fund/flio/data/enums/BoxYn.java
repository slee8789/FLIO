package com.fund.flio.data.enums;

public enum BoxYn {
    Y("예"),
    N("아니오");
    private String type;

    BoxYn(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}