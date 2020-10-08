package com.fund.flio.data.enums;

public enum Purpose {
    A("음악감상"),
    B("홈레코딩"),
    C("스튜디오"),
    D("1인방송"),
    E("강의실"),
    F("하이파이"),
    G("PA"),
    H("부품"),
    I("인테리어"),
    J("룸어쿠스틱");

    private String type;

    Purpose(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}