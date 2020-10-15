package com.fund.flio.ui.main.message.chat.detail;


import android.content.Context;
import android.view.View;

import androidx.databinding.ObservableField;
import androidx.lifecycle.MutableLiveData;

import com.annimon.stream.Stream;
import com.fund.flio.data.DataManager;
import com.fund.flio.data.bus.MessageBus;
import com.fund.flio.data.enums.MessageType;
import com.fund.flio.data.model.Chat;
import com.fund.flio.data.model.ChatRoom;
import com.fund.flio.data.model.body.ChatDetailBody;
import com.fund.flio.data.model.body.ChatListBody;
import com.fund.flio.data.model.body.SendMessageBody;
import com.fund.flio.di.provider.ResourceProvider;
import com.fund.flio.di.provider.SchedulerProvider;
import com.fund.flio.ui.base.BaseViewModel;
import com.fund.flio.utils.CommonUtils;
import com.google.firebase.auth.FirebaseAuth;
import com.orhanobut.logger.Logger;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ChatDetailViewModel extends BaseViewModel {

    private Context mContext;

    public int mChatSeq;
    public boolean isSource;
    private ChatRoom mChatRoom;
    private SimpleDateFormat chatTimeFormat = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
    private DecimalFormat formatter = new DecimalFormat("###,###");
    public ObservableField<String> inputMessage = new ObservableField<>();
    public ObservableField<String> remoteUserName = new ObservableField<>();
    public ObservableField<String> productTitle = new ObservableField<>();
    public ObservableField<String> imageUrl = new ObservableField<>();
    public ObservableField<String> productPrice = new ObservableField<>();

    public void setChatRoom(ChatRoom mChatRoom) {
        Logger.d("setChatRoom " + mChatRoom);
        this.mChatRoom = mChatRoom;
        isSource = FirebaseAuth.getInstance().getUid().equals(mChatRoom.getChatSourceUid());
        remoteUserName.set(isSource ? mChatRoom.getChatTargetName() : mChatRoom.getChatSourceName());
        productTitle.set(mChatRoom.getTitle());
        productPrice.set((formatter.format(mChatRoom.getProductPrice()) + "원"));
        imageUrl.set("http://flio.iptime.org:8080/image/" + mChatRoom.getProductBaseUrl() + "/" + mChatRoom.getProductImageUrl().split(",")[0]);
        selectMyChatDetail(mChatRoom.getChatSeq());
    }

    private MutableLiveData<List<Chat>> chats = new MutableLiveData<>();

    public MutableLiveData<List<Chat>> getChats() {
        return chats;
    }

    public ChatDetailViewModel(Context context, DataManager dataManager, SchedulerProvider schedulerProvider, ResourceProvider resourceProvider) {
        super(dataManager, schedulerProvider, resourceProvider);
        mContext = context;
        Logger.d("ChatDetailViewModel constructor");
        subscribeEvent();
    }

    private void subscribeEvent() {
        getCompositeDisposable().add(MessageBus.getInstance().getDirect()
                .observeOn(getSchedulerProvider().ui())
                .subscribe(message -> {
                    chats.getValue().add(message);
                    chats.setValue(chats.getValue());
                }));
    }

    public void selectMyChat(int chatSeq) {
        mChatSeq = chatSeq;
        getCompositeDisposable().add(getDataManager().selectMyChat(new ChatListBody(FirebaseAuth.getInstance().getCurrentUser().getUid()))
                .concatMap(chatRooms -> {
                    if (chatRooms.isSuccessful()) {
                        setChatRoom(Stream.of(chatRooms.body().getChatRooms()).filter(chatRoom -> chatRoom.getChatSeq() == chatSeq).findFirst().get());
                    }
                    return getDataManager().selectMyChatDetail(new ChatDetailBody(chatSeq));
                })
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(chats -> {
                    if (chats.isSuccessful()) {
                        chats.body().getChats().add(0, new Chat(chats.body().getChats().get(0).getChatDate(), MessageType.DATE.ordinal()));
                        for (int i = 1; i < chats.body().getChats().size(); i++) {
                            if (CommonUtils.diffOfDate(chats.body().getChats().get(i - 1).getChatDate(), chats.body().getChats().get(i).getChatDate()) != 0) {
                                chats.body().getChats().add(i, new Chat(chats.body().getChats().get(i).getChatDate(), MessageType.DATE.ordinal()));
                            } else {
                                if (isSource) {
                                    chats.body().getChats().get(i).setChatType(chats.body().getChats().get(i).getChatSourceMessage() == null ? MessageType.REMOTE.ordinal() : MessageType.LOCAL.ordinal());
                                    chats.body().getChats().get(i).setImageUrl(mChatRoom.getChatTargetImageUrl());
                                } else {
                                    chats.body().getChats().get(i).setChatType(chats.body().getChats().get(i).getChatSourceMessage() == null ? MessageType.LOCAL.ordinal() : MessageType.REMOTE.ordinal());
                                    chats.body().getChats().get(i).setImageUrl(mChatRoom.getChatSourceImageUrl());
                                }
                            }
                        }
                        this.chats.setValue(chats.body().getChats());
                    }
                }, onError -> Logger.e("chatRooms error " + onError)));
    }

    public void selectMyChatDetail(int chatSeq) {
        mChatSeq = chatSeq;
        getCompositeDisposable().add(getDataManager().selectMyChatDetail(new ChatDetailBody(chatSeq))
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(chats -> {
                    if (chats.isSuccessful()) {
                        if (chats.body().getChats().size() != 0) {
                            chats.body().getChats().add(0, new Chat(chats.body().getChats().get(0).getChatDate(), MessageType.DATE.ordinal()));
                            for (int i = 1; i < chats.body().getChats().size(); i++) {
                                if (CommonUtils.diffOfDate(chats.body().getChats().get(i - 1).getChatDate(), chats.body().getChats().get(i).getChatDate()) != 0) {
                                    chats.body().getChats().add(i, new Chat(chats.body().getChats().get(i).getChatDate(), MessageType.DATE.ordinal()));
                                } else {
                                    if (isSource) {
                                        chats.body().getChats().get(i).setChatType(chats.body().getChats().get(i).getChatSourceMessage() == null ? MessageType.REMOTE.ordinal() : MessageType.LOCAL.ordinal());
                                        chats.body().getChats().get(i).setImageUrl(mChatRoom.getChatTargetImageUrl());
                                    } else {
                                        chats.body().getChats().get(i).setChatType(chats.body().getChats().get(i).getChatSourceMessage() == null ? MessageType.LOCAL.ordinal() : MessageType.REMOTE.ordinal());
                                        chats.body().getChats().get(i).setImageUrl(mChatRoom.getChatSourceImageUrl());
                                    }
                                }
                            }
                            this.chats.setValue(chats.body().getChats());
                        }
                    }
                }, onError -> Logger.e("messages error " + onError)));
    }

    public void onSend(View v) {
        getCompositeDisposable().add(getDataManager().sendMessage(new SendMessageBody(mChatSeq, isSource, getDataManager().getUserName(), inputMessage.get(), getDataManager().getUserImageUrl(), mChatRoom.getChatSourceMessageToken(), mChatRoom.getChatTargetMessageToken()))
                .observeOn(getSchedulerProvider().ui())
                .subscribeOn(getSchedulerProvider().io())
                .subscribe(Void -> {
                    if (chats.getValue() == null) {
                        ArrayList<Chat> firstChat = new ArrayList<>();
                        //Todo : chatIndex로 변경 현재는 200만 옴.
                        firstChat.add(new Chat(chatTimeFormat.format(System.currentTimeMillis()), MessageType.DATE.ordinal()));
                        firstChat.add(new Chat(mChatRoom.getChatSeq(), new Random().nextInt(), isSource, chatTimeFormat.format(System.currentTimeMillis()), inputMessage.get(), null, MessageType.LOCAL.ordinal()));
                        chats.setValue(firstChat);
                    } else {
                        chats.getValue().add(new Chat(mChatRoom.getChatSeq(), new Random().nextInt(), isSource, chatTimeFormat.format(System.currentTimeMillis()), inputMessage.get(), null, MessageType.LOCAL.ordinal()));
                        chats.setValue(chats.getValue());
                    }

                    inputMessage.set("");
                }));

    }

    @Override
    protected void onCleared() {
        super.onCleared();
        Logger.d("ChatDetailViewModel onCleared");
    }
}
