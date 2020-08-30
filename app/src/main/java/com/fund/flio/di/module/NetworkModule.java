package com.fund.flio.di.module;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.fund.flio.BuildConfig;
import com.fund.flio.data.remote.ApiHelper;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


@Module
public class NetworkModule {

    @Provides
    @Singleton
    @Named("flio")
    OkHttpClient provideOkHttpClient() {
        return new OkHttpClient.Builder()
                .addNetworkInterceptor(new StethoInterceptor())
                .build();
    }

    @Provides
    @Singleton
    @Named("auth")
    OkHttpClient provideOkHttpAuthClient() {
        return new OkHttpClient.Builder()
                .addNetworkInterceptor(new StethoInterceptor())
                .build();
    }


    @Provides
    @Singleton
    @Named("flio")
    Retrofit provideService(@Named("flio") OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .addCallAdapterFactory(RxJava3CallAdapterFactory.createAsync())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BuildConfig.REST_BASE_URL)
                .client(okHttpClient)
                .build();
    }

    @Provides
    @Singleton
    @Named("auth")
    Retrofit provideAuthService(@Named("auth") OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .addCallAdapterFactory(RxJava3CallAdapterFactory.createAsync())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BuildConfig.REST_AUTH_URL)
                .client(okHttpClient)
                .build();
    }


    @Provides
    @Singleton
    @Named("flio")
    ApiHelper provideApiHelper(@Named("flio") Retrofit retrofit) {
        return retrofit.create(ApiHelper.class);
    }

    @Provides
    @Singleton
    @Named("auth")
    ApiHelper provideAuthApiHelper(@Named("auth") Retrofit retrofit) {
        return retrofit.create(ApiHelper.class);
    }



}
