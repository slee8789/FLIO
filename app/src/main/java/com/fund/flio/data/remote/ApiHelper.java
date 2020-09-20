package com.fund.flio.data.remote;


import com.fund.flio.data.model.ChatRoomWrapper;
import com.fund.flio.data.model.MessageWrapper;
import com.fund.flio.data.model.User;
import com.fund.flio.data.model.body.ChatDetailBody;
import com.fund.flio.data.model.body.SendMessageBody;
import com.fund.flio.data.model.body.ChatListBody;
import com.fund.flio.data.model.body.TokenBody;

import io.reactivex.rxjava3.core.Single;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

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


}