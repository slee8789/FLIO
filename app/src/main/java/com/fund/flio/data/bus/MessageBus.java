package com.fund.flio.data.bus;

import com.fund.flio.data.model.Chat;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.subjects.PublishSubject;

public class MessageBus {

    private static MessageBus messageBus;
    private final PublishSubject<Chat> directSubject;
    private final PublishSubject<Chat> messageSubject;

    private MessageBus() {
        directSubject = PublishSubject.create();
        messageSubject = PublishSubject.create();
    }

    public static MessageBus getInstance() {
        if (messageBus == null) {
            messageBus = new MessageBus();
        }
        return messageBus;
    }

    public void sendDirect(Chat chat) {
        directSubject.onNext(chat);
    }

    public Observable<Chat> getDirect() {
        return directSubject.ofType(Chat.class);
    }

    public void sendMessage(Chat chat) {
        messageSubject.onNext(chat);
    }

    public Observable<Chat> getMessage() {
        return messageSubject.ofType(Chat.class);
    }

}
