package com.fund.flio.data.enums;

public enum PurchaseKind {
    Y("정품"),
    R("중고"),
    S("기타");

    private String type;

    PurchaseKind(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
//Y: 정품, R: 중고, S: 기타)