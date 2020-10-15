package com.fund.flio.data.remote;

public class ApiDefine {

    public interface Header {
        String CONTENT_TYPE_JSON = "Content-Type:application/json";
        String CONTENT_TYPE_MULTIPART = "Content-Type:multipart/form-data";
        String ACCEPT_JSON = "Accept:application/json;charset=utf-8";
        String AUTHORIZATION_KAKAO = "Authorization:KakaoAK 797bf35ca01c75734348bb6445849393";
    }

    public interface Body {

        String AUTH_TOKEN = "verifyToken";
        String INSERT_USER = "user/insertUser";
        String LOGOUT_USER = "user/updateLogoutUser";

        String INSERT_MY_CHAT = "chat/insertMyChat";
        String SELECT_MY_CHAT = "chat/selectMyChat";
        String SELECT_MY_CHAT_DETAIL = "chat/selectMyChatDetail";
        String SEND_MESSAGE = "message/sendMessage";

        String INSERT_PRODUCT = "board/insertProduct";
        String SELECT_PRODUCT = "board/selectProduct";
        String DETAIL_PRODUCT = "board/detailProduct";
        String MAIN_PRODUCT = "board/mainProduct";
        String MY_PAGE_PRODUCT = "board/mypageProduct";
        String PURPOSE_PRODUCT = "board/purposeProduct";
        String RECOMMAND_PRODUCT = "board/recommendProduct";
        String SEARCH_PRODUCT = "board/searchProduct";
        String TARGET_PRODUCT = "board/targetProduct";

        String TARGET_USER_LIST = "board/targetUserList";
        String TARGET_USER_UPDATE = "board/targetUserUpdate";
        String TARGET_USER_REVIEW = "board/targetUserReview";
        String SWITCH_FAVORITE = "favorite/switchFavorite";
        String SELECT_FAVORITE = "favorite/selectFavorite";

        String SEARCH_KEYWORD = "search/keyword";

    }


}
