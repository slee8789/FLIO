package com.fund.flio.data.enums;

public enum FlioYn {
    Y("예"),
    N("아니오");
    private String type;

    FlioYn(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}