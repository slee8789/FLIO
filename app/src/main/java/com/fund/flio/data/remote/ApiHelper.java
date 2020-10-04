package com.fund.flio.data.remote;


import com.fund.flio.data.model.ChatRoomWrapper;
import com.fund.flio.data.model.MessageWrapper;
import com.fund.flio.data.model.User;
import com.fund.flio.data.model.body.ChatDetailBody;
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

    // 테스트 이미지 업로드
    @Multipart
    @POST(ApiDefine.Body.TEST_IMAGE_UPLOAD)
    Single<Response<Void>> testImageUpload(@Part MultipartBody.Part[] imgList, @Part("boardKind") RequestBody boardKind, @Part("boardTitle") RequestBody boardTitle);

    // 테스트 이미지 업로드
    @Multipart
    @POST(ApiDefine.Body.INSERT_PRODUCT)
    Single<Response<Void>> insertProduct(
            @Part("productName") RequestBody productName,
            @Part("title") RequestBody title,
            @Part("content") RequestBody content,
            @Part("status") RequestBody status,
            @Part("saleYn") RequestBody saleYn,
            @Part("classification") RequestBody classification,
            @Part("tag") RequestBody tag,
            @Part MultipartBody.Part[] imgList,
            @Part("displayYn") RequestBody displayYn,
            @Part("useDate") RequestBody useDate,
            @Part("purchaseKind") RequestBody purchaseKind,
            @Part("purchasePrice") RequestBody purchasePrice,
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
    Single<Response<Void>> selectProduct(@Query("uid") String uid);
}