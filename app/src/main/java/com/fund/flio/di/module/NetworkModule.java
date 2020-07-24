package com.fund.flio.di.module;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.fund.flio.BuildConfig;
import com.fund.flio.data.remote.ApiHelper;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import pl.droidsonroids.retrofit2.JspoonConverterFactory;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;


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
    @Named("flio")
    Retrofit provideService(@Named("flio") OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createAsync())
                .addConverterFactory(JspoonConverterFactory.create())
                .baseUrl(BuildConfig.REST_BASE_URL)
                .client(okHttpClient)
                .build();
    }


    @Provides
    @Singleton
    @Named("flio")
    ApiHelper provideApiHelper(@Named("flio") Retrofit retrofit) {
        return retrofit.create(ApiHelper.class);
    }



}
