package com.fund.flio.data.bus;

import com.fund.flio.data.model.Chat;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.subjects.PublishSubject;

public class AuthBus {

    private static AuthBus authBus;
    private final PublishSubject<Boolean> logoutSubject;

    private AuthBus() {
        logoutSubject = PublishSubject.create();
    }

    public static AuthBus getInstance() {
        if (authBus == null) {
            authBus = new AuthBus();
        }
        return authBus;
    }

    public void sendLogout() {
        logoutSubject.onNext(true);
    }

    public Observable<Boolean> getLogout() {
        return logoutSubject.ofType(Boolean.class);
    }

}
