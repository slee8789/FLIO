package com.fund.flio.core;

import android.app.Application;
import android.content.Context;

import androidx.annotation.Nullable;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

import com.facebook.stetho.Stetho;
import com.fund.flio.BuildConfig;
import com.fund.flio.R;
import com.fund.flio.data.DataManager;
import com.fund.flio.di.component.DaggerAppComponent;
import com.jakewharton.threetenabp.AndroidThreeTen;
import com.kakao.auth.IApplicationConfig;
import com.kakao.auth.KakaoAdapter;
import com.kakao.auth.KakaoSDK;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasAndroidInjector;

public class FlioApplication extends Application implements HasAndroidInjector, LifecycleObserver {

    @Inject
    DispatchingAndroidInjector<Object> dispatchingAndroidInjector;

    @Inject
    DataManager dataManager;

    private Thread.UncaughtExceptionHandler mUncaughtExceptionHandler;



    @Override
    public AndroidInjector<Object> androidInjector() {
        return dispatchingAndroidInjector;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void onForeground() {
        Logger.d("SSCA onForeground");

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void onBackground() {
        Logger.d("SSCA onBackground");

    }

    @Override
    public void onCreate() {
        super.onCreate();
        Logger.d("FLIO Application onCreate");
        DaggerAppComponent.builder()
                .application(this)
                .build()
                .inject(this);

        mUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(new UncaughtExceptionHandler());
        KakaoSDK.init(new KakaoAdapter() {
            private Context getApplicationContext() {
                return FlioApplication.this;
            }

            @Override
            public IApplicationConfig getApplicationConfig() {
                return this::getApplicationContext;
            }
        });
//        KakaoSdk.init(this, getString(R.string.kakao_app_key));

        Stetho.initializeWithDefaults(this);
        AndroidThreeTen.init(this);
        Logger.addLogAdapter(new AndroidLogAdapter() {
            @Override
            public boolean isLoggable(int priority, @Nullable String tag) {
                return BuildConfig.DEBUG;
            }
        });
//        Logger.addLogAdapter(new DiskLogAdapter());
//        RxJavaPlugins.setErrorHandler(e -> Logger.d("HelmetManagerApplication rx e " + e.getMessage()));

    }


    private class UncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {
        @Override
        public void uncaughtException(Thread thread, Throwable ex) {
            Logger.e("!!!!!uncaughtException!!!!! " + thread.getName() + ", " + ex);
            mUncaughtExceptionHandler.uncaughtException(thread, ex);
        }

    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        Logger.d("FLIO Application onTerminate");
    }

}