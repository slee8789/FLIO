package com.fund.flio.data.bus;

import com.fund.flio.data.model.Message;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.subjects.PublishSubject;

public class MessageBus {

    private static MessageBus messageBus;
    private final PublishSubject<Message> messageSubject;

    private MessageBus() {
        messageSubject = PublishSubject.create();
    }

    public static MessageBus getInstance() {
        if (messageBus == null) {
            messageBus = new MessageBus();
        }
        return messageBus;
    }

    public void sendMessage(Message isOtherViews) {
        messageSubject.onNext(isOtherViews);
    }

    public Observable<Message> getMessage() {
        return messageSubject.ofType(Message.class);
    }

}
