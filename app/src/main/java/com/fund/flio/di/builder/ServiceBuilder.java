package com.fund.flio.di.builder;

import com.fund.flio.service.PushService;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ServiceBuilder {

    @ContributesAndroidInjector
    abstract PushService bindPushService();

}
