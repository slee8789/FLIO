package com.fund.flio.data.remote;


import com.fund.flio.data.model.BuyerWrapper;
import com.fund.flio.data.model.ChatRoom;
import com.fund.flio.data.model.ChatRoomWrapper;
import com.fund.flio.data.model.Data;
import com.fund.flio.data.model.InsertMyChatWrapper;
import com.fund.flio.data.model.MessageWrapper;
import com.fund.flio.data.model.Product;
import com.fund.flio.data.model.ProductWrapper;
import com.fund.flio.data.model.User;
import com.fund.flio.data.model.body.ChatDetailBody;
import com.fund.flio.data.model.body.InsertMyChatBody;
import com.fund.flio.data.model.body.SendMessageBody;
import com.fund.flio.data.model.body.ChatListBody;
import com.fund.flio.data.model.body.TestBody;
import com.fund.flio.data.model.body.TokenBody;

import java.util.LinkedHashMap;
import java.util.List;

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
import retrofit2.http.PartMap;
import retrofit2.http.Query;

public interface ApiHelper {

    // 유저 정보
    @POST(ApiDefine.Body.AUTH_TOKEN)
    @Headers({ApiDefine.Header.CONTENT_TYPE_JSON})
    Single<Response<User>> postAuthToken(@Body TokenBody tokenBody);

    // 유저 등록
    @POST(ApiDefine.Body.INSERT_USER)
    @Headers({ApiDefine.Header.CONTENT_TYPE_JSON})
    Single<Response<Void>> postInsertUser(@Body User user);

    // 유저 등록
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

    // 채팅 메세지 저장
    @POST(ApiDefine.Body.SEND_MESSAGE)
    @Headers({ApiDefine.Header.CONTENT_TYPE_JSON})
    Single<Response<Void>> sendMessage(@Body SendMessageBody sendMessageBody);

    // 채팅 메세지 저장
    @POST(ApiDefine.Body.INSERT_MY_CHAT)
    @Headers({ApiDefine.Header.CONTENT_TYPE_JSON})
    Single<Response<InsertMyChatWrapper>> insertMyChat(@Body InsertMyChatBody insertMyChatBody);

    // 테스트 이미지 업로드
    @Multipart
    @POST(ApiDefine.Body.INSERT_PRODUCT)
    Single<Response<Void>> insertProduct(
            @Part("title") RequestBody title,
            @Part("content") RequestBody content,
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
    Single<Response<ProductWrapper>> selectProduct(@Query("uid") String uid);

    // 상품 글가져오기
    @GET(ApiDefine.Body.DETAIL_PRODUCT)
    @Headers({ApiDefine.Header.CONTENT_TYPE_JSON})
    Single<Response<ProductWrapper>> detailProduct(@Query("productId") String productId);

    // 상품 글가져오기
    @GET(ApiDefine.Body.MAIN_PRODUCT)
    @Headers({ApiDefine.Header.CONTENT_TYPE_JSON})
    Single<Response<ProductWrapper>> mainProduct();

    // 상품 글가져오기
    @GET(ApiDefine.Body.MY_PAGE_PRODUCT)
    @Headers({ApiDefine.Header.CONTENT_TYPE_JSON})
    Single<Response<ProductWrapper>> myPageProduct(@Query("uid") String uid);

    // 상품 글가져오기
    @GET(ApiDefine.Body.PURPOSE_PRODUCT)
    @Headers({ApiDefine.Header.CONTENT_TYPE_JSON})
    Single<Response<ProductWrapper>> purposeProduct(@Query("purpose") String purpose);

    // 상품 글가져오기
    @GET(ApiDefine.Body.RECOMMAND_PRODUCT)
    @Headers({ApiDefine.Header.CONTENT_TYPE_JSON})
    Single<Response<ProductWrapper>> recommandProduct(@Query("categoryDepth1") String categoryDepth1, @Query("categoryDepth2") String categoryDepth2);

    // 상품 글가져오기
    @GET(ApiDefine.Body.SEARCH_PRODUCT)
    @Headers({ApiDefine.Header.CONTENT_TYPE_JSON})
    Single<Response<ProductWrapper>> searchProduct(@Query("keyword") String keyword);

    //장터 - 판매완료 선택 리스트
    @GET(ApiDefine.Body.TARGET_USER_LIST)
    @Headers({ApiDefine.Header.CONTENT_TYPE_JSON})
    Single<Response<BuyerWrapper>> targetUserList(@Query("productId") int productId, @Query("sourceUid") String sourceUid);

    //장터 - 판매완료 업데이트
    @GET(ApiDefine.Body.TARGET_USER_UPDATE)
    @Headers({ApiDefine.Header.CONTENT_TYPE_JSON})
    Single<Response<ProductWrapper>> targetUserUpdate(@Query("productId") int productId, @Query("sourceUid") String sourceUid,  @Query("targetUid") String targetUid);

    //
    @POST(ApiDefine.Body.SWITCH_FAVORITE)
    @Headers({ApiDefine.Header.CONTENT_TYPE_JSON})
    Single<Response<Void>> switchFavorite(@Query("uid") String uid, @Query("productId") int productId);
}