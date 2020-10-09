package com.fund.flio.data.enums;

public enum SaleYn {
    Y("판매완료"),
    S("예약중"),
    R("판매중");
    private String type;

    SaleYn(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}