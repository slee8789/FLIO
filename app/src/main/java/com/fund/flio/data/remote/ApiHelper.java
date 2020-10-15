package com.fund.flio.data.remote;


import com.fund.flio.data.model.BuyerWrapper;
import com.fund.flio.data.model.ChatRoomWrapper;
import com.fund.flio.data.model.InsertMyChatWrapper;
import com.fund.flio.data.model.MessageWrapper;
import com.fund.flio.data.model.Product;
import com.fund.flio.data.model.ProductWrapper;
import com.fund.flio.data.model.ProductsWrapper;
import com.fund.flio.data.model.SearchWrapper;
import com.fund.flio.data.model.User;
import com.fund.flio.data.model.body.ChatDetailBody;
import com.fund.flio.data.model.body.InsertMyChatBody;
import com.fund.flio.data.model.body.SearchBody;
import com.fund.flio.data.model.body.SendMessageBody;
import com.fund.flio.data.model.body.ChatListBody;
import com.fund.flio.data.model.body.TokenBody;

import io.reactivex.rxjava3.core.Single;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface ApiHelper {

    // 유저 정보
    @POST(ApiDefine.Body.AUTH_TOKEN)
    @Headers({ApiDefine.Header.CONTENT_TYPE_JSON})
    Single<Response<User>> postAuthToken(@Body TokenBody tokenBody);

    // INSERT 유저정보
    @POST(ApiDefine.Body.INSERT_USER)
    @Headers({ApiDefine.Header.CONTENT_TYPE_JSON})
    Single<Response<Void>> postInsertUser(@Body User user);

    // 유저 로그아웃 업데이트
    @POST(ApiDefine.Body.LOGOUT_USER)
    @Headers({ApiDefine.Header.CONTENT_TYPE_JSON})
    Single<Response<Void>> postLogoutUser(@Query("uid") String uid);

    // 채팅내역 불러오기
    @POST(ApiDefine.Body.SELECT_MY_CHAT)
    @Headers({ApiDefine.Header.CONTENT_TYPE_JSON})
    Single<Response<ChatRoomWrapper>> selectMyChat(@Body ChatListBody chatListBody);

    // 채팅리스트 불러오기
    @POST(ApiDefine.Body.SELECT_MY_CHAT_DETAIL)
    @Headers({ApiDefine.Header.CONTENT_TYPE_JSON})
    Single<Response<MessageWrapper>> selectMyChatDetail(@Body ChatDetailBody chatDetailBody);

    // 1:1 SEND 채팅 메세지 전송
    @POST(ApiDefine.Body.SEND_MESSAGE)
    @Headers({ApiDefine.Header.CONTENT_TYPE_JSON})
    Single<Response<Void>> sendMessage(@Body SendMessageBody sendMessageBody);

    // 채팅 메세지 저장
    @POST(ApiDefine.Body.INSERT_MY_CHAT)
    @Headers({ApiDefine.Header.CONTENT_TYPE_JSON})
    Single<Response<InsertMyChatWrapper>> insertMyChat(@Body InsertMyChatBody insertMyChatBody);

    // 상품글 올리기
    @Multipart
    @POST(ApiDefine.Body.INSERT_PRODUCT)
    Single<Response<Void>> insertProduct(
            @Part("title") RequestBody title,
            @Part("content") RequestBody content,
            @Part("categoryDepth1") RequestBody categoryDepth1,
            @Part("categoryDepth2") RequestBody categoryDepth2,
            @Part("saleYn") RequestBody saleYn,
            @Part("tag") RequestBody tag,
            @Part MultipartBody.Part[] imgList,
            @Part("useDate") RequestBody useDate,
            @Part("purchaseKind") RequestBody purchaseKind,
            @Part("productPrice") RequestBody productPrice,
            @Part("tradeKind") RequestBody tradeKind,
            @Part("boxYn") RequestBody boxYn,
            @Part("brand") RequestBody brand,
            @Part("purpose") RequestBody purpose,
            @Part("modelNo") RequestBody modelNo,
            @Part("serialNo") RequestBody serialNo,
            @Part("repairYn") RequestBody repairYn,
            @Part("productRelatedUrl") RequestBody productRelatedUrl,
            @Part("uid") RequestBody uid
    );

    // 상품 글가져오기
    @GET(ApiDefine.Body.SELECT_PRODUCT)
    @Headers({ApiDefine.Header.CONTENT_TYPE_JSON})
    Single<Response<ProductsWrapper>> selectProduct(@Query("uid") String uid);

    // 장터 - 상세물품페이지
    @GET(ApiDefine.Body.DETAIL_PRODUCT)
    @Headers({ApiDefine.Header.CONTENT_TYPE_JSON})
    Single<Response<ProductWrapper>> detailProduct(@Query("productId") String productId);

    // 장터 - 메인페이지
    @GET(ApiDefine.Body.MAIN_PRODUCT)
    @Headers({ApiDefine.Header.CONTENT_TYPE_JSON})
    Single<Response<ProductsWrapper>> mainProduct();

    // 장터 - 마이페이지 - 판매내역
    @GET(ApiDefine.Body.MY_PAGE_PRODUCT)
    @Headers({ApiDefine.Header.CONTENT_TYPE_JSON})
    Single<Response<ProductsWrapper>> myPageProduct(@Query("uid") String uid);

    // 장터 - 마이페이지 - 구매내역
    @GET(ApiDefine.Body.TARGET_PRODUCT)
    @Headers({ApiDefine.Header.CONTENT_TYPE_JSON})
    Single<Response<ProductsWrapper>> targetProduct(@Query("targetUid") String uid);

    // 장터 - 상세물품페이지 - 관련매물
    @GET(ApiDefine.Body.PURPOSE_PRODUCT)
    @Headers({ApiDefine.Header.CONTENT_TYPE_JSON})
    Single<Response<ProductsWrapper>> purposeProduct(@Query("productId") int productId, @Query("purpose") String purpose);

    // 장터 - 추천매물
    @GET(ApiDefine.Body.RECOMMAND_PRODUCT)
    @Headers({ApiDefine.Header.CONTENT_TYPE_JSON})
    Single<Response<ProductsWrapper>> recommandProduct(@Query("categoryDepth1") String categoryDepth1, @Query("categoryDepth2") String categoryDepth2);

    // 장터태그, 장터내용 검색
    @GET(ApiDefine.Body.SEARCH_PRODUCT)
    @Headers({ApiDefine.Header.CONTENT_TYPE_JSON})
    Single<Response<SearchWrapper>> searchProduct(@Query("keyword") String keyword);

    //장터 - 판매완료 선택 리스트
    @GET(ApiDefine.Body.TARGET_USER_LIST)
    @Headers({ApiDefine.Header.CONTENT_TYPE_JSON})
    Single<Response<BuyerWrapper>> targetUserList(@Query("productId") int productId, @Query("sourceUid") String sourceUid);

    //장터 - 판매완료 업데이트
    @GET(ApiDefine.Body.TARGET_USER_UPDATE)
    @Headers({ApiDefine.Header.CONTENT_TYPE_JSON})
    Single<Response<Void>> targetUserUpdate(@Query("productId") int productId, @Query("sourceUid") String sourceUid, @Query("targetUid") String targetUid);

    //장터 - 구매자 후기 업데이트
    @GET(ApiDefine.Body.TARGET_USER_REVIEW)
    @Headers({ApiDefine.Header.CONTENT_TYPE_JSON})
    Single<Response<Void>> targetUserReview(@Query("productId") int productId, @Query("targetUid") String targetUid, @Query("review") String review);

    //관심 목록 토글
    @POST(ApiDefine.Body.SWITCH_FAVORITE)
    @Headers({ApiDefine.Header.CONTENT_TYPE_JSON})
    Single<Response<Void>> switchFavorite(@Query("uid") String uid, @Query("productId") int productId);

    // 관심목록 리스트
    @GET(ApiDefine.Body.SELECT_FAVORITE)
    @Headers({ApiDefine.Header.CONTENT_TYPE_JSON})
    Single<Response<ProductsWrapper>> selectFavorite(@Query("uid") String uid);

    // 검색
    @POST(ApiDefine.Body.SEARCH_KEYWORD)
    @Headers({ApiDefine.Header.CONTENT_TYPE_JSON})
    Single<Response<SearchWrapper>> searchKeyword(@Body SearchBody searchBody);
}