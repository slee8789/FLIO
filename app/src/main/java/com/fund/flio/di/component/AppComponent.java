package com.fund.flio.di.component;

import android.app.Application;

import com.fund.flio.core.FlioApplication;
import com.fund.flio.di.builder.ActivityBuilder;
import com.fund.flio.di.builder.ReceiverBuilder;
import com.fund.flio.di.builder.ServiceBuilder;
import com.fund.flio.di.module.AppModule;
import com.fund.flio.di.module.AuthModule;
import com.fund.flio.di.module.NetworkModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;

@Singleton
@Component(modules = {
        AndroidInjectionModule.class,
        AppModule.class,
        AuthModule.class,
        NetworkModule.class,
        ActivityBuilder.class,
        ReceiverBuilder.class,
        ServiceBuilder.class})
public interface AppComponent {

    void inject(FlioApplication app);

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }

}

