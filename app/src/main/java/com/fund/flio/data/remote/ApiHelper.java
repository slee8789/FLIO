package com.fund.flio.data.remote;


import com.fund.flio.data.model.body.TokenBody;

import java.util.List;

import io.reactivex.Single;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiHelper {

    // 유저 정보
    @GET(ApiDefine.Body.TEST_SELECT)
    @Headers({ApiDefine.Header.ACCEPT_JSON})
    Single<Response<Void>> getTestSelect();

    // 유저 정보
    @POST(ApiDefine.Body.AUTH_TOKEN)
    @Headers({ApiDefine.Header.CONTENT_TYPE_JSON})
    Single<Response<Void>> postAuthToken(@Body TokenBody tokenBody);

}