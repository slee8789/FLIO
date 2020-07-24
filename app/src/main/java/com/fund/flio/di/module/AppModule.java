package com.fund.flio.di.module;

import android.app.Application;
import android.app.NotificationManager;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.room.Room;

import com.fund.flio.BuildConfig;
import com.fund.flio.core.AppConstant;
import com.fund.flio.data.AppDataManager;
import com.fund.flio.data.DataManager;
import com.fund.flio.data.local.prefs.AppPreferencesHelper;
import com.fund.flio.data.local.prefs.PreferencesHelper;
import com.fund.flio.di.provider.AppResourceProvider;
import com.fund.flio.di.provider.AppSchedulerProvider;
import com.fund.flio.di.provider.ResourceProvider;
import com.fund.flio.di.provider.SchedulerProvider;
import com.fund.flio.di.qualifier.DatabaseInfo;
import com.fund.flio.di.qualifier.PreferenceInfo;
import com.fund.flio.ui.main.home.BannerAdapter;
import com.nhn.android.naverlogin.OAuthLogin;
import com.nhn.android.naverlogin.OAuthLoginHandler;


import java.util.ArrayList;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    @Provides
    @Singleton
    Context provideContext(Application application) {
        return application.getApplicationContext();
    }

    @Provides
    @Singleton
    static SharedPreferences provideSharedPreferences(Context context) {
        return context.getSharedPreferences(providePreferenceName(), Context.MODE_PRIVATE);
    }

    @Provides
    @PreferenceInfo
    static String providePreferenceName() {
        return AppConstant.PREF_NAME;
    }

    @Provides
    @Singleton
    PreferencesHelper providePreferencesHelper(AppPreferencesHelper appPreferencesHelper) {
        return appPreferencesHelper;
    }

    @Provides
    @Singleton
    DataManager provideDataManager(AppDataManager appDataManager) {
        return appDataManager;
    }

    @Provides
    SchedulerProvider provideSchedulerProvider() {
        return new AppSchedulerProvider();
    }

    @Provides
    ResourceProvider provideResourceProvider(Context context) {
        return new AppResourceProvider(context);
    }

    @Provides
    NotificationManager provideNotificationManager(Context context) {
        return (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
    }

    @Provides
    BannerAdapter provideBannerAdapter() {
        return new BannerAdapter(new ArrayList<>());
    }

}
