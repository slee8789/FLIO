package com.fund.flio.data.enums;

public enum TradeKind {
    DIRECT("직거래"),
    DELIVERY("택배거리");

    private String type;

    TradeKind(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}