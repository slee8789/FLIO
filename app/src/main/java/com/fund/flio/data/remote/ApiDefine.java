package com.fund.flio.data.remote;

public class ApiDefine {

    public interface Header {
        String CONTENT_TYPE_JSON = "Content-Type:application/json";
        String CONTENT_TYPE_MULTIPART = "Content-Type:multipart/form-data";
        String ACCEPT_JSON = "Accept:application/json;charset=utf-8";
        String AUTHORIZATION_KAKAO = "Authorization:KakaoAK 797bf35ca01c75734348bb6445849393";
    }

    public interface Body {
        String TEST_SELECT = "test/selectTest";
        String TEST_INSERT = "v2/local/geo/coord2address.json";
        String TEST_IMAGE_UPLOAD = "board/insertBoard";


        String AUTH_TOKEN = "verifyToken";

    }


}
