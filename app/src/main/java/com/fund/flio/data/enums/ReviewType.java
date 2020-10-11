package com.fund.flio.data.enums;

public enum ReviewType {
    A("최고에요"),
    B("괜찮았어요"),
    C("친절하게 잘 설명해주셨어요"),
    D("중고 제품이 사진/설명과 동일했어요"),
    E("그저 그랬어요"),
    NO_REVIEW("리뷰 없음");
    private String type;

    ReviewType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}