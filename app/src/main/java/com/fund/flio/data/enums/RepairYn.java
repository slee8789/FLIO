package com.fund.flio.data.enums;

public enum RepairYn {
    YES("예"),
    NO("아니오");
    private String type;

    RepairYn(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}