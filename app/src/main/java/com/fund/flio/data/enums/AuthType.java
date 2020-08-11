package com.fund.flio.data.enums;

public enum AuthType {
    NONE("NONE"),
    KAKAO("KAKAO"),
    GOOGLE("GOOGLE"),
    NAVER("NAVER");

    private String type;

    AuthType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
