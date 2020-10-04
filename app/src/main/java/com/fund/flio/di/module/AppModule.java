package com.fund.flio.di.module;

import android.app.Application;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.room.Room;

import com.fund.flio.core.AppConstant;
import com.fund.flio.data.AppDataManager;
import com.fund.flio.data.DataManager;
import com.fund.flio.data.local.db.AppDatabase;
import com.fund.flio.data.local.db.AppDbHelper;
import com.fund.flio.data.local.db.DbHelper;
import com.fund.flio.data.local.prefs.AppPreferencesHelper;
import com.fund.flio.data.local.prefs.PreferencesHelper;
import com.fund.flio.di.provider.AppResourceProvider;
import com.fund.flio.di.provider.AppSchedulerProvider;
import com.fund.flio.di.provider.ResourceProvider;
import com.fund.flio.di.provider.SchedulerProvider;
import com.fund.flio.di.qualifier.DatabaseInfo;
import com.fund.flio.di.qualifier.PreferenceInfo;
import com.fund.flio.ui.main.community.certificate.list.CertificateAdapter;
import com.fund.flio.ui.main.community.certificate.register.CertificateThumbnailAdapter;
import com.fund.flio.ui.main.community.event.list.EventAdapter;
import com.fund.flio.ui.main.community.event.register.EventThumbnailAdapter;
import com.fund.flio.ui.main.community.news.NewsAdapter;
import com.fund.flio.ui.main.market.register.ProductThumbnailAdapter;
import com.fund.flio.ui.main.message.chat.detail.ChatAdapter;
import com.fund.flio.ui.main.message.chat.list.ChatListAdapter;
import com.fund.flio.ui.main.home.BannerAdapter;
import com.fund.flio.ui.main.home.CertificatedAdapter;
import com.fund.flio.ui.main.home.RecommendAdapter;
import com.fund.flio.ui.main.market.ProductAdapter;
import com.fund.flio.ui.main.message.reply.list.ReplyListAdapter;
import com.fund.flio.ui.main.search.SearchRecentAdapter;


import java.util.ArrayList;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

import static com.fund.flio.core.AppConstant.NOTIFICATION_CHANNEL_ID_CHAT;
import static com.fund.flio.core.AppConstant.NOTIFICATION_CHANNEL_NAME_CHAT;

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
    AppDatabase provideAppDatabase(@DatabaseInfo String dbName, Context context) {
        return Room.databaseBuilder(context, AppDatabase.class, dbName).fallbackToDestructiveMigration()
                .build();
    }

    @Provides
    @DatabaseInfo
    String provideDatabaseName() {
        return AppConstant.DB_NAME;
    }

    @Provides
    @Singleton
    DbHelper provideDbHelper(AppDbHelper appDbHelper) {
        return appDbHelper;
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

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Provides
    NotificationChannel provideNotificationChatChannel() {
        NotificationChannel chatChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID_CHAT, NOTIFICATION_CHANNEL_NAME_CHAT, NotificationManager.IMPORTANCE_HIGH);
        chatChannel.enableVibration(true);
        chatChannel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);
        return chatChannel;
    }

    @Provides
    BannerAdapter provideBannerAdapter() {
        return new BannerAdapter(new ArrayList<>());
    }

    @Provides
    RecommendAdapter provideRecommendAdapter() {
        return new RecommendAdapter(new ArrayList<>());
    }

    @Provides
    ProductAdapter provideProductAdapter() {
        return new ProductAdapter(new ArrayList<>());
    }

    @Provides
    CertificatedAdapter provideCertificatedAdapter() {
        return new CertificatedAdapter(new ArrayList<>());
    }

    @Provides
    ChatListAdapter provideChatListAdapter() {
        return new ChatListAdapter(new ArrayList<>());
    }

    @Provides
    ChatAdapter provideChatAdapter() {
        return new ChatAdapter(new ArrayList<>());
    }

    @Provides
    ReplyListAdapter provideReplyListAdapter() {
        return new ReplyListAdapter(new ArrayList<>());
    }

    @Provides
    NewsAdapter provideNewsAdapter() {
        return new NewsAdapter(new ArrayList<>());
    }

    @Provides
    CertificateAdapter provideCertificateAdapter() {
        return new CertificateAdapter(new ArrayList<>());
    }

    @Provides
    EventAdapter provideEventAdapter() {
        return new EventAdapter(new ArrayList<>());
    }

    @Provides
    SearchRecentAdapter provideSearchRecentAdapter() {
        return new SearchRecentAdapter(new ArrayList<>());
    }

    @Provides
    ProductThumbnailAdapter provideProductThumbnailAdapter() {
        return new ProductThumbnailAdapter(new ArrayList<>());
    }

    @Provides
    CertificateThumbnailAdapter provideCertificateThumbnailAdapter() {
        return new CertificateThumbnailAdapter(new ArrayList<>());
    }

    @Provides
    EventThumbnailAdapter provideEventThumbnailAdapter() {
        return new EventThumbnailAdapter(new ArrayList<>());
    }

}
