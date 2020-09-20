package com.fund.flio.service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.navigation.NavDeepLinkBuilder;

import com.fund.flio.R;
import com.fund.flio.core.FlioApplication;
import com.fund.flio.data.DataManager;
import com.fund.flio.data.bus.MessageBus;
import com.fund.flio.data.enums.MessageType;
import com.fund.flio.data.model.Chat;
import com.fund.flio.data.model.ChatRoom;
import com.fund.flio.utils.Foreground;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.orhanobut.logger.Logger;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Random;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

import static com.fund.flio.core.AppConstant.NOTIFICATION_CHANNEL_ID_CHAT;
import static com.fund.flio.core.AppConstant.NOTIFICATION_ID_CHAT;

public class PushService extends FirebaseMessagingService {

    @Inject
    DataManager dataManager;

    @Inject
    NotificationManager notificationManager;

    @Inject
    NotificationChannel notificationChannel;

    private boolean isSource;
    private int chatSeq;
    private String remoteName, chatMessage, remoteUserImageUrl;
    private SimpleDateFormat chatTimeFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        Logger.d("PushService onMessageReceived remoteMessage " + remoteMessage.getData());
//        Logger.d("PushService onMessageReceived remoteMessage type " + remoteMessage.getData().get("chatSeq") + ", " + remoteMessage.getData().get("chatSourceMessage") + ", " + remoteMessage.getData().get("chatTargetMessage") + ", " + remoteMessage.getData().get("chatDate") + ", " + Foreground.get().isBackground());
        //Todo : chatSeq로 현재 채팅방을 확인하여 백그라운드이거나 다른방 채팅일 경우에 노티
        //Todo : 푸쉬 클릭해서 채팅상세화면까지 온 후 뒤로가기 클릭시 어느화면? 정의 필요
        isSource = remoteMessage.getData().get("chatSourceMessage") != null;
        chatSeq = Integer.parseInt(remoteMessage.getData().get("chatSeq"));
        if (isSource) {
            remoteName = remoteMessage.getData().get("chatSourceName");
            chatMessage = remoteMessage.getData().get("chatSourceMessage");
            remoteUserImageUrl = remoteMessage.getData().get("chatSourceImageUrl");
        } else {
            remoteName = remoteMessage.getData().get("chatTargetName");
            chatMessage = remoteMessage.getData().get("chatTargetMessage");
            remoteUserImageUrl = remoteMessage.getData().get("chatTargetImageUrl");
        }
        if (Foreground.get().isBackground()) {

            Bundle chatBundle = new Bundle();
            chatBundle.putInt("chatSeq", chatSeq);
            createNotification(NOTIFICATION_CHANNEL_ID_CHAT, NOTIFICATION_ID_CHAT, remoteName, chatMessage, remoteUserImageUrl, getPendingIntent(chatBundle, R.id.nav_chat_detail), null, Notification.CATEGORY_MESSAGE, null, null);

        } else {
            Logger.d("flio app " + ((FlioApplication) getApplicationContext()).getCurrentChatSeq());
            if (((FlioApplication) getApplicationContext()).getCurrentDestinationId() == R.id.nav_chat_detail && ((FlioApplication) getApplicationContext()).getCurrentChatSeq() == chatSeq) {
                MessageBus.getInstance().sendMessage(new Chat(chatSeq, new Random().nextInt(), isSource, chatTimeFormat.format(System.currentTimeMillis()), chatMessage, remoteUserImageUrl, MessageType.REMOTE.ordinal()));
            } else {
                Bundle chatBundle = new Bundle();
                chatBundle.putInt("chatSeq", chatSeq);
                createNotification(NOTIFICATION_CHANNEL_ID_CHAT, NOTIFICATION_ID_CHAT, remoteName, chatMessage, remoteUserImageUrl, getPendingIntent(chatBundle, R.id.nav_chat_detail), null, Notification.CATEGORY_MESSAGE, null, null);
            }
        }

    }

    @Override
    public void onNewToken(@NonNull String token) {
        super.onNewToken(token);
        Logger.i("onNewToken " + token);
        dataManager.setMessageToken(token);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        AndroidInjection.inject(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationManager.createNotificationChannel(notificationChannel);
        }
    }

    public Bitmap getBitmapfromUrl(String imageUrl) {
        try {
            URL url = new URL(imageUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            return BitmapFactory.decodeStream(input);

        } catch (Exception e) {
            Logger.e("Error in getting notification image: " + e.getLocalizedMessage());
            return null;
        }
    }

    private void createNotification(String channelId, int notificationId, String title, String body, String remoteUserImageUrl, PendingIntent pendingIntent, PendingIntent deleteIntent, String category, Uri soundUri, Notification.Action action) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            //Todo 왜 아이콘이 안될까
            NotificationCompat.Builder builder = new NotificationCompat.Builder(this, channelId)
//                    .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.push_icon))
                    .setLargeIcon(getBitmapfromUrl(remoteUserImageUrl))
                    .setSmallIcon(R.mipmap.push_icon)
                    .setContentTitle(title)
                    .setContentText(body)
                    .setAutoCancel(true)
                    .setShowWhen(true)
                    .setCategory(category)
                    .setContentIntent(pendingIntent)
                    .setDeleteIntent(deleteIntent)
                    .setPriority(NotificationCompat.PRIORITY_HIGH)
                    .setFullScreenIntent(pendingIntent, true);

            notificationManager.notify(notificationId, builder.build());
        } else {
            Notification notification = new Notification.Builder(this)
                    .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.push_icon))
                    .setSmallIcon(R.mipmap.push_icon)
                    .setCategory(category)
                    .setContentTitle(title)
                    .setContentText(body)
                    .setAutoCancel(true)
                    .setContentIntent(pendingIntent)
                    .setDeleteIntent(deleteIntent)
                    .setPriority(Notification.PRIORITY_HIGH)
                    .setFullScreenIntent(pendingIntent, true)
                    .setSound(soundUri)
                    .build();
            notification.defaults |= Notification.DEFAULT_VIBRATE;
            notificationManager.notify(notificationId, notification);
        }
    }

    private PendingIntent getPendingIntent(Bundle bundle, int destination) {
        NavDeepLinkBuilder navDeepLinkBuilder = new NavDeepLinkBuilder(this);
        return navDeepLinkBuilder
                .setGraph(R.navigation.nav_graph_main)
                .setDestination(destination)
                .setArguments(bundle)
                .createPendingIntent();
    }
}